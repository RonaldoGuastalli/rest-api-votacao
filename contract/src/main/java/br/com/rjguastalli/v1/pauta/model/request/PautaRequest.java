package br.com.rjguastalli.v1.pauta.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PautaRequest {

    @NotNull(message = "Uma descricao para a pauta é necessário.")
    @ApiModelProperty(value = "Descrição da pauta.",
            example = "proposta de pagamento de dividendos semestralmente.",
            required = true)
    private String descricao;

    @Builder.Default
    private Long votosContra = 0L;

    @Builder.Default
    private Long votosFavor = 0L;
}
