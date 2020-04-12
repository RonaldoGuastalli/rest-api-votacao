package br.com.rjguastalli.v1.sessao.model.request;

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
public class SessaoRequest {

    @NotNull(message = "Informe o identificador da pauta selecionada.")
    @ApiModelProperty(value = "Identificador da pauta.",
            example = "1", required = true)
    private Long pautaId;

    @ApiModelProperty(value = "Situação da sessão referente a pauta escolhida.",
            example = "ATIVA", required = true)
    @Builder.Default
    private String situacao = "ATIVA";

    @ApiModelProperty(value = "Tempo que a sessão de votação ficará aberta.")
    @Builder.Default
    private Long tempoAbertura = 1L;

}
