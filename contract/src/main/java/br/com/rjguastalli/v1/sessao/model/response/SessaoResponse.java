package br.com.rjguastalli.v1.sessao.model.response;

import br.com.rjguastalli.util.Parametro;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessaoResponse {

    @ApiModelProperty(value = "indentificador da sessao.", example = "1")
    private Long id;

    @ApiModelProperty(value = "indentificador da pauda em que a sessao esta vinculada.", example = "1")
    private Long pauta;

    @ApiModelProperty(value = "Data de abertura da sessao", example = "2020-04-04")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = Parametro.ZONE_ID)
    private LocalDateTime dataAbertura;

    @ApiModelProperty(value = "Data em que finaliza(ou) a sessao.", example = "2020-04-15")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = Parametro.ZONE_ID)
    private LocalDateTime dataTermino;

    @ApiModelProperty(value = "Duração da sessão em min", example = "1")
    private Long duracao;

    @ApiModelProperty(value = "Situação em que se encontra a sessão", example = "ATIVA", allowableValues = "ATIVA ou ENCERRADA")
    private String situacao;


}
