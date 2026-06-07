package com.braziliantopteam.btt.observer;

import com.braziliantopteam.btt.entity.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoSubject {

    private final List<AlunoObserver> observers = new ArrayList<>();

    public void adicionarObserver(AlunoObserver observer) {
        observers.add(observer);
    }

    public void notificar(Aluno aluno) {

        for (AlunoObserver observer : observers) {
            observer.atualizar(aluno);
        }
    }
}