package br.com.rjguastalli.voto.service;

import br.com.rjguastalli.sessao.service.SessaoService;
import br.com.rjguastalli.voto.model.VotoAssociadoModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class VotoAssociadoDomainFacade {

    private VotoAssociadoService votoAssociadoService;
    private SessaoService sessaoService;

    public void computarVoto(VotoAssociadoModel votoAssociadoModel) {
        var sessaoEntity = sessaoService.buscarSessao(votoAssociadoModel.getSessaoId());
        votoAssociadoService.computarVoto(votoAssociadoModel, sessaoEntity);
    }


}
