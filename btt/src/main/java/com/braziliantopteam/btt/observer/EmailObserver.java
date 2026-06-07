package com.braziliantopteam.btt.observer;

import com.braziliantopteam.btt.entity.Aluno;

public class EmailObserver implements AlunoObserver {

    @Override
    public void atualizar(Aluno aluno) {
        System.out.println(
                "EMAIL: Bem-vindo "
                        + aluno.getNome()
                        + "!"
        );
    }
}