package br.com.rjguastalli.v1.sessao.model.response;

import br.com.rjguastalli.v1.pauta.model.response.PautaResponse;
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

    private Long id;
    private Long pautaId;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataTermino;
    private Integer duracao;
    private String situacao;
    private PautaResponse pautaResponse;

}
