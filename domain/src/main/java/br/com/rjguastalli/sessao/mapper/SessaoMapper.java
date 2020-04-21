package br.com.rjguastalli.sessao.mapper;

import br.com.rjguastalli.pauta.repository.entity.PautaEntity;
import br.com.rjguastalli.sessao.enumeration.SituacaoEnum;
import br.com.rjguastalli.sessao.model.SessaoModelIn;
import br.com.rjguastalli.sessao.model.SessaoModelOut;
import br.com.rjguastalli.sessao.repository.entity.SessaoEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SessaoMapper {

    public static SessaoEntity mapToSessaoEntity(SessaoModelIn sessaoModelIn, PautaEntity pautaEntity) {
        return SessaoEntity.builder()
                .situacao(SituacaoEnum.toEnum(sessaoModelIn.getSituacao()))
                .dataAbertura(LocalDateTime.now())
                .dataTermino(LocalDateTime.now().plusMinutes(sessaoModelIn.getTempoAbertura()))
                .pauta(pautaEntity)
                .tempoAbertura(sessaoModelIn.getTempoAbertura())
                .build();
    }

    public static SessaoModelOut mapToSessaoModelOut(SessaoEntity sessaoEntity) {
        return SessaoModelOut.builder()
                .id(sessaoEntity.getId())
                .pautaId(sessaoEntity.getPauta().getId())
                .pautaDescricao(sessaoEntity.getPauta().getDescricao())
                .situacao(sessaoEntity.getSituacao().getValue())
                .tempoAbertura(sessaoEntity.getTempoAbertura())
                .dataFinalizacao(sessaoEntity.getDataTermino())
                .dataAbertura(sessaoEntity.getDataAbertura())
                .build();
    }

}
