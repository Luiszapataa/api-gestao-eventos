package com.luiszapata.gestaoeventosapi.controller;

import com.luiszapata.gestaoeventosapi.dto.InscricaoRequestDTO;
import com.luiszapata.gestaoeventosapi.dto.InscricaoResponseDTO;
import com.luiszapata.gestaoeventosapi.service.InscricaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoController{


    @Autowired
    private InscricaoService inscricaoService;

    @PostMapping
    public ResponseEntity<InscricaoResponseDTO> inscrever(@RequestBody @Valid InscricaoRequestDTO dto){
        InscricaoResponseDTO criada = inscricaoService.inscrever(dto);
        return new ResponseEntity<>(criada, HttpStatus.CREATED);
    }

    @GetMapping
    public List<InscricaoResponseDTO> listar(){
        return inscricaoService.listarTodas();
    }

    @GetMapping("/evento/{eventoId}")
    public List<InscricaoResponseDTO> listarPorEvento(@PathVariable Long eventoId){
        return inscricaoService.listarPorEvento(eventoId);
    }

    @GetMapping("/participante/{participanteId}")
    public List<InscricaoResponseDTO> listarPorParticipante(@PathVariable Long participanteId){
        return inscricaoService.listarPorParticipante(participanteId);
    }

    @GetMapping("/{id}")
    public InscricaoResponseDTO buscarPorId(@PathVariable Long id){
        return inscricaoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        inscricaoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}