package br.com.rjguastalli.sessao.service;

import br.com.rjguastalli.GenericException;
import br.com.rjguastalli.pauta.service.PautaService;
import br.com.rjguastalli.sessao.enumeration.SituacaoEnum;
import br.com.rjguastalli.sessao.model.SessaoModel;
import br.com.rjguastalli.sessao.repository.SessaoRepository;
import br.com.rjguastalli.sessao.repository.entity.SessaoEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
                .pautaId(pautaEntity)
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

    public SessaoEntity buscarSessao(Long sessaoId) {
        var sessaoEntity = sessaoRepository.findById(sessaoId);
        if (!sessaoEntity.isPresent()) {
            throw new GenericException("Sessão não encontrada", HttpStatus.BAD_REQUEST);
        }
        return sessaoEntity.get();
    }


}
