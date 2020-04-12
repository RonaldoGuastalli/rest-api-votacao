package br.com.rjguastalli.voto.mapper;

import br.com.rjguastalli.voto.model.VotoAssociadoModelInput;
import br.com.rjguastalli.voto.repository.entity.VotoAssociadoEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VotoAssociadoMapper {

    public static VotoAssociadoEntity mapToVotoAssociadoEntity(VotoAssociadoModelInput votoAssociadoModelInput) {
        if (ObjectUtils.isEmpty(votoAssociadoModelInput)) return null;
        return VotoAssociadoEntity.builder()
                .cpfAssociado(votoAssociadoModelInput.getCpf())
                .voto(votoAssociadoModelInput.getVoto())
                .build();
    }
}
