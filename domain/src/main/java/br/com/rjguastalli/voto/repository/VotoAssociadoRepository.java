package br.com.rjguastalli.voto.repository;

import br.com.rjguastalli.voto.repository.entity.VotoAssociadoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VotoAssociadoRepository extends CrudRepository<VotoAssociadoEntity, Long> {

    Boolean existsBySessaoIdAndCpfAssociado(Long sessaoId, String cpfAssociado);

    List<VotoAssociadoEntity> findBySessaoId(Long sessaoId);

}
