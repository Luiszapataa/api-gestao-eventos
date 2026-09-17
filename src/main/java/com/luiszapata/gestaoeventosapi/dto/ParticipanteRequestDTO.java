package com.luiszapata.gestaoeventosapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ParticipanteRequestDTO{

    @NotBlank(message = "O nome do participante e obrigatório")
    private String nome;


    @NotBlank(message = "O e-mail do participante e obrigatório")
    @Email(message = "E-mail inválido")
    private String email;


    public ParticipanteRequestDTO(){

    }

    public String getNome(){
        return nome;

    }


    public void setNome(String nome){
        this.nome = nome;
    }


    public String getEmail(){
        return email;
    }


    public void setEmail(String email){
        this.email = email;

    }





}