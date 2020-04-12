package br.com.rjguastalli.sessao.service;

import br.com.rjguastalli.GenericException;
import br.com.rjguastalli.estatistica.mapper.EstatisticaMapper;
import br.com.rjguastalli.estatistica.service.EstatisticaService;
import br.com.rjguastalli.pauta.repository.PautaRepository;
import br.com.rjguastalli.pauta.repository.entity.PautaEntity;
import br.com.rjguastalli.sessao.mapper.SessaoMapper;
import br.com.rjguastalli.sessao.model.SessaoModelIn;
import br.com.rjguastalli.sessao.model.SessaoModelOut;
import br.com.rjguastalli.sessao.repository.SessaoRepository;
import br.com.rjguastalli.sessao.repository.entity.SessaoEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class SessaoService {

    private SessaoRepository sessaoRepository;
    private PautaRepository pautaRepository;
    private EstatisticaService estatisticaService;

    public SessaoModelOut criarNovaSessao(SessaoModelIn sessaoModelIn) {
        var pautaEntity = buscarPauta(sessaoModelIn.getPautaId());
        var sessaoEntity = SessaoMapper.mapToSessaoEntity(sessaoModelIn, pautaEntity);
        SessaoEntity save = sessaoRepository.save(sessaoEntity);
        return SessaoMapper.mapToSessaoModelOut(save);
    }

    public PautaEntity buscarPauta(Long pautaId) {
        return pautaRepository.findById(pautaId)
                .orElseThrow(() -> new GenericException(
                        "Pauta não encontrada para o id: ".concat(pautaId.toString()), HttpStatus.NOT_FOUND));
    }

    public SessaoEntity buscarSessao(Long sessaoId) {
        var sessaoEntity = sessaoRepository.findById(sessaoId);
        if (!sessaoEntity.isPresent()) {
            throw new GenericException("Sessão não encontrada", HttpStatus.BAD_REQUEST);
        }
        return sessaoEntity.get();
    }

    public List<SessaoEntity> sessoes(PautaEntity pautaEntity) {
        try {
            return sessaoRepository.findAllByPauta(pautaEntity);
        } catch (Exception ex) {
            throw new GenericException("Erro ocorreu ao buscar as sessão relacionada com a pauta de id: "
                    .concat(pautaEntity.getId().toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<SessaoModelOut> dadosDaSessao(List<SessaoEntity> sessoes) {
        return sessoes.stream()
                .map(sessaoEntity -> {
                    var estatisticasVotos = estatisticaService.estatisticasVotos(sessaoEntity);
                    var sessaoModelOut = SessaoMapper.mapToSessaoModelOut(sessaoEntity);
                    sessaoEntity.setEstatisticas(estatisticasVotos);
                    sessaoModelOut.setEstatistica(EstatisticaMapper.mapToEstatisticaModel(estatisticasVotos));
                    return sessaoModelOut;
                }).collect(Collectors.toList());
    }


}
