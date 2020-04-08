package br.com.rjguastalli.sessao.model;

import br.com.rjguastalli.pauta.repository.entity.PautaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessaoModel {

    private Long id;
    private Long pautaId;
    private String situacao;
    private Long tempoAbertura;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFinalizacao;
    private PautaEntity pautaEntity;


}
