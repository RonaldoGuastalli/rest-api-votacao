package br.com.rjguastalli.sessao.repository.entity;

import br.com.rjguastalli.pauta.repository.entity.PautaEntity;
import br.com.rjguastalli.sessao.enumeration.SituacaoEnum;
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
@Table(name = "sessao")
public class SessaoEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PAUTA_ID")
    private PautaEntity pautaId;

    @Column(name = "DATA_ABERTURA", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataAbertura;

    @Column(name = "DATA_TERMINO", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataTermino;

    @Column(name = "SITUACAO")
    @Enumerated(EnumType.ORDINAL)
    private SituacaoEnum situacao;

    @Column(name = "TEMPO_ABERTURA")
    private Long tempoAbertura;
}
