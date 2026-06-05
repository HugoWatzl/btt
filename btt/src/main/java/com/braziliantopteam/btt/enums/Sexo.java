package com.braziliantopteam.btt.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Sexo do aluno",
        allowableValues = {"MASCULINO", "FEMININO"}
)
public enum Sexo {
    MASCULINO,
    FEMININO
}