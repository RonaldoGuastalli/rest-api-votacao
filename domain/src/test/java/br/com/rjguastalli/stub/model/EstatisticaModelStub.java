package br.com.rjguastalli.stub.model;

import br.com.rjguastalli.estatistica.model.EstatisticaModel;

public class EstatisticaModelStub {

    public static EstatisticaModel estatisticaModel() {
        return EstatisticaModel.builder()
                .votoFavor(1L)
                .votoContra(1L)
                .total(2L)
                .build();
    }
}
