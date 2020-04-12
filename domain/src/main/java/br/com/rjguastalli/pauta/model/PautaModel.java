package br.com.rjguastalli.pauta.model;

import br.com.rjguastalli.sessao.model.SessaoModelOut;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PautaModel {

    private Long id;
    private String descricao;
    private LocalDateTime dataCadastroPauta;
    private LocalDateTime dataDesativacao;
    private List<SessaoModelOut> sessoes;
}
