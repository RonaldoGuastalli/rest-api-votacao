package br.com.rjguastalli.v1.sessao.mapper;

import br.com.rjguastalli.sessao.model.SessaoModel;
import br.com.rjguastalli.v1.pauta.mapper.PautaMapper;
import br.com.rjguastalli.v1.pauta.model.response.PautaResponse;
import br.com.rjguastalli.v1.sessao.model.request.SessaoRequest;
import br.com.rjguastalli.v1.sessao.model.response.SessaoResponse;
import org.springframework.util.ObjectUtils;

public class SessaoMapper {

    public static SessaoModel mapToSessaoModel(SessaoRequest sessaoRequest) {
        if (ObjectUtils.isEmpty(sessaoRequest)) return null;
        return SessaoModel.builder()
                .pautaId(sessaoRequest.getPautaId())
                .situacao(sessaoRequest.getSituacao())
                .tempoAbertura(sessaoRequest.getTempoAbertura())
                .build();
    }

    public static SessaoResponse mapToSessaoResponse(SessaoModel sessaoModel) {
        if (ObjectUtils.isEmpty(sessaoModel)) return null;
        return SessaoResponse.builder()
                .id(sessaoModel.getId())
                .pautaId(sessaoModel.getPautaEntity().getId())
                .pautaResponse(mapToPautaResponse(sessaoModel))
                .situacao(sessaoModel.getSituacao())
                .duracao(sessaoModel.getTempoAbertura())
                .dataAbertura(sessaoModel.getDataAbertura())
//                .dataTermino()
                .build();
    }

    public static PautaResponse mapToPautaResponse(SessaoModel sessaoModel) {
        return PautaResponse.builder()
                .id(sessaoModel.getPautaEntity().getId())
                .dataCadastroPauta(sessaoModel.getPautaEntity().getDataCadastroPauta())
                .descricao(sessaoModel.getPautaEntity().getDescricao())
                .build();

    }
}
