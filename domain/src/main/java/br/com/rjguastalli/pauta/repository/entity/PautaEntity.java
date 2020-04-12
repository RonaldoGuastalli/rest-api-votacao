package br.com.rjguastalli.pauta.repository.entity;

import br.com.rjguastalli.estatistica.entity.EstatisticaEntity;
import br.com.rjguastalli.sessao.repository.entity.SessaoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PAUTA")
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

    @Transient
    private EstatisticaEntity estatisticaEntity;

    @Transient
    @Builder.Default
    private List<SessaoEntity> sessoes = new ArrayList<>();
}
