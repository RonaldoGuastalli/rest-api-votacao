package br.com.rjguastalli.v1.sessao.stubs;

import br.com.rjguastalli.v1.sessao.model.response.SessaoResponse;

import java.time.LocalDateTime;
import java.time.Month;

public class SessaoResponseStub {

    public static SessaoResponse sessaoResponseMock() {
        return SessaoResponse.builder()
                .id(1L)
                .pauta(1L)
                .dataAbertura(LocalDateTime.of(2020, Month.APRIL, 12, 11, 48, 31, 11111111))
                .dataTermino(LocalDateTime.of(2020, Month.APRIL, 12, 11, 48, 31, 11111111).plusMinutes(1))
                .situacao("ATIVA")
                .duracao(1445L)
                .build();
    }

}
