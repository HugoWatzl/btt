package com.braziliantopteam.btt.service;

import com.braziliantopteam.btt.strategy.CalculoMensalidadeStrategy;
import com.braziliantopteam.btt.strategy.PlanoFull;
import com.braziliantopteam.btt.strategy.PlanoUmaModalidade;
import com.braziliantopteam.btt.strategy.PlanoVariasModalidades;
import org.springframework.stereotype.Service;

@Service
public class CalculoMensalidadeService {

    public Double calcular(Double valorBase, Integer quantidadeModalidades) {
        CalculoMensalidadeStrategy strategy;

        if (quantidadeModalidades == 1) {
            strategy = new PlanoUmaModalidade();
        } else if (quantidadeModalidades >= 3) {
            strategy = new PlanoFull();
        } else {
            strategy = new PlanoVariasModalidades();
        }

        return strategy.calcular(valorBase, quantidadeModalidades);
    }
}