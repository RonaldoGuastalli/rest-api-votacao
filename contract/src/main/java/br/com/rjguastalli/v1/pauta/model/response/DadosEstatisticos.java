package br.com.rjguastalli.v1.pauta.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DadosEstatisticos {

    @ApiModelProperty(value = "Total de votos contra a pauta.", example = "10")
    @Builder.Default
    private Long votosContra = 0L;

    @ApiModelProperty(value = "Total de votos a favor a a pauta.", example = "15")
    @Builder.Default
    private Long votosFavor = 0L;

}
