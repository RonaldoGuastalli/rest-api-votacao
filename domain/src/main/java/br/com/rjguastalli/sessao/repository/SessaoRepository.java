package br.com.rjguastalli.sessao.repository;

import br.com.rjguastalli.pauta.repository.entity.PautaEntity;
import br.com.rjguastalli.sessao.repository.entity.SessaoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SessaoRepository extends CrudRepository<SessaoEntity, Long> {

    SessaoEntity findByPautaId(Long pautaId);

    List<SessaoEntity> findAllByPauta(PautaEntity pauta);

}
