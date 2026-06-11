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

    @Schema(
            description = "Identificador da modalidade",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "Tipo da modalidade",
            allowableValues = {
                    "BOXE",
                    "BJJ",
                    "MUAYTHAI",
                    "BJJ_FEMININO"
            },
            example = "BOXE"
    )
    private TipoArteMarcial tipo;

    @Schema(
            description = "Descrição feita no Factory Method",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private String descricao;



    @Schema(
            description = "Valor da mensalidade pelo Factory Method",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Double valorMensalidade;
}