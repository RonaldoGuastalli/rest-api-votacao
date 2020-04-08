package br.com.rjguastalli.voto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoAssociadoModelOutput {

    private Long id;
    private Long sessaoId;
    private Long pautaId;
    private String cpf;
    private Integer voto;

}
