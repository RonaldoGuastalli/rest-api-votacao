package br.com.rjguastalli.v1.pauta.mapper;

import br.com.rjguastalli.pauta.model.PautaModel;
import br.com.rjguastalli.v1.pauta.model.request.PautaRequest;
import br.com.rjguastalli.v1.pauta.model.response.PautaResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PautaMapper {

    public static PautaModel mapToPautaModel(PautaRequest pautaRequest) {
        if (ObjectUtils.isEmpty(pautaRequest)) return null;
        return PautaModel.builder()
                .descricao(pautaRequest.getDescricao())
                .build();
    }

    public static PautaResponse mapToPautaResponse(PautaModel pautaModel) {
        if (ObjectUtils.isEmpty(pautaModel)) return null;
        return PautaResponse.builder()
                .id(pautaModel.getId())
                .descricao(pautaModel.getDescricao())
                .dataCadastroPauta(pautaModel.getDataCadastroPauta())
                .sessoes(pautaModel.getSessoes())
                .build();
    }
}
