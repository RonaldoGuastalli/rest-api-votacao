package br.com.rjguastalli.pauta.service;

import br.com.rjguastalli.pauta.repository.entity.PautaEntity;
import br.com.rjguastalli.pauta.model.PautaModel;
import br.com.rjguastalli.pauta.repository.PautaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class PautaService {

    private PautaRepository pautaRepository;


    public PautaModel criarNovaPauta(PautaModel pautaModel) {
        var pautaEntity = PautaEntity.builder()
                .descricao(pautaModel.getDescricao())
                .build();
        pautaEntity.setDataCadastroPauta(LocalDate.now());

        PautaEntity save = pautaRepository.save(pautaEntity);

        return PautaModel.builder()
                .id(save.getId())
                .descricao(save.getDescricao())
                .dataCadastroPauta(save.getDataCadastroPauta())
                .build();
    }

    public PautaEntity buscarPauta(Long pautaId) {
        return pautaRepository.findById(pautaId)
                .orElseThrow();
    }

    public PautaModel buscarPontuacaoPauta(Long pautaId) {
        PautaEntity pautaEntity = pautaRepository.findById(pautaId)
                .orElseThrow();
        return PautaModel.builder()
                .id(pautaEntity.getId())
                .descricao(pautaEntity.getDescricao())
                .dataCadastroPauta(pautaEntity.getDataCadastroPauta())
                .build();
    }

}
