package br.com.rjguastalli.sessao.repository.entity;

import br.com.rjguastalli.estatistica.entity.EstatisticaEntity;
import br.com.rjguastalli.pauta.repository.entity.PautaEntity;
import br.com.rjguastalli.sessao.enumeration.SituacaoEnum;
import br.com.rjguastalli.voto.repository.entity.VotoAssociadoEntity;
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
@Table(name = "sessao")
public class SessaoEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PAUTA_ID")
    private PautaEntity pauta;

    @Column(name = "DATA_ABERTURA", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataAbertura;

    @Column(name = "DATA_TERMINO", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataTermino;

    @Column(name = "SITUACAO")
    @Enumerated(EnumType.ORDINAL)
    private SituacaoEnum situacao;

    @Column(name = "TEMPO_ABERTURA")
    private Long tempoAbertura;

    @Builder.Default
    @Transient
    private List<VotoAssociadoEntity> votos = new ArrayList<>();

    @Builder.Default
    @Transient
    private EstatisticaEntity estatisticas = new EstatisticaEntity();
}
