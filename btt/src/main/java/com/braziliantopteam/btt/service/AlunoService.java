package com.braziliantopteam.btt.service;

import com.braziliantopteam.btt.dao.AlunoDao;
import com.braziliantopteam.btt.entity.Aluno;
import com.braziliantopteam.btt.enums.Sexo;
import com.braziliantopteam.btt.enums.TipoArteMarcial;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoDao alunoDao;

    public AlunoService(AlunoDao alunoDao) {
        this.alunoDao = alunoDao;
    }

    public Aluno salvar(Aluno aluno) {
        validarMatricula(aluno);
        return alunoDao.salvar(aluno);
    }

    public List<Aluno> listarTodos() {
        return alunoDao.listarTodos();
    }

    public Aluno buscarPorId(Long id) {
        return alunoDao.buscarPorId(id);
    }

    public Aluno atualizar(Long id, Aluno aluno) {
        validarMatricula(aluno);
        return alunoDao.atualizar(id, aluno);
    }

    public void deletar(Long id) {
        alunoDao.deletar(id);
    }

    private void validarMatricula(Aluno aluno) {
        if (aluno.getSexo() == Sexo.MASCULINO &&
                aluno.getModalidade() == TipoArteMarcial.JIU_JITSU_FEMININO) {
            throw new IllegalArgumentException(
                    "Aluno masculino não pode se matricular no Jiu-Jitsu Feminino."
            );
        }
    }
}