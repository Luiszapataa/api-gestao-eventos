package com.luiszapata.gestaoeventosapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class TratadorDeExcecoes{


    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<MensagemErroDTO> tratarRecursoNaoEncontrado(RecursoNaoEncontradoException ex){
        MensagemErroDTO erro = new MensagemErroDTO(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()

        );

        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(VagasEsgotadasException.class)
    public ResponseEntity<MensagemErroDTO> tratarVagasEsgotadas(VagasEsgotadasException ex){
        MensagemErroDTO erro = new MensagemErroDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage()
        );

        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);

    }



}