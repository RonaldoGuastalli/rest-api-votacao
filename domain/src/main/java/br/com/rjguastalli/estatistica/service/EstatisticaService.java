package br.com.rjguastalli.estatistica.service;


import br.com.rjguastalli.estatistica.entity.EstatisticaEntity;
import br.com.rjguastalli.sessao.repository.entity.SessaoEntity;
import br.com.rjguastalli.voto.repository.VotoAssociadoRepository;
import br.com.rjguastalli.voto.repository.entity.VotoAssociadoEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EstatisticaService {

    private static final int VOTO_SIM = 1;
    private static final int VOTO_NAO = 0;

    private VotoAssociadoRepository votoAssociadoRepository;

    public EstatisticaEntity estatisticasVotos(SessaoEntity sessaoEntity) {
        List<VotoAssociadoEntity> votos = votoAssociadoRepository.findBySessaoId(sessaoEntity.getId());
        return EstatisticaEntity.builder()
                .votoFavor(votos.stream().filter(votoAssociadoEntity -> votoAssociadoEntity.getVoto() == VOTO_SIM).count())
                .votoContra(votos.stream().filter(votoAssociadoEntity -> votoAssociadoEntity.getVoto() == VOTO_NAO).count())
                .total((long) votos.size())
                .build();
    }

}
