package br.com.rjguastalli.v1.voto.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VotoAssociadoResponse {

    @ApiModelProperty(value = "Indentificador unico relacionado ao voto.", example = "1")
    private Long id;

    @ApiModelProperty(value = "Indentificador da sessao.", example = "1")
    private Long sessaId;

    @ApiModelProperty(value = "Indentificador da pauta.", example = "1")
    private Long pautaId;

    @ApiModelProperty(value = "CPF do associado que votou.", example = "12345678")
    private String cpfAssociado;

    @ApiModelProperty(value = "Escolha do voto", example = "SIM", allowableValues = "SIM ou NAO")
    private String voto;

}
