package br.com.rjguastalli;

import br.com.rjguastalli.pauta.model.PautaModel;
import br.com.rjguastalli.pauta.repository.entity.PautaEntity;
import br.com.rjguastalli.pauta.service.PautaService;
import br.com.rjguastalli.sessao.enumeration.SituacaoEnum;
import br.com.rjguastalli.sessao.repository.entity.SessaoEntity;
import br.com.rjguastalli.sessao.service.SessaoService;
import br.com.rjguastalli.voto.service.VotoAssociadoDomainFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class JulgadorSessao {

    private final PautaService pautaService;
    private final SessaoService sessaoService;
    private final KafkaProducer kafkaProducer;
    private final VotoAssociadoDomainFacade votoAssociadoDomainFacade;

    @Scheduled(fixedDelayString = "${produtor.mensagem.pauta}")
    public void enviarPautaFinalizadaKafka() {
        pautaService.pautas().forEach(pautaEntity -> {
            var votoAssociadoEntities = votoAssociadoDomainFacade
                    .buscarVotoDaSessaoDaPautaEspecifica(pautaEntity.getId());
            votoAssociadoEntities.forEach(votoAssociadoEntity -> {
                var sessaoEntity = votoAssociadoEntity.getSessao();
                validarSessaoPodeMudarSituacao(pautaEntity, sessaoEntity);
            });
        });
    }

    private void validarSessaoPodeMudarSituacao(PautaEntity pautaEntity, SessaoEntity sessaoEntity) {
        if (sessaoAptaParaEncerrar(sessaoEntity)) {
            mudarSituacaoSessao(sessaoEntity);
            var pautaModel = pautaService.buscarPontuacaoPauta(pautaEntity.getId());
            kafkaProducer.send(obterMesagemPadraKafka(pautaModel));
        }
    }

    private void mudarSituacaoSessao(SessaoEntity sessaoEntity) {
        sessaoEntity.setSituacao(SituacaoEnum.ENCERRADA);
        sessaoService.atualizarSessao(sessaoEntity);
    }

    private boolean sessaoAptaParaEncerrar(SessaoEntity sessaoEntity) {
        return SituacaoEnum.ATIVA.equals(sessaoEntity.getSituacao())
                && sessaoEntity.getDataTermino().isBefore(LocalDateTime.now())
                || sessaoEntity.getDataTermino().equals(LocalDateTime.now());
    }

    private KafkaMessage obterMesagemPadraKafka(PautaModel pautaModel) {
        return KafkaMessage.builder()
                .message(pautaModel)
                .build();
    }
}
