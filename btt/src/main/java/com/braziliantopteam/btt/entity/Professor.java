package com.braziliantopteam.btt.entity;

<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583
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

<<<<<<< HEAD

    @Schema(
            description = "Modalidade escolhida pelo aluno",
            allowableValues = {
                    "BOXE",
                    "BJJ",
                    "MUAYTHAI",
                    "BJJ_FEMININO"
            },
            example = "[\"MUAYTHAI\"]"
    )
    private List<TipoArteMarcial> tiposDeArteMarcial;
=======
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
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583
}