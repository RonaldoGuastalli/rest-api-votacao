package br.com.rjguastalli.v1.sessao.mapper;

import br.com.rjguastalli.sessao.model.SessaoModelIn;
import br.com.rjguastalli.sessao.model.SessaoModelOut;
import br.com.rjguastalli.v1.sessao.model.request.SessaoResponse;
import org.springframework.util.ObjectUtils;

public class SessaoMapper {

    public static SessaoModelIn mapToSessaoModel(SessaoResponse sessaoRequest) {
        if (ObjectUtils.isEmpty(sessaoRequest)) return null;
        return SessaoModelIn.builder()
                .pautaId(sessaoRequest.getPautaId())
                .situacao(sessaoRequest.getSituacao())
                .tempoAbertura(sessaoRequest.getTempoAbertura())
                .build();
    }

    public static br.com.rjguastalli.v1.sessao.model.response.SessaoResponse mapToSessaoResponse(SessaoModelOut sessaoModel) {
        if (ObjectUtils.isEmpty(sessaoModel)) return null;
        return br.com.rjguastalli.v1.sessao.model.response.SessaoResponse.builder()
                .id(sessaoModel.getId())
                .pauta(sessaoModel.getPautaId())
                .situacao(sessaoModel.getSituacao())
                .duracao(sessaoModel.getTempoAbertura())
                .dataAbertura(sessaoModel.getDataAbertura())
                .dataTermino(sessaoModel.getDataAbertura().plusMinutes(sessaoModel.getTempoAbertura()))
                .build();
    }

}
