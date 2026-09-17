package com.luiszapata.gestaoeventosapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "inscricoes")
public class Inscricao {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDateTime dataInscricao;


    @NotNull(message = "O evento é obrigatório")
    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;


    @NotNull(message = "O participante é obrigatório")
    @ManyToOne
    @JoinColumn(name = "participante_id", nullable = false)
    private Participante participante;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public LocalDateTime getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDateTime dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Inscricao() {

    }

}