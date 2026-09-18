package com.luiszapata.gestaoeventosapi.controller;

import com.luiszapata.gestaoeventosapi.dto.ParticipanteRequestDTO;
import com.luiszapata.gestaoeventosapi.dto.ParticipanteResponseDTO;
import com.luiszapata.gestaoeventosapi.service.ParticipanteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participantes")
public class ParticipanteController{

    @Autowired
    private ParticipanteService participanteService;

    @PostMapping
    public ParticipanteResponseDTO criar(@RequestBody @Valid ParticipanteRequestDTO dto){
        return participanteService.salvar(dto);
    }


    @GetMapping
    public List<ParticipanteResponseDTO> listar(){
        return participanteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ParticipanteResponseDTO buscarPorId(@PathVariable Long id){
        return participanteService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        participanteService.deletar(id);
    }




}