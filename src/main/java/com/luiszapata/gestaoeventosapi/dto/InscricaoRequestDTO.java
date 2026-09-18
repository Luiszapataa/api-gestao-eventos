package com.luiszapata.gestaoeventosapi.dto;

import jakarta.validation.constraints.NotNull;


public class InscricaoRequestDTO{

    @NotNull(message = "O id do evento é obrigatório")
    private Long eventoId;

    @NotNull(message = "O id do participante é obrigatório")
    private Long participanteId;

    public InscricaoRequestDTO(){

    }

    public Long getEventoId(){
        return eventoId;

    }

    public void setEventoId(Long eventoId){
        this.eventoId = eventoId;

    }

    public Long getParticipanteId(){
        return participanteId;

    }

    public void setParticipanteId(Long participanteId){
        this.participanteId = participanteId;

    }



}