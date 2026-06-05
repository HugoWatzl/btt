package com.braziliantopteam.btt.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Sexo do aluno",
        allowableValues = {"M", "F"}
)
public enum Sexo {
    M,
    F
}