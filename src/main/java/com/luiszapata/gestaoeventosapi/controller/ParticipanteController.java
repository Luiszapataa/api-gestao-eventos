package com.luiszapata.gestaoeventosapi.controller;

import com.luiszapata.gestaoeventosapi.dto.ParticipanteRequestDTO;
import com.luiszapata.gestaoeventosapi.dto.ParticipanteResponseDTO;
import com.luiszapata.gestaoeventosapi.service.ParticipanteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/participantes")
public class ParticipanteController{

    @Autowired
    private ParticipanteService participanteService;

    @PostMapping
    public ResponseEntity<ParticipanteResponseDTO> criar(@RequestBody @Valid ParticipanteRequestDTO dto){
        ParticipanteResponseDTO criado = participanteService.salvar(dto);
        return new ResponseEntity<>(criado, HttpStatus.CREATED);
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
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        participanteService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}