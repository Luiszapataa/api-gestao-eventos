package com.luiszapata.gestaoeventosapi.dto;

import java.time.LocalDateTime;

public class EventoResponseDTO {

    private Long id;

    private String nome;

    private String descricao;

    private LocalDateTime dataEvento;

    private String local;

    private Integer vagasTotais;

    public EventoResponseDTO() {


    }

    public EventoResponseDTO(Long id, String nome, String descricao, LocalDateTime dataEvento, String local, Integer vagasTotais){

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataEvento = dataEvento;
        this.local = local;
        this.vagasTotais = vagasTotais;

    }

    public Long getId(){
        return id;

    }

    public void setId(Long id){
        this.id = id;

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