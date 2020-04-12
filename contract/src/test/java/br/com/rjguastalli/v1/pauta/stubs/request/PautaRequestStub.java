package br.com.rjguastalli.v1.pauta.stubs.request;

import br.com.rjguastalli.v1.pauta.model.request.PautaRequest;

public class PautaRequestStub {

    public static PautaRequest pautaRequestMock() {
        return PautaRequest.builder()
                .descricao("Pagamento de dividendo aos associados.")
                .build();
    }
}
