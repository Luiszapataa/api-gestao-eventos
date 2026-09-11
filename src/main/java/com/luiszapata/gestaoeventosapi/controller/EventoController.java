package com.luiszapata.gestaoeventosapi.controller;


import com.luiszapata.gestaoeventosapi.model.Evento;
import com.luiszapata.gestaoeventosapi.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    public Evento criar(@RequestBody @Valid Evento evento){
        return eventoService.salvar(evento);
    }

    @GetMapping
    public List<Evento> listar(){
        return eventoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Evento buscarPorId(@PathVariable Long id) {
        return eventoService.buscarPorId(id);
    }


    @PutMapping("/{id}")
    public Evento atualizar(@PathVariable Long id, @RequestBody @Valid Evento evento) {
        evento.setId(id);
        return eventoService.salvar(evento);
    }


    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        eventoService.deletar(id);
    }


}
