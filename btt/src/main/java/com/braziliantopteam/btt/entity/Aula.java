package com.braziliantopteam.btt.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aula {

    @Schema(description = "ID gerado automaticamente", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "ID da modalidade vinculada à aula", example = "1")
    private Long modalidadeId;

    @Schema(description = "Dia(s) da semana da(s) aula(s)", example = "SEGUNDA | QUARTA | SEXTA ")
    private String diaSemana;

    @Schema(description = "Horário da aula", example = "19:00")
    private String horario;
}