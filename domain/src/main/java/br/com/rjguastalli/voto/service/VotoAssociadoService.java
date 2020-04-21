package br.com.rjguastalli.voto.service;

import br.com.rjguastalli.sessao.repository.entity.SessaoEntity;
import br.com.rjguastalli.voto.mapper.VotoAssociadoMapper;
import br.com.rjguastalli.voto.model.VotoAssociadoModel;
import br.com.rjguastalli.voto.repository.VotoAssociadoRepository;
import br.com.rjguastalli.voto.repository.entity.VotoAssociadoEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class VotoAssociadoService {

    private VotoAssociadoRepository votoAssociadoRepository;

    protected void computarVoto(VotoAssociadoModel votoAssociadoModel, SessaoEntity sessaoEntity) {
        var votoAssociadoEntity = VotoAssociadoMapper.mapToVotoAssociadoEntity(votoAssociadoModel);
        salvarVotoAssociado(votoAssociadoEntity, sessaoEntity);
    }

    private void salvarVotoAssociado(VotoAssociadoEntity votoAssociadoEntity, SessaoEntity sessaoEntity) {
        if (!Objects.isNull(votoAssociadoEntity)) {
            votoAssociadoEntity.setSessao(sessaoEntity);
            votoAssociadoRepository.save(votoAssociadoEntity);
        }
    }

    protected List<VotoAssociadoEntity> buscarVotosDaSessaoParaAPautaEspecifica(Long sessaoId) {
        return votoAssociadoRepository.findBySessaoId(sessaoId);
    }

}
