package com.braziliantopteam.btt.service;

import com.braziliantopteam.btt.dao.AulaDao;
import com.braziliantopteam.btt.entity.Aula;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AulaService {

    private final AulaDao aulaDao;

    public AulaService(AulaDao aulaDao) {
        this.aulaDao = aulaDao;
    }

    public Aula salvar(Aula aula) {
        return aulaDao.salvar(aula);
    }

    public List<Aula> listarTodos() {
        return aulaDao.listarTodos();
    }

    public Aula buscarPorId(Long id) {
        return aulaDao.buscarPorId(id);
    }

    public Aula atualizar(Long id, Aula aula) {
        return aulaDao.atualizar(id, aula);
    }

    public void deletar(Long id) {
        aulaDao.deletar(id);
    }
}