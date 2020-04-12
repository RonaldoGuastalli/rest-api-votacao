package br.com.rjguastalli.v1.pauta;

import br.com.rjguastalli.v1.pauta.facade.PautaContractFacade;
import br.com.rjguastalli.v1.pauta.stubs.request.PautaRequestStub;
import br.com.rjguastalli.v1.pauta.stubs.response.PautaResponseStub;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PautaController.class)
@ContextConfiguration(classes = {PautaController.class})
@AutoConfigureMockMvc
public class PautaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PautaContractFacade pautaContractFacade;


    @Test
    void deveCriarUmaPauta() throws Exception {
        var pautaResponse = PautaResponseStub.pautaResponseMock();
        when(pautaContractFacade.criarNovaPauta(any())).thenReturn(PautaResponseStub.pautaResponseMock());
        mockMvc.perform(post("/v1/pauta")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(converterParaString(PautaRequestStub.pautaRequestMock())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(pautaResponse.getId()))
                .andExpect(jsonPath("$.descricao").value(pautaResponse.getDescricao()))
                .andExpect(jsonPath("$.dataCadastroPauta").value(pautaResponse.getDataCadastroPauta().toString()))
                .andExpect(jsonPath("$.dataDesativacao").doesNotExist())
                .andExpect(jsonPath("$.sessoes").value(pautaResponse.getSessoes()));
    }

    @Test
    void deveRetornarUmaPautaComPontuacaoAoBuscar() throws Exception {
        when(pautaContractFacade.buscarPontuacaoPauta(any())).thenReturn(PautaResponseStub.pautaResponseComSessaoMock());
        var pautaResponse = PautaResponseStub.pautaResponseComSessaoMock();
        long pautaId = 1L;

        mockMvc.perform(get("/v1/pauta/1/pontuacao".replace("{pautaId}", Long.toString(pautaId))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(pautaResponse.getId()))
                .andExpect(jsonPath("$.descricao").value(pautaResponse.getDescricao()))
                .andExpect(jsonPath("$.dataCadastroPauta").value(pautaResponse.getDataCadastroPauta().toString()))
                .andExpect(jsonPath("$.dataDesativacao").doesNotExist())
                .andExpect(jsonPath("$.sessoes.[0]id").value(pautaResponse.getSessoes().get(0).getId()))
                .andExpect(jsonPath("$.sessoes.[0]pautaId").value(pautaResponse.getSessoes().get(0).getPautaId()))
                .andExpect(jsonPath("$.sessoes.[0]dataAbertura").value(pautaResponse.getSessoes().get(0).getDataAbertura().toString()))
                .andExpect(jsonPath("$.sessoes.[0]tempoAbertura").value(pautaResponse.getSessoes().get(0).getTempoAbertura().toString()))
                .andExpect(jsonPath("$.sessoes.[0]dataFinalizacao").value(pautaResponse.getSessoes().get(0).getDataFinalizacao()))
                .andExpect(jsonPath("$.sessoes.[0]situacao").value(pautaResponse.getSessoes().get(0).getSituacao()));
    }

}