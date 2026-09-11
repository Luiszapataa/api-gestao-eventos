package com.luiszapata.gestaoeventosapi.controller;


import com.luiszapata.gestaoeventosapi.model.Inscricao;
import com.luiszapata.gestaoeventosapi.service.InscricaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {

    @Autowired
    private InscricaoService inscricaoService;

    @PostMapping
    public Inscricao inscrever(@RequestBody @Valid Inscricao inscricao){
        return inscricaoService.inscrever(inscricao);
    }

    @GetMapping
    public List<Inscricao> listar() {
        return inscricaoService.listarTodas();
    }

    @GetMapping("/evento/{eventoId}")
    public List<Inscricao> listarPorEvento(@PathVariable Long eventoId) {
        return inscricaoService.listarPorEvento(eventoId);
    }

    @GetMapping("/{id}")
    public Inscricao buscarPorId(@PathVariable Long id) {
        return inscricaoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        inscricaoService.deletar(id);
    }

}

