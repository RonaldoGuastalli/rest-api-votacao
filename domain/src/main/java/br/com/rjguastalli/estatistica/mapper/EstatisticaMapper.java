package br.com.rjguastalli.estatistica.mapper;

import br.com.rjguastalli.estatistica.entity.EstatisticaEntity;
import br.com.rjguastalli.estatistica.model.EstatisticaModel;

public class EstatisticaMapper {

    public static EstatisticaModel mapToEstatisticaModel(EstatisticaEntity estatisticaEntity) {
        return EstatisticaModel.builder()
                .total(estatisticaEntity.getTotal())
                .votoContra(estatisticaEntity.getVotoContra())
                .votoFavor(estatisticaEntity.getVotoFavor())
                .build();
    }
}
