package br.com.rjguastalli.pauta.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pauta")
public class PautaEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "DATA_CADASTRO", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataCadastroPauta;

    @Column(name = "DATA_DESATIVACAO", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataDesativacao;
}
