package br.com.rjguastalli.estatistica.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstatisticaEntity {

    private Long votoContra;
    private Long votoFavor;
    private Long total;

}
