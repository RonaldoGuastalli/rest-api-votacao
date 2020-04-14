package br.com.rjguastalli.voto.mapper;

import br.com.rjguastalli.voto.model.VotoAssociadoModel;
import br.com.rjguastalli.voto.repository.entity.VotoAssociadoEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VotoAssociadoMapper {

    public static VotoAssociadoEntity mapToVotoAssociadoEntity(VotoAssociadoModel votoAssociadoModel) {
        if (ObjectUtils.isEmpty(votoAssociadoModel)) return null;
        return VotoAssociadoEntity.builder()
                .cpfAssociado(votoAssociadoModel.getCpf())
                .voto(votoAssociadoModel.getVoto())
                .build();
    }
}
