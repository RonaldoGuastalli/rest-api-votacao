package br.com.rjguastalli.pauta.service;

import br.com.rjguastalli.GenericException;
import br.com.rjguastalli.pauta.repository.PautaRepository;
import br.com.rjguastalli.sessao.service.SessaoService;
import br.com.rjguastalli.stub.entity.PautaEntityStub;
import br.com.rjguastalli.stub.entity.SessaoEntityStub;
import br.com.rjguastalli.stub.entity.model.PautaModelStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PautaServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private SessaoService sessaoService;

    @InjectMocks
    private PautaService pautaService;

    @Test
    void testdeveRetornarUmaPauta() {
        when(pautaRepository.save(any())).thenReturn(PautaEntityStub.pautaEntityRetorno());
        var response = pautaService.criarNovaPauta(PautaModelStub.pautaModel());
        assertEquals(response, PautaModelStub.pautaModelRetornoAposSalva());
    }

    @Test
    void testDeveBuscarUmaPautaERetornar() {
        when(pautaRepository.findById(any())).thenReturn(Optional.of(PautaEntityStub.pautaEntityRetorno()));
        var entity = pautaService.buscarPauta(1L);
        assertEquals(entity, PautaEntityStub.pautaEntityRetorno());
    }

    @Test
    public void testdeveRetornarUmaExceptionCasoAPautaNaoSEjaEncontrada() {
        when(pautaRepository.findById(any())).thenReturn(Optional.empty());
        var thrown = assertThrows(GenericException.class, () ->
                pautaService.buscarPauta(4L)
        );
        assertEquals("Pauta não encontrada para o id: 4", thrown.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, thrown.getStatus());
    }

    @Test
    void testRetornaUmaPautaComEstatisticasComputadas() {
        when(sessaoService.sessoes(any())).thenReturn(List.of(SessaoEntityStub.sessaoEntityRetorno()));
        when(pautaRepository.findById(any()))
                .thenReturn(Optional.of(PautaEntityStub.pautaEntityRetorno()));

        var response = pautaService.buscarPauta(1L);
        assertEquals(1L, response.getId().longValue());
        assertEquals(1L, response.getEstatisticaEntity().getVotoContra().longValue());
        assertEquals(0L, response.getEstatisticaEntity().getVotoFavor().longValue());
        assertEquals(3L, response.getEstatisticaEntity().getTotal().longValue());
    }

    @Test
    void testRetornaUmaPautaComEstatisticasSemVotosComputados() {
        when(pautaRepository.findById(any())).thenReturn(Optional.of(PautaEntityStub.pautaEntityRetornoSemVotosComputados()));
        var response = pautaService.buscarPauta(1L);
        assertEquals(0L, response.getEstatisticaEntity().getVotoFavor().longValue());
        assertEquals(0L, response.getEstatisticaEntity().getVotoContra().longValue());
    }

    @Test
    public void testRetornarUmaExceptionCasoNaoEncontreAPauta() {
        when(pautaRepository.findById(any())).thenReturn(Optional.empty());
        var thrown = assertThrows(GenericException.class, () ->
                pautaService.buscarPontuacaoPauta(2L)
        );
        assertEquals("Pauta não encontrada para o id: 2", thrown.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, thrown.getStatus());
    }


}