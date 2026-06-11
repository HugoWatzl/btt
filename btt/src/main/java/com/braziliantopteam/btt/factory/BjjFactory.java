package com.braziliantopteam.btt.factory;

import com.braziliantopteam.btt.entity.Modalidade;
import com.braziliantopteam.btt.enums.TipoArteMarcial;

public class BjjFactory implements ModalidadeFactory {

    @Override
    public Modalidade criar() {

        Modalidade modalidade = new Modalidade();

        modalidade.setTipo(TipoArteMarcial.BJJ);
        modalidade.setDescricao("Arte suave brazilian jiu-jitsu!");
        modalidade.setValorMensalidade(360.0);

        return modalidade;
    }
}