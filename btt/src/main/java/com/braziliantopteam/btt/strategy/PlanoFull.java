package com.braziliantopteam.btt.strategy;

public class PlanoFull implements CalculoMensalidadeStrategy {

    public static final Double VALOR_PLANO_FULL = 450.0;

    @Override
    public Double calcular(Double valorBase, Integer quantidadeModalidades) {
        return VALOR_PLANO_FULL;
    }
}