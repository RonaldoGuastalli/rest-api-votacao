package br.com.rjguastalli.v1.pauta.model.response;

import br.com.rjguastalli.sessao.model.SessaoModelOut;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PautaResponse {

    @ApiModelProperty(value = "indentificador da pauta.", example = "1")
    private Long id;

    @ApiModelProperty(value = "Descrição sobre a pauta votada", example = "Pagamento dividendos anuais.")
    private String descricao;

    @ApiModelProperty(value = "Data de abertura da pauta", example = "2020-04-04")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataCadastroPauta;

    @ApiModelProperty(value = "Data em que finaliza(ou) a votação.", example = "2020-04-15")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataDesativacaoPauta;

    @ApiModelProperty(value = "Dados das sessões vinculados a pauta")
    @Builder.Default
    private List<SessaoModelOut> sessoes = new ArrayList<>();
}
