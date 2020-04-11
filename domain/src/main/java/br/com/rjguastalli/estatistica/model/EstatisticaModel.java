package br.com.rjguastalli.estatistica.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstatisticaModel {

    private Long votoContra;
    private Long votoFavor;
    private Long total;

}
