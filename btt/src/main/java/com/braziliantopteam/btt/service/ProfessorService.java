package com.braziliantopteam.btt.service;

import com.braziliantopteam.btt.dao.ProfessorDao;
import com.braziliantopteam.btt.entity.Professor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorDao professorDao;

    public ProfessorService(ProfessorDao professorDao) {
        this.professorDao = professorDao;
    }

    public Professor salvar(Professor professor) {
        return professorDao.salvar(professor);
    }

    public List<Professor> listarTodos() {
        return professorDao.listarTodos();
    }

    public Professor buscarPorId(Long id) {
        return professorDao.buscarPorId(id);
    }

    public Professor atualizar(Long id, Professor professor) {
        return professorDao.atualizar(id, professor);
    }

    public void deletar(Long id) {
        professorDao.deletar(id);
    }
}