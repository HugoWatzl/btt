package com.braziliantopteam.btt.factory;

import com.braziliantopteam.btt.enums.TipoArteMarcial;

public class ModalidadeFactorySelector {

    private ModalidadeFactorySelector() {
    }

    public static ModalidadeFactory selecionar(TipoArteMarcial tipo) {

        return switch (tipo) {

            case BOXE -> new BoxeFactory();

            case BJJ -> new BjjFactory();

            case MUAYTHAI -> new MuayThaiFactory();

            case BJJ_FEMININO -> new BjjFemininoFactory();
        };
    }
}