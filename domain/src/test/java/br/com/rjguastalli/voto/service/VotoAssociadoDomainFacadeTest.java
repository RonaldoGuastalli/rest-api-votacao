package br.com.rjguastalli.voto.service;

import br.com.rjguastalli.sessao.repository.entity.SessaoEntity;
import br.com.rjguastalli.sessao.service.SessaoService;
import br.com.rjguastalli.stub.entity.SessaoEntityStub;
import br.com.rjguastalli.stub.entity.VotoAssociadoEntityStub;
import br.com.rjguastalli.stub.model.VotoAssociadoModelStub;
import br.com.rjguastalli.voto.model.VotoAssociadoModel;
import br.com.rjguastalli.voto.repository.VotoAssociadoRepository;
import br.com.rjguastalli.voto.repository.entity.VotoAssociadoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class VotoAssociadoDomainFacadeTest {

    @Mock
    private SessaoService sessaoService;

    @Mock
    private VotoAssociadoService votoAssociadoService;

    @Mock
    private VotoAssociadoRepository votoAssociadoRepository;


    @Mock
    private RegraVotoAssociadoService regraVotoAssociadoService;

    @InjectMocks
    private VotoAssociadoDomainFacade votoAssociadoDomainFacade;


    private SessaoEntity sessaoEntity;
    private VotoAssociadoModel votoAssociadoModel;

    @BeforeEach
    void setUp() {
        sessaoEntity = SessaoEntityStub.sessaoEntityRetorno();
        votoAssociadoModel = VotoAssociadoModelStub.votoAssociadoModelMock();
    }

    @Test
    public void testDeveComputarVoto() {
        when(sessaoService.buscarSessao(any())).thenReturn(sessaoEntity);
        when(votoAssociadoRepository.findBySessaoId(anyLong())).thenReturn(List.of(VotoAssociadoEntityStub.votoAssociadoEntityMock()));
        when(votoAssociadoRepository.save(any())).thenReturn(VotoAssociadoEntityStub.votoAssociadoEntityMock());
        votoAssociadoDomainFacade.computarVoto(votoAssociadoModel);
        verify(votoAssociadoService, times(1)).computarVoto(votoAssociadoModel, sessaoEntity);
    }

    @Test
    public void testBuscarOsVotosDaSessaoDeUmaPautaEspecifica() {
        when(votoAssociadoService.buscarVotosDaSessaoParaAPautaEspecifica(any())).thenReturn(List.of(VotoAssociadoEntityStub.votoAssociadoEntityMock()));
        List<VotoAssociadoEntity> votoAssociadoEntities = votoAssociadoDomainFacade.buscarVotoDaSessaoDaPautaEspecifica(1L);
        assertAll(
                () -> assertEquals(List.of(VotoAssociadoEntityStub.votoAssociadoEntityMock()), votoAssociadoEntities),
                () -> assertEquals("96667891086", votoAssociadoEntities.get(0).getCpfAssociado()),
                () -> assertEquals(5L, votoAssociadoEntities.get(0).getSessao().getId().longValue())
        );
    }


}