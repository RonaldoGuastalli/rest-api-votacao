package br.com.rjguastalli.voto;

import br.com.rjguastalli.model.UserInfoEnum;
import br.com.rjguastalli.model.UserInfoResponse;
import br.com.rjguastalli.restclient.UserInfoRestClient;
import br.com.rjguastalli.sessao.repository.entity.SessaoEntity;
import br.com.rjguastalli.sessao.service.SessaoService;
import br.com.rjguastalli.stub.entity.SessaoEntityStub;
import br.com.rjguastalli.stub.model.VotoAssociadoModelStub;
import br.com.rjguastalli.voto.model.VotoAssociadoModel;
import br.com.rjguastalli.voto.repository.VotoAssociadoRepository;
import br.com.rjguastalli.voto.service.VotoAssociadoDomainFacade;
import br.com.rjguastalli.voto.service.VotoAssociadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class VotoAssociadoServiceTest {

    @Mock
    private SessaoService sessaoService;

    @Mock
    private UserInfoRestClient userInfoRestClient;

    @Mock
    private VotoAssociadoRepository votoAssociadoRepository;

    @Mock
    private VotoAssociadoDomainFacade votoAssociadoDomainFacade;

    @InjectMocks
    private VotoAssociadoService votoAssociadoService;

    private SessaoEntity sessaoEntity;
    private UserInfoResponse userInfoResponse;
    private VotoAssociadoModel votoAssociadoModel;

    @BeforeEach
    void setUp() {
        sessaoEntity = SessaoEntityStub.sessaoEntityRetorno();
        UserInfoResponse userInfoResponse = UserInfoResponse.builder().result(UserInfoEnum.ABLE_TO_VOTE).build();
        votoAssociadoModel = VotoAssociadoModelStub.votoAssociadoModelMock();
    }

//    @Test
//    public void testDeveComputarVoto() {
//        sessaoEntity.setDataAbertura(LocalDateTime.now().minusMinutes(10));
//        sessaoEntity.setDataTermino(LocalDateTime.now().plusMinutes(20));
//        VotoAssociadoModel votoAssociadoModel = VotoAssociadoModelStub.votoAssociadoModelMock();
//        when(sessaoService.buscarSessao(any())).thenReturn(sessaoEntity);
//        when(userInfoRestClient.buscarUsuario(any())).thenReturn(userInfoResponse);
//        when(votoAssociadoRepository.existsBySessaoIdAndCpfAssociado(any(), any())).thenReturn(Boolean.FALSE);
//
//
//        assertAll(() -> votoAssociadoDomainFacade.computarVoto(votoAssociadoModel));
//    }


}