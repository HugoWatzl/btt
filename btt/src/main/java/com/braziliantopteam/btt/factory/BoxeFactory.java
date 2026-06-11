package com.braziliantopteam.btt.factory;

import com.braziliantopteam.btt.entity.Modalidade;
import com.braziliantopteam.btt.enums.TipoArteMarcial;

public class BoxeFactory implements ModalidadeFactory {

    @Override
    public Modalidade criar() {

        Modalidade modalidade = new Modalidade();

        modalidade.setTipo(TipoArteMarcial.BOXE);
        modalidade.setDescricao("Nobre arte ");
        modalidade.setValorMensalidade(210.0);

        return modalidade;
    }
}