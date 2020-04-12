package br.com.rjguastalli.v1.voto.model.request;

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
public class VotoAssociadoRequest {

    @NotNull(message = "Informar o CPF do associado.")
    @ApiModelProperty(value = "CPF do associado.", example = "45678909812")
    private String cpfAssociado;

    @NotNull(message = "Informar o voto")
    @ApiModelProperty(value = "Voto do associado",
            allowableValues = "SIM ou NAO", required = true)
    private String voto;

}
