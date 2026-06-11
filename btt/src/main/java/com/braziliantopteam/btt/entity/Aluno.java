package com.braziliantopteam.btt.entity;

import com.braziliantopteam.btt.enums.Sexo;
import com.braziliantopteam.btt.enums.TipoArteMarcial;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Schema(description = "Matrícula gferada automaticamente", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nome completo", example = "Bruno Mendes")
    private String nome;

    @Schema(description = "E-mail", example = "bruno@email.com")
    private String email;

    @Schema(description = "Telefone", example = "21999999999")
    private String telefone;

    @Schema(description = "Sexo", allowableValues = {"M", "F"}, example = "F")
    private Sexo sexo;

    @Schema(
            description = "Modalidades escolhidas pelo aluno",
            allowableValues = {"BOXE", "BJJ", "MUAYTHAI", "BJJ_FEMININO"},
            example = "[\"BOXE\", \"BJJ_FEMININO\"]"
    )
    private List<TipoArteMarcial> modalidades;

    @Schema(description = "Mensalidade calculada automaticamente", accessMode = Schema.AccessMode.READ_ONLY)
    private Double mensalidade;
}