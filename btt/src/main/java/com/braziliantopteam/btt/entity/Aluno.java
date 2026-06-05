package com.braziliantopteam.btt.entity;

import java.util.List;
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
    private String email;
    private String telefone;
    private Sexo sexo;

    @Schema(
            description = "Modalidade escolhida pelo aluno",
            allowableValues = {
                    "BOXE",
                    "BJJ",
                    "MUAYTHAI",
                    "BJJ_FEMININO"
            },
            example = "[\"BOXE\"]"
    )
    private List<TipoArteMarcial> modalidades;
}