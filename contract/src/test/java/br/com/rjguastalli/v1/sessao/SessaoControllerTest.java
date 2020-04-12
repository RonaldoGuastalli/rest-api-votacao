package br.com.rjguastalli.v1.sessao;

import br.com.rjguastalli.config.ResponseParaBodyConfig;
import br.com.rjguastalli.v1.sessao.facade.SessaoContractFacade;
import br.com.rjguastalli.v1.sessao.stubs.SessaoRequestStub;
import br.com.rjguastalli.v1.sessao.stubs.SessaoResponseStub;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SessaoController.class)
@ContextConfiguration(classes = {SessaoController.class})
@AutoConfigureMockMvc
class SessaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessaoContractFacade sessaoContractFacade;

    @Test
    void testDeveCriarUmaSessaoParaPauta() throws Exception {
        when(sessaoContractFacade.criarNovaSessao(any())).thenReturn(SessaoResponseStub.sessaoResponseMock());
        var sessaoResponse = SessaoResponseStub.sessaoResponseMock();

        var dataHoraAbertura = "2020-04-12T11:48:31";
        var dataHoraEncerramento = "2020-04-12T11:49:31";

        this.mockMvc.perform(post("/v1/sessao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ResponseParaBodyConfig.converterParaString(SessaoRequestStub.sessaoRequestMock()))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(sessaoResponse.getId()))
                .andExpect(jsonPath("$.dataAbertura").value(dataHoraAbertura))
                .andExpect(jsonPath("$.dataTermino").value(dataHoraEncerramento))
                .andExpect(jsonPath("$.situacao").value(sessaoResponse.getSituacao()))
                .andExpect(jsonPath("$.duracao").value(sessaoResponse.getDuracao()))
                .andExpect(jsonPath("$.pauta").value(sessaoResponse.getPauta()));
    }

}