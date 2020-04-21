package br.com.rjguastalli.voto.service;

import br.com.rjguastalli.GenericException;
import br.com.rjguastalli.model.UserInfoResponse;
import br.com.rjguastalli.restclient.UserInfoRestClient;
import br.com.rjguastalli.stub.entity.SessaoEntityStub;
import br.com.rjguastalli.stub.entity.UserInfoResponseStub;
import br.com.rjguastalli.voto.repository.VotoAssociadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class RegraVotoAssociadoServiceTest {

    @Mock
    private VotoAssociadoRepository votoAssociadoRepository;

    @Mock
    private UserInfoRestClient userInfoRestClient;

    @InjectMocks
    private RegraVotoAssociadoService regraVotoAssociadoService;

    private UserInfoResponse userInfoResponse;

    @BeforeEach
    void setUp() {
        userInfoResponse = UserInfoResponseStub.userInfoResponseCpfAbleMock();
    }


    @Test
    void testDeveVerificarSeOCpfValido() {
        when(userInfoRestClient.buscarUsuario(anyString())).thenReturn(UserInfoResponseStub.userInfoResponseCpfAbleMock());
        regraVotoAssociadoService.verificarCpfValido(anyString());
        assertAll(() -> regraVotoAssociadoService.verificarCpfValido(anyString()));
    }

    @Test
    void testDeveVerificarSeOCpfInvalido() {
        when(userInfoRestClient.buscarUsuario(anyString())).thenReturn(UserInfoResponseStub.userInfoResponseCpfUnableMock());
        var genericException = assertThrows(GenericException.class, () ->
                regraVotoAssociadoService.verificarCpfValido(anyString())
        );
        assertEquals("Este CPF não está inválido ou não é apto para votar nesta sessão.",
                genericException.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, genericException.getStatus());
    }

    @Test
    void testDeveSeAlgumaExceptionOcorreu() {
        when(userInfoRestClient.buscarUsuario(anyString())).thenReturn(UserInfoResponseStub.userInfoResponseCpfExceptionfMock());
        var genericException = assertThrows(GenericException.class, () ->
                regraVotoAssociadoService.verificarCpfValido(anyString())
        );
        assertEquals("Impossível prosseguir a votação para este CPF.",
                genericException.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, genericException.getStatus());
    }

    @Test
    void testSessaoNaoPertenceAPauta() {
        var sessaoEntity = SessaoEntityStub.sessaoEntityRetornoComSessaoId();
        var genericException = assertThrows(GenericException.class, () ->
                regraVotoAssociadoService.sessaoEstaNaPauta(sessaoEntity, 2L)
        );
        assertEquals("Esta sessão não consta para esta pauta.",
                genericException.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, genericException.getStatus());
    }

    @Test
    void testSessaoPertenceAPauta() {
        var sessaoEntity = SessaoEntityStub.sessaoEntityRetornoComSessaoId();
        assertAll(() -> regraVotoAssociadoService.sessaoEstaNaPauta(sessaoEntity, 1L));
    }

    @Test
    void testDataDaSessaoValidaParaVotacao() {
        var sessaoEntity = SessaoEntityStub.sessaoEntityRetornoComSessaoId();
        sessaoEntity.setDataTermino(LocalDateTime.now().plusMinutes(100));
        assertAll(() -> regraVotoAssociadoService.dataDaSessaoValidaParaVotacao(sessaoEntity));
    }

    @Test
    void testDataDaSessaoInvalidaParaVotacao() {
        var sessaoEntity = SessaoEntityStub.sessaoEntityRetornoComSessaoId();
        sessaoEntity.setDataTermino(LocalDateTime.now());
        var genericException = assertThrows(GenericException.class, () ->
                regraVotoAssociadoService.dataDaSessaoValidaParaVotacao(sessaoEntity)
        );
        assertEquals("Esta sessão esta com o perído inválido (data abertura: "
                        .concat(sessaoEntity.getDataAbertura().toString()
                                .concat(" data fechamento: ").concat(sessaoEntity.getDataTermino().toString())),
                genericException.getMessage()
        );
        assertEquals(HttpStatus.BAD_REQUEST, genericException.getStatus());

    }

    @Test
    void testAssociadoJaVotou() {
        var cpfAssociado = "46569992023";
        when(votoAssociadoRepository.existsBySessaoIdAndCpfAssociado(1L, cpfAssociado)).thenReturn(true);
        var genericException = assertThrows(GenericException.class, () ->
                regraVotoAssociadoService.verificaAssociadoJaVotou(1L, cpfAssociado)
        );
        assertEquals("Este CPF: "
                        .concat(cpfAssociado)
                        .concat(" já votou nesta sessão e seu voto já foi computado"),
                genericException.getMessage()
        );
        assertEquals(HttpStatus.BAD_REQUEST, genericException.getStatus());
    }

    @Test
    void testAssociadoQueNaoVotou() {
        var cpfAssociado = "46569992023";
        when(votoAssociadoRepository.existsBySessaoIdAndCpfAssociado(1L, cpfAssociado)).thenReturn(false);
        assertAll(() -> regraVotoAssociadoService.verificaAssociadoJaVotou(1l, cpfAssociado));
    }

}