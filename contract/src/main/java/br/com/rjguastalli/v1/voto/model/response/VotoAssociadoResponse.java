package br.com.rjguastalli.v1.voto.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VotoAssociadoResponse {

    private Long id;
    private Long sessaId;
    private Long pautaId;
    private String cpfAssociado;
    private String voto;

}
