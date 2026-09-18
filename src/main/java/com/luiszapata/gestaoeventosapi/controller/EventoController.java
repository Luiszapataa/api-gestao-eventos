package com.luiszapata.gestaoeventosapi.controller;

import com.luiszapata.gestaoeventosapi.dto.EventoRequestDTO;
import com.luiszapata.gestaoeventosapi.dto.EventoResponseDTO;
import com.luiszapata.gestaoeventosapi.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/eventos")
public class EventoController{

    @Autowired
    private EventoService eventoService;

    @PostMapping
    public ResponseEntity<EventoResponseDTO> criar(@RequestBody @Valid EventoRequestDTO dto){
        EventoResponseDTO criado = eventoService.salvar(dto);
        return new ResponseEntity<>(criado, HttpStatus.CREATED);
    }

    @GetMapping
    public List<EventoResponseDTO> listar(){
        return eventoService.listarTodos();
    }

    @GetMapping("/{id}")
    public EventoResponseDTO buscarPorId(@PathVariable Long id){
        return eventoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public EventoResponseDTO atualizar(@PathVariable Long id, @RequestBody @Valid EventoRequestDTO dto){
        eventoService.deletar(id);
        return eventoService.salvar(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        eventoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}