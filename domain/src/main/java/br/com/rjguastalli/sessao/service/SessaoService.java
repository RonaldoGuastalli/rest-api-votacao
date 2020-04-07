package br.com.rjguastalli.sessao.service;

import br.com.rjguastalli.pauta.service.PautaService;
import br.com.rjguastalli.sessao.repository.SessaoRepository;
import br.com.rjguastalli.sessao.model.SessaoModel;
import br.com.rjguastalli.sessao.repository.entity.SessaoEntity;
import br.com.rjguastalli.sessao.enumeration.SituacaoEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class SessaoService {

    private PautaService pautaService;

    private SessaoRepository sessaoRepository;

    public SessaoModel criarNovaSessao(SessaoModel sessaoModel) {
        var pautaEntity = pautaService.buscarPauta(sessaoModel.getPautaId());
        var sessaoEntity = SessaoEntity.builder()
                .situacao(SituacaoEnum.toEnum(sessaoModel.getSituacao()))
                .dataAbertura(LocalDateTime.now())
                .dataTermino(LocalDateTime.now().plusMinutes(sessaoModel.getTempoAbertura()))
                .pautaId(pautaEntity.getId())
                .tempoAbertura(sessaoModel.getTempoAbertura())
                .build();

        SessaoEntity save = sessaoRepository.save(sessaoEntity);

        return SessaoModel.builder()
                .id(save.getId())
                .pautaEntity(pautaEntity)
                .situacao(save.getSituacao().getValue())
                .dataAbertura(save.getDataAbertura())
                .tempoAbertura(save.getTempoAbertura())
                .dataFinalizacao(save.getDataTermino())
                .build();
    }


}
