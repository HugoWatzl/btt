package com.braziliantopteam.btt.service;

import com.braziliantopteam.btt.dao.AlunoDao;
import com.braziliantopteam.btt.entity.Aluno;
import com.braziliantopteam.btt.enums.Sexo;
import com.braziliantopteam.btt.enums.TipoArteMarcial;
import com.braziliantopteam.btt.observer.AlunoSubject;
import com.braziliantopteam.btt.observer.EmailObserver;
import com.braziliantopteam.btt.observer.LogObserver;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoDao alunoDao;
    private final CalculoMensalidadeService calculoMensalidadeService;

    public AlunoService(
            AlunoDao alunoDao,
            CalculoMensalidadeService calculoMensalidadeService
    ) {
        this.alunoDao = alunoDao;
        this.calculoMensalidadeService = calculoMensalidadeService;
    }

    public Aluno salvar(Aluno aluno) {
        validarMatricula(aluno);

        Double mensalidade = calculoMensalidadeService.calcular(
                200.0,
                aluno.getModalidades().size()
        );

        aluno.setMensalidade(mensalidade);

        Aluno alunoSalvo = alunoDao.salvar(aluno);

        AlunoSubject subject = new AlunoSubject();
        subject.adicionarObserver(new LogObserver());
        subject.adicionarObserver(new EmailObserver());
        subject.notificar(alunoSalvo);

        return alunoSalvo;
    }

    public List<Aluno> listarTodos() {
        return alunoDao.listarTodos();
    }

    public Aluno buscarPorId(Long id) {
        return alunoDao.buscarPorId(id);
    }

    public Aluno atualizar(Long id, Aluno aluno) {
        validarMatricula(aluno);

        Double mensalidade = calculoMensalidadeService.calcular(
                200.0,
                aluno.getModalidades().size()
        );

        aluno.setMensalidade(mensalidade);

        return alunoDao.atualizar(id, aluno);
    }

    public void deletar(Long id) {
        alunoDao.deletar(id);
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