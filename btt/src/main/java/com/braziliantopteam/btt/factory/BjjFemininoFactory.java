package com.braziliantopteam.btt.factory;

import com.braziliantopteam.btt.entity.Modalidade;
import com.braziliantopteam.btt.enums.TipoArteMarcial;

public class BjjFemininoFactory implements ModalidadeFactory {

    @Override
    public Modalidade criar() {

        Modalidade modalidade = new Modalidade();

        modalidade.setTipo(TipoArteMarcial.BJJ_FEMININO);
        modalidade.setDescricao("Aulas de Jiu-Jitsu apenas para mulheres!");
        modalidade.setValorMensalidade(319.99);

        return modalidade;
    }
}