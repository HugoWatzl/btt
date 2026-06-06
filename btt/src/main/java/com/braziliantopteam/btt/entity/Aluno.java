package com.braziliantopteam.btt.entity;

<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583
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
<<<<<<< HEAD
    private String email;
    private String telefone;
=======
    private String matricula;
    private String email;
    private String telefone;

    @Schema(
            description = "Sexo do aluno",
            allowableValues = {"MASCULINO", "FEMININO"},
            example = "MASCULINO"
    )
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583
    private Sexo sexo;

    @Schema(
            description = "Modalidade escolhida pelo aluno",
            allowableValues = {
                    "BOXE",
<<<<<<< HEAD
                    "BJJ",
                    "MUAYTHAI",
                    "BJJ_FEMININO"
            },
            example = "[\"BOXE\"]"
    )
    private List<TipoArteMarcial> modalidades;
=======
                    "JIU_JITSU",
                    "MUAY_THAI",
                    "JIU_JITSU_FEMININO"
            },
            example = "BOXE"
    )
    private TipoArteMarcial modalidade;
>>>>>>> 73468666dada2620ef7d05e735fa6a4523bcc583
}