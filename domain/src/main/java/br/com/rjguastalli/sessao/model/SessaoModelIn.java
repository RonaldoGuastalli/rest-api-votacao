package br.com.rjguastalli.sessao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessaoModelIn {

    private Long pautaId;
    private String situacao;
    private Long tempoAbertura;

}
