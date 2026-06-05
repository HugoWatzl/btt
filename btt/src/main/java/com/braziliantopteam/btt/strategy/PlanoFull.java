package com.braziliantopteam.btt.strategy;

public class PlanoFull implements CalculoMensalidadeStrategy {

    @Override
    public Double calcular(Double valorBase, Integer quantidadeModalidades) {
        return 410.0;
    }
}