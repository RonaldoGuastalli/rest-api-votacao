package br.com.rjguastalli.pauta.service;

import br.com.rjguastalli.GenericException;
import br.com.rjguastalli.pauta.mapper.PautaMapper;
import br.com.rjguastalli.pauta.model.PautaModel;
import br.com.rjguastalli.pauta.repository.PautaRepository;
import br.com.rjguastalli.pauta.repository.entity.PautaEntity;
import br.com.rjguastalli.sessao.repository.entity.SessaoEntity;
import br.com.rjguastalli.sessao.service.SessaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PautaService {

    private PautaRepository pautaRepository;
    private SessaoService sessaoService;


    public PautaModel criarNovaPauta(PautaModel pautaModel) {
        if (pautaModel == null) return null;
        PautaEntity pautaEntity1 = PautaMapper.mapToPautaModel(pautaModel);
        return PautaMapper.mapToPautaModel(pautaRepository.save(pautaEntity1));
    }

    public PautaEntity buscarPauta(Long pautaId) {
        return pautaRepository.findById(pautaId)
                .orElseThrow(() -> new GenericException(
                        "Pauta não encontrada para o id: ".concat(pautaId.toString()), HttpStatus.NOT_FOUND));
    }

    public PautaModel buscarPontuacaoPauta(Long pautaId) {
        var pautaEntity = buscarPauta(pautaId);
        var pautaModel = PautaMapper.mapToPautaModel(pautaEntity);
        var sessoes = sessaoService.sessoes(pautaEntity);
        pautaEntity.setSessoes(sessoes);
        return dadosDaPauta(pautaModel, sessoes);
    }

    private PautaModel dadosDaPauta(PautaModel pautaModel, List<SessaoEntity> sessoes) {
        var sessaoModelOuts = sessaoService.dadosDaSessao(sessoes);
        return PautaMapper.mapToPautaModel(pautaModel, sessaoModelOuts);
    }

}
