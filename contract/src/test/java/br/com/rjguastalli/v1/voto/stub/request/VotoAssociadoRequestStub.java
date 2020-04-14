package br.com.rjguastalli.v1.voto.stub.request;

import br.com.rjguastalli.v1.voto.model.request.VotoAssociadoRequest;

public class VotoAssociadoRequestStub {

    public static VotoAssociadoRequest votoAssociadoRequestMock() {
        return VotoAssociadoRequest.builder()
                .cpfAssociado("96667891086")
                .voto("SIM")
                .build();
    }
}
