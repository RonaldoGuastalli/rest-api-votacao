package br.com.rjguastalli.v1.voto;

import br.com.rjguastalli.v1.voto.stub.request.VotoAssociadoRequestStub;
import br.com.rjguastalli.voto.service.VotoAssociadoDomainFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.rjguastalli.config.ResponseParaBodyConfig.converterParaString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = VotoAssociadoController.class)
@ContextConfiguration(classes = {VotoAssociadoController.class})
@AutoConfigureMockMvc
class VotoAssociadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VotoAssociadoDomainFacade votoAssociadoDomainFacade;

    @Test
    void testDeveCriarVotoNaSessao() throws Exception {
        this.mockMvc.perform(post("/v1/pauta/1/sessao/1/voto-sessao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(converterParaString(VotoAssociadoRequestStub.votoAssociadoRequestMock()))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


}