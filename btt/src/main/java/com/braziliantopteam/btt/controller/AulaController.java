package com.braziliantopteam.btt.controller;

import com.braziliantopteam.btt.entity.Aula;
import com.braziliantopteam.btt.service.AulaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aulas")
public class AulaController {

    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @PostMapping
    public Aula salvar(@RequestBody Aula aula) {
        return aulaService.salvar(aula);
    }

    @GetMapping
    public List<Aula> listarTodos() {
        return aulaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Aula buscarPorId(@PathVariable Long id) {
        return aulaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Aula atualizar(@PathVariable Long id, @RequestBody Aula aula) {
        return aulaService.atualizar(id, aula);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        aulaService.deletar(id);
    }
}