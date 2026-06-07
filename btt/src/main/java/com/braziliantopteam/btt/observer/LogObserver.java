package com.braziliantopteam.btt.observer;

import com.braziliantopteam.btt.entity.Aluno;

public class LogObserver implements AlunoObserver {

    @Override
    public void atualizar(Aluno aluno) {
        System.out.println(
                "LOG: Novo aluno cadastrado -> "
                        + aluno.getNome()
        );
    }
}