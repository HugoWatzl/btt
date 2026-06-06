package com.braziliantopteam.btt.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Modalidades disponíveis na academia",
        allowableValues = {
                "BOXE",
<<<<<<< HEAD
                "BJJ",
                "MUAYTHAI",
                "BJJ_FEMININO"
=======
                "JIU_JITSU",
                "MUAY_THAI",
                "JIU_JITSU_FEMININO"
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583
        }
)
public enum TipoArteMarcial {
    BOXE,
<<<<<<< HEAD
    MUAYTHAI,
    BJJ,
    BJJ_FEMININO
=======
    JIU_JITSU,
    MUAY_THAI,
    JIU_JITSU_FEMININO
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583
}