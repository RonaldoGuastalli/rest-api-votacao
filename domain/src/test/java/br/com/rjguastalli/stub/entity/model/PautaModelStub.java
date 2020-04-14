package br.com.rjguastalli.stub.entity.model;

import br.com.rjguastalli.pauta.model.PautaModel;

import java.time.LocalDateTime;
import java.util.List;

public class PautaModelStub {


    private static final String DATA_CADASTRO_PAUTA = "2020-04-13T23:57:39.190282";


    public static PautaModel pautaModelRetorno() {
        return PautaModel.builder()
                .id(1L)
                .descricao("Estratégia de pagamento dividendos.")
                .dataCadastroPauta(LocalDateTime.parse(DATA_CADASTRO_PAUTA))
                .dataDesativacao(null)
                .sessoes(List.of(SessaoModelOutStub.SessaoModelOutRetorno()))
                .build();
    }

    public static PautaModel pautaModel() {
        return PautaModel.builder()
                .id(1L)
                .descricao("Estratégia de pagamento dividendos.")
                .dataCadastroPauta(LocalDateTime.parse(DATA_CADASTRO_PAUTA))
                .build();
    }

    public static PautaModel pautaModelRetornoAposSalva() {
        return PautaModel.builder()
                .id(1L)
                .descricao("Estratégia de pagamento dividendos.")
                .dataCadastroPauta(LocalDateTime.parse(DATA_CADASTRO_PAUTA))
                .dataDesativacao(null)
                .sessoes(null)
                .build();
    }
}
