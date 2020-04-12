package br.com.rjguastalli.v1.sessao.facade;

import br.com.rjguastalli.sessao.service.SessaoService;
import br.com.rjguastalli.v1.sessao.mapper.SessaoMapper;
import br.com.rjguastalli.v1.sessao.model.request.SessaoResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class SessaoContractFacade {

    private SessaoService sessaoService;

    public br.com.rjguastalli.v1.sessao.model.response.SessaoResponse criarNovaSessao(SessaoResponse sessaoRequest) {
        var sessaoModelOut = sessaoService.criarNovaSessao(SessaoMapper.mapToSessaoModel(sessaoRequest));
        return SessaoMapper.mapToSessaoResponse(sessaoModelOut);
    }
}
