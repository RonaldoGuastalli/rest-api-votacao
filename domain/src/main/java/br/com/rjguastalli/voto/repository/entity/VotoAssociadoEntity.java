package br.com.rjguastalli.voto.repository.entity;


import br.com.rjguastalli.sessao.repository.entity.SessaoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "voto_associado")
public class VotoAssociadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_SESSAO")
    private SessaoEntity sessao;

    @Column(name = "CPF_ASSOCIADO")
    private String cpfAssociado;

    @Column(name = "VOTO")
    private Integer voto;
}
