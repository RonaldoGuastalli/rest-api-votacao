package br.com.rjguastalli.v1.voto.mapper;

import br.com.rjguastalli.v1.voto.enumeration.VotoAssociadoEnum;
import br.com.rjguastalli.v1.voto.model.request.VotoAssociadoRequest;
import br.com.rjguastalli.voto.model.VotoAssociadoModelInput;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VotoAssociadoMapper {

    public static VotoAssociadoModelInput mapToVotoAssociadoModelInput(VotoAssociadoRequest votoAssociadoRequest) {
        if (Objects.isNull(votoAssociadoRequest)) return null;
        return VotoAssociadoModelInput.builder()
                .cpf(votoAssociadoRequest.getCpfAssociado())
                .voto(VotoAssociadoEnum.toEnum(votoAssociadoRequest.getVoto().toUpperCase()))
                .build();
    }


}
