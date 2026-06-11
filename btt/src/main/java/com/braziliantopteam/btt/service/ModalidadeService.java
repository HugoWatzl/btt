package com.braziliantopteam.btt.service;

import com.braziliantopteam.btt.dao.ModalidadeDao;
import com.braziliantopteam.btt.entity.Modalidade;
import org.springframework.stereotype.Service;

import com.braziliantopteam.btt.factory.ModalidadeFactory;
import com.braziliantopteam.btt.factory.ModalidadeFactorySelector;
import java.util.List;

@Service
public class ModalidadeService {

    private final ModalidadeDao modalidadeDao;

    public ModalidadeService(ModalidadeDao modalidadeDao) {
        this.modalidadeDao = modalidadeDao;
    }

    public Modalidade salvar(Modalidade modalidade) {

        ModalidadeFactory factory =
                ModalidadeFactorySelector.selecionar(
                        modalidade.getTipo()
                );

        Modalidade modalidadePadrao = factory.criar();


        return modalidadeDao.salvar(modalidadePadrao);
    }

    public List<Modalidade> listarTodos() {
        return modalidadeDao.listarTodos();
    }

    public Modalidade buscarPorId(Long id) {
        return modalidadeDao.buscarPorId(id);
    }

    public Modalidade atualizar(Long id, Modalidade modalidade) {
        return modalidadeDao.atualizar(id, modalidade);
    }

    public void deletar(Long id) {
        modalidadeDao.deletar(id);
    }
}