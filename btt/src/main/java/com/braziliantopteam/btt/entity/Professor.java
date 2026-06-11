package com.braziliantopteam.btt.entity;

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
public class Professor {

    @Schema(description = "ID gerado automaticamente", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nome completo ", example = "Carlos Silva")
    private String nome;

    @Schema(description = "E-mail ", example = "carlos@btt.com")
    private String email;

    @Schema(description = "Salário", example = "5000.0")
    private Double salario;

    @Schema(
            description = "Modalidades ",
            allowableValues = {"BOXE", "BJJ", "MUAYTHAI", "BJJ_FEMININO"},
            example = "[\"BOXE\", \"BJJ\"]"
    )
    private List<TipoArteMarcial> tiposDeArteMarcial;
}