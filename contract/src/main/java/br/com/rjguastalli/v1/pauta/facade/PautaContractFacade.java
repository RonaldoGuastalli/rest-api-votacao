package br.com.rjguastalli.v1.pauta.facade;

import br.com.rjguastalli.pauta.service.PautaService;
import br.com.rjguastalli.v1.pauta.mapper.PautaMapper;
import br.com.rjguastalli.v1.pauta.model.request.PautaRequest;
import br.com.rjguastalli.v1.pauta.model.response.PautaResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class PautaContractFacade {

    private PautaService pautaService;

    public PautaResponse criarNovaPauta(PautaRequest pautaRequest) {
        var model = PautaMapper.mapToPautaModel(pautaRequest);
        var pautaModel = pautaService.criarNovaPauta(model);
        return PautaMapper.mapToPautaResponse(pautaModel);
    }

    public PautaResponse buscarPontuacaoPauta(Long pautaId) {
        var pautaModel = pautaService.buscarPontuacaoPauta(pautaId);
        return PautaMapper.mapToPautaResponse(pautaModel);
    }
}
