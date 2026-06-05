package com.braziliantopteam.btt.controller;

import com.braziliantopteam.btt.entity.Modalidade;
import com.braziliantopteam.btt.service.ModalidadeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modalidades")
public class ModalidadeController {

    private final ModalidadeService modalidadeService;

    public ModalidadeController(ModalidadeService modalidadeService) {
        this.modalidadeService = modalidadeService;
    }

    @PostMapping
    public Modalidade salvar(@RequestBody Modalidade modalidade) {
        return modalidadeService.salvar(modalidade);
    }

    @GetMapping
    public List<Modalidade> listarTodos() {
        return modalidadeService.listarTodos();
    }

    @GetMapping("/{id}")
    public Modalidade buscarPorId(@PathVariable Long id) {
        return modalidadeService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Modalidade atualizar(@PathVariable Long id, @RequestBody Modalidade modalidade) {
        return modalidadeService.atualizar(id, modalidade);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        modalidadeService.deletar(id);
    }
}