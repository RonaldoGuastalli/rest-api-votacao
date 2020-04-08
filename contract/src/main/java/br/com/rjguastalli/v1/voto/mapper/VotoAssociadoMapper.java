package br.com.rjguastalli.v1.voto.mapper;

import br.com.rjguastalli.v1.voto.VotoAssociadoEnum;
import br.com.rjguastalli.v1.voto.model.request.VotoAssociadoRequest;
import br.com.rjguastalli.v1.voto.model.response.VotoAssociadoResponse;
import br.com.rjguastalli.voto.model.VotoAssociadoModelInput;
import br.com.rjguastalli.voto.model.VotoAssociadoModelOutput;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VotoAssociadoMapper {

    public static VotoAssociadoModelInput mapToVotoAssociadoModelInput(VotoAssociadoRequest votoAssociadoRequest) {
        return VotoAssociadoModelInput.builder()
                .cpf(votoAssociadoRequest.getCpfAssociado())
                .voto(votoAssociadoRequest.getVoto())
                .build();
    }

    public static VotoAssociadoResponse mapToVotoAssociadoModelInput(VotoAssociadoModelOutput votoAssociadoModelOutput) {
        return VotoAssociadoResponse.builder()
                .id(votoAssociadoModelOutput.getId())
                .cpfAssociado(votoAssociadoModelOutput.getCpf())
                .pautaId(votoAssociadoModelOutput.getPautaId())
                .sessaId(votoAssociadoModelOutput.getSessaoId())
                .voto(VotoAssociadoEnum.toEnum(votoAssociadoModelOutput.getVoto()))
                .build();
    }

}
