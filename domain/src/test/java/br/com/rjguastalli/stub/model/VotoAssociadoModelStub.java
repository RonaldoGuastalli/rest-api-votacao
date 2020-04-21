package br.com.rjguastalli.stub.model;

import br.com.rjguastalli.voto.model.VotoAssociadoModel;

public class VotoAssociadoModelStub {

    public static VotoAssociadoModel votoAssociadoModelMock() {
        return VotoAssociadoModel.builder()
                .pautaId(1L)
                .sessaoId(1L)
                .voto(1)
                .cpf("41443405035")
                .build();
    }

}
