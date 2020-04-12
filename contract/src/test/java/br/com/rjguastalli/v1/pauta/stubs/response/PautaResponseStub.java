package br.com.rjguastalli.v1.pauta.stubs.response;

import br.com.rjguastalli.estatistica.model.EstatisticaModel;
import br.com.rjguastalli.sessao.model.SessaoModelOut;
import br.com.rjguastalli.v1.pauta.model.response.PautaResponse;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

public class PautaResponseStub {

    private static final String ZONE_ID = "America/Sao_Paulo";
    private static Clock clock = Clock.fixed(Instant.parse("2014-12-23T10:15:30.00Z"), ZoneId.of(ZONE_ID));

    public static PautaResponse pautaResponseMock() {
        return PautaResponse.builder()
                .id(1L)
                .descricao("Evento divulgação resultados.")
                .dataCadastroPauta(LocalDateTime.now(clock))
                .sessoes(null)
                .build();
    }

    public static PautaResponse pautaResponseComSessaoMock() {
        return PautaResponse.builder()
                .id(1L)
                .descricao("Evento divulgação resultados.")
                .dataCadastroPauta(LocalDateTime.now(clock))
                .sessoes(Arrays.asList(sessaoModelOutMock()))
                .build();
    }

    public static SessaoModelOut sessaoModelOutMock() {
        return SessaoModelOut.builder()
                .id(1L)
                .pautaId(1L)
                .pautaDescricao("Evento divulgação resultados.")
                .situacao("ATIVA")
                .tempoAbertura(1400L)
                .dataAbertura(LocalDateTime.now(clock))
                .estatistica(estatisticaModelMock())
                .build();
    }

    private static EstatisticaModel  estatisticaModelMock() {
        return EstatisticaModel.builder()
                .votoContra(1L)
                .votoFavor(1L)
                .total(2L)
                .build();

    }
}
