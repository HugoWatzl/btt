package com.braziliantopteam.btt.service;

import com.braziliantopteam.btt.dao.AlunoDao;
import com.braziliantopteam.btt.dao.ModalidadeDao;
import com.braziliantopteam.btt.entity.Aluno;
import com.braziliantopteam.btt.enums.Sexo;
import com.braziliantopteam.btt.enums.TipoArteMarcial;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoDao alunoDao;
    private final ModalidadeDao modalidadeDao;
    private final CalculoMensalidadeService calculoMensalidadeService;

    public AlunoService(
            AlunoDao alunoDao,
            ModalidadeDao modalidadeDao,
            CalculoMensalidadeService calculoMensalidadeService
    ) {
        this.alunoDao = alunoDao;
        this.modalidadeDao = modalidadeDao;
        this.calculoMensalidadeService = calculoMensalidadeService;
    }

    public Aluno salvar(Aluno aluno) {
        validarMatricula(aluno);

        Double mensalidade = calcularMensalidade(aluno);

        aluno.setMensalidade(mensalidade);

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

        Double mensalidade = calcularMensalidade(aluno);

        aluno.setMensalidade(mensalidade);

        return alunoDao.atualizar(id, aluno);
    }

    public void deletar(Long id) {
        alunoDao.deletar(id);
    }

    private Double calcularMensalidade(Aluno aluno) {
        Double valorTotalModalidades = aluno.getModalidades()
                .stream()
                .mapToDouble(modalidadeDao::buscarValorPorTipo)
                .sum();

        return calculoMensalidadeService.calcular(
                valorTotalModalidades,
                aluno.getModalidades().size()
        );
    }

    private void validarMatricula(Aluno aluno) {
        if (aluno.getSexo() == Sexo.M &&
                aluno.getModalidades().contains(TipoArteMarcial.BJJ_FEMININO)) {

            throw new IllegalArgumentException(
                    "Aluno masculino não pode se matricular no Jiu-Jitsu Feminino."
            );
        }
    }
}