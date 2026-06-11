package com.braziliantopteam.btt.factory;

import com.braziliantopteam.btt.entity.Modalidade;
import com.braziliantopteam.btt.enums.TipoArteMarcial;

public class MuayThaiFactory implements ModalidadeFactory {

    @Override
    public Modalidade criar() {

        Modalidade modalidade = new Modalidade();

        modalidade.setTipo(TipoArteMarcial.MUAYTHAI);
        modalidade.setDescricao("Luta das 8 armas");
        modalidade.setValorMensalidade(200.0);

        return modalidade;
    }
}