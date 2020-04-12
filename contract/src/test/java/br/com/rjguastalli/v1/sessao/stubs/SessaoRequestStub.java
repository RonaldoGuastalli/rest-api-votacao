package br.com.rjguastalli.v1.sessao.stubs;

import br.com.rjguastalli.v1.sessao.model.request.SessaoResponse;

public class SessaoRequestStub {

    public static SessaoResponse sessaoRequestMock() {
        return SessaoResponse.builder()
                .pautaId(1L)
                .situacao("ATIVA")
                .tempoAbertura(1445L)
                .build();
    }
}
