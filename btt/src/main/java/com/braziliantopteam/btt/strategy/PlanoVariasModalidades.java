package com.braziliantopteam.btt.strategy;

public class PlanoVariasModalidades implements CalculoMensalidadeStrategy {

    @Override
    public Double calcular(Double valorBase, Integer quantidadeModalidades) {
        return valorBase * quantidadeModalidades * 0.8;                                              //20% de desconto
    }
}