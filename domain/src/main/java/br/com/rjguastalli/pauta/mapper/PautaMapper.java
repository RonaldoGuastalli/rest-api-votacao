package br.com.rjguastalli.pauta.mapper;

import br.com.rjguastalli.pauta.model.PautaModel;
import br.com.rjguastalli.pauta.repository.entity.PautaEntity;
import br.com.rjguastalli.sessao.model.SessaoModelOut;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class PautaMapper {

    private static final String ZONE_ID = "America/Sao_Paulo";

    public static PautaModel mapToPautaModel(PautaEntity pautaEntity) {
        return PautaModel.builder()
                .id(pautaEntity.getId())
                .descricao(pautaEntity.getDescricao())
                .dataCadastroPauta(pautaEntity.getDataCadastroPauta())
                .build();
    }

    public static PautaEntity mapToPautaModel(PautaModel pautaModel) {
        return PautaEntity.builder()
                .descricao(pautaModel.getDescricao())
                .dataCadastroPauta(LocalDateTime.ofInstant(Instant.now(), ZoneId.of(ZONE_ID)))
                .build();
    }

    public static PautaModel mapToPautaModel(PautaModel pautaModel, List<SessaoModelOut> sessoes) {
        return PautaModel.builder()
                .id(pautaModel.getId())
                .sessoes(sessoes)
                .descricao(pautaModel.getDescricao())
                .dataDesativacao(pautaModel.getDataDesativacao())
                .dataCadastroPauta(pautaModel.getDataCadastroPauta())
                .build();
    }
}
