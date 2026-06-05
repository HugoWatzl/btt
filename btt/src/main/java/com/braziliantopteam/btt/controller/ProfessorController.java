package com.braziliantopteam.btt.controller;

import com.braziliantopteam.btt.entity.Professor;
import com.braziliantopteam.btt.service.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public Professor salvar(@RequestBody Professor professor) {
        return professorService.salvar(professor);
    }

    @GetMapping
    public List<Professor> listarTodos() {
        return professorService.listarTodos();
    }

    @GetMapping("/{id}")
    public Professor buscarPorId(@PathVariable Long id) {
        return professorService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Professor atualizar(@PathVariable Long id, @RequestBody Professor professor) {
        return professorService.atualizar(id, professor);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        professorService.deletar(id);
    }
}