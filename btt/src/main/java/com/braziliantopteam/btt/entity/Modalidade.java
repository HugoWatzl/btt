package com.braziliantopteam.btt.entity;

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
public class Modalidade {

    private Long id;


    @Schema(
            description = "Modalidade escolhida pelo aluno",
            allowableValues = {
                    "BOXE",
                    "BJJ",
                    "MUAYTHAI",
                    "BJJ_FEMININO"
            },
            example =  "[\"BJJ_FEMININO\"]"
    )
    private TipoArteMarcial tipo;
    private String descricao;
    private String horarioAula;
    private Double valorMensalidade;
}