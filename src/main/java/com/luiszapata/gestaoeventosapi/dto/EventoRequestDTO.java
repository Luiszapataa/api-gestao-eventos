package com.luiszapata.gestaoeventosapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class EventoRequestDTO{

    @NotBlank(message = "O nome do evento é obrigatório")
    private String nome;

    private String descricao;

    @NotNull(message = "A data do evento é obrigatória")
    private LocalDateTime dataEvento;


    @NotBlank(message = "O local do evento é obrigatório")
    private String local;


    @NotNull(message = "A quantidade de vagas é obrigatória")

    @Positive(message = "A quantidade de vagas deve ser maior que zero")
    private Integer vagasTotais;

    public EventoRequestDTO(){

    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }


    public String getDescricao(){
        return descricao;
    }


    public void setDescricao(String descricao){
        this.descricao = descricao;
    }


    public LocalDateTime getDataEvento(){
        return dataEvento;
    }


    public void setDataEvento(LocalDateTime dataEvento){
        this.dataEvento = dataEvento;
    }


    public String getLocal(){
        return local;
    }


    public void setLocal(String local){
        this.local = local;
    }


    public Integer getVagasTotais(){
        return vagasTotais;

    }


    public void setVagasTotais(Integer vagasTotais){
        this.vagasTotais = vagasTotais;

    }




}