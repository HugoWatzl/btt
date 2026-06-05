package com.braziliantopteam.btt.strategy;

public class PlanoUmaModalidade implements CalculoMensalidadeStrategy {

    @Override
    public Double calcular(Double valorBase, Integer quantidadeModalidades) {
        return valorBase;
    }
}