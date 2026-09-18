package com.luiszapata.gestaoeventosapi.dto;

import java.time.LocalDateTime;

public class InscricaoResponseDTO{

    private Long id;
    private LocalDateTime dataInscricao;
    private EventoResponseDTO evento;
    private ParticipanteResponseDTO participante;

    public InscricaoResponseDTO(){

    }

    public InscricaoResponseDTO(Long id, LocalDateTime dataInscricao, EventoResponseDTO evento, ParticipanteResponseDTO participante) {
        this.id = id;
        this.dataInscricao = dataInscricao;
        this.evento = evento;
        this.participante = participante;

    }

    public Long getId(){
        return id;

    }

    public void setId(Long id){
        this.id = id;

    }

    public LocalDateTime getDataInscricao(){
        return dataInscricao;
    }

    public void setDataInscricao(LocalDateTime dataInscricao){
        this.dataInscricao = dataInscricao;

    }

    public EventoResponseDTO getEvento(){
        return evento;

    }

    public void setEvento(EventoResponseDTO evento){
        this.evento = evento;

    }

    public ParticipanteResponseDTO getParticipante(){
        return participante;

    }

    public void setParticipante(ParticipanteResponseDTO participante){
        this.participante = participante;

    }



}