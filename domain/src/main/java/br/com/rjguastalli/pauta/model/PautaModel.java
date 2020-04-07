package br.com.rjguastalli.pauta.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PautaModel {

    private Long id;
    private String descricao;
    private LocalDate dataCadastroPauta;
    private Long votosContra;
    private Long votosFavor;
}
