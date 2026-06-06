package com.braziliantopteam.btt.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Modalidades disponíveis na academia",
        allowableValues = {
                "BOXE",
                "BJJ",
                "MUAYTHAI",
                "BJJ_FEMININO"
        }
)
public enum TipoArteMarcial {
    BOXE,
    MUAYTHAI,
    BJJ,
    BJJ_FEMININO
}