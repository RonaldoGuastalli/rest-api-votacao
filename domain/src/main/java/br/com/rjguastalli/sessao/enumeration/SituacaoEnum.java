package br.com.rjguastalli.sessao.enumeration;

import org.springframework.util.ObjectUtils;

import java.util.Arrays;

public enum SituacaoEnum {

    ENCERRADA(0, "ENCERRADA"),
    ATIVA(1, "ATIVA");

    private final int cod;
    private final String value;

    SituacaoEnum(int cod, String value) {
        this.cod = cod;
        this.value = value;
    }


    public static SituacaoEnum toEnum(Integer cod) {
        if (ObjectUtils.isEmpty(cod)) return null;
        return Arrays.stream(SituacaoEnum.values())
                .filter(situacaoEnum -> cod == situacaoEnum.getCod())
                .findFirst()
                .orElse(null);
    }

    public static SituacaoEnum toEnum(String value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return Arrays.stream(SituacaoEnum.values())
                .filter(situacaoEnum -> situacaoEnum.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }

    public int getCod() {
        return cod;
    }

    public String getValue() {
        return value;
    }
}
