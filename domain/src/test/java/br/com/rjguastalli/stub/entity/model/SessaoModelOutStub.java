package br.com.rjguastalli.stub.entity.model;

import br.com.rjguastalli.sessao.enumeration.SituacaoEnum;
import br.com.rjguastalli.sessao.model.SessaoModelOut;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class SessaoModelOutStub {

    private static final String ZONE_ID = "America/Sao_Paulo";

    public static SessaoModelOut SessaoModelOutRetorno() {
        return SessaoModelOut.builder()
                .id(1L)
                .pautaId(1L)
                .situacao("Estratégia de pagamento dividendos.")
                .dataAbertura(LocalDateTime.ofInstant(Instant.now(), ZoneId.of(ZONE_ID)))
                .dataFinalizacao(null)
                .tempoAbertura(1L)
                .situacao(SituacaoEnum.ATIVA.getValue())
                .estatistica(EstatisticaModelStub.estatisticaModel())
                .build();
    }
}
