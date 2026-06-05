package com.braziliantopteam.btt.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Modalidades disponíveis na academia",
        allowableValues = {
                "BOXE",
                "JIU_JITSU",
                "MUAY_THAI",
                "JIU_JITSU_FEMININO"
        }
)
public enum TipoArteMarcial {
    BOXE,
    JIU_JITSU,
    MUAY_THAI,
    JIU_JITSU_FEMININO
}