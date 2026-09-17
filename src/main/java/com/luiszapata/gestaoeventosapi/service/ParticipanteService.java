package com.luiszapata.gestaoeventosapi.service;

import com.luiszapata.gestaoeventosapi.model.Participante;
import com.luiszapata.gestaoeventosapi.repository.ParticipanteRepository;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ParticipanteService {


    @Autowired
    private ParticipanteRepository participanteRepository;

    public Participante salvar(Participante participante) {
        return participanteRepository.save(participante);
    }


    public List<Participante> listarTodos() {
        return participanteRepository.findAll();
    }


    public Participante buscarPorId(Long id) {
        Optional<Participante> participanteOptional = participanteRepository.findById(id);

        if (participanteOptional.isPresent()) {
            return participanteOptional.get();
        } else {
            throw new RuntimeException("Participante não encontrado com id: " + id);
        }
    }



    public void deletar(Long id) {
        participanteRepository.deleteById(id);
    }

}