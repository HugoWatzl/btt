package com.braziliantopteam.btt.entity;

import com.braziliantopteam.btt.enums.Sexo;
import com.braziliantopteam.btt.enums.TipoArteMarcial;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    private Long id;
    private String nome;
    private String matricula;
    private String email;
    private String telefone;

    @Schema(
            description = "Sexo do aluno",
            allowableValues = {"MASCULINO", "FEMININO"},
            example = "MASCULINO"
    )
    private Sexo sexo;

    @Schema(
            description = "Modalidade escolhida pelo aluno",
            allowableValues = {
                    "BOXE",
                    "JIU_JITSU",
                    "MUAY_THAI",
                    "JIU_JITSU_FEMININO"
            },
            example = "BOXE"
    )
    private TipoArteMarcial modalidade;
}