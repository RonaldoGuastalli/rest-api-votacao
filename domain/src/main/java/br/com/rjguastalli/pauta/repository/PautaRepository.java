package br.com.rjguastalli.pauta.repository;

import br.com.rjguastalli.pauta.repository.entity.PautaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PautaRepository extends CrudRepository<PautaEntity, Long> {

    List<PautaEntity> findAll();
}
