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
public class Professor {

    private Long id;
    private String nome;
    private String email;
    private Double salario;

    @Schema(
            description = "Arte marcial que o professor ensina",
            allowableValues = {
                    "BOXE",
                    "JIU_JITSU",
                    "MUAY_THAI",
                    "JIU_JITSU_FEMININO"
            },
            example = "JIU_JITSU"
    )
    private TipoArteMarcial tipoDeArteMarcial;
}