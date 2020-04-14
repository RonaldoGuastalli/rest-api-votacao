package br.com.rjguastalli.stub.entity;

import br.com.rjguastalli.estatistica.entity.EstatisticaEntity;

public class EstatisticaEntityStub {

    public static EstatisticaEntity estatisticaEntityRetorno() {
        return EstatisticaEntity.builder()
                .votoContra(1L)
                .votoFavor(0L)
                .total(3L)
                .build();
    }

    public static EstatisticaEntity estatisticaEntityRetornoSemVotosComputados() {
        return EstatisticaEntity.builder()
                .votoContra(0L)
                .votoFavor(0L)
                .total(0L)
                .build();
    }
}
