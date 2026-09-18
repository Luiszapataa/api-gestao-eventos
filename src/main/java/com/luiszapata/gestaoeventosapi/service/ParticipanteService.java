package com.luiszapata.gestaoeventosapi.service;

import com.luiszapata.gestaoeventosapi.dto.ParticipanteRequestDTO;
import com.luiszapata.gestaoeventosapi.dto.ParticipanteResponseDTO;
import com.luiszapata.gestaoeventosapi.exception.RecursoNaoEncontradoException;
import com.luiszapata.gestaoeventosapi.model.Participante;
import com.luiszapata.gestaoeventosapi.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteService{


    @Autowired
    private ParticipanteRepository participanteRepository;

    public ParticipanteResponseDTO salvar(ParticipanteRequestDTO dto){

        Participante participante = new Participante();
        participante.setNome(dto.getNome());
        participante.setEmail(dto.getEmail());

        Participante salvo = participanteRepository.save(participante);

        return converterParaResponseDTO(salvo);

    }

    public List<ParticipanteResponseDTO> listarTodos(){
        List<Participante> participantes = participanteRepository.findAll();
        List<ParticipanteResponseDTO> resultado = new java.util.ArrayList<>();


        for (Participante participante : participantes){
            resultado.add(converterParaResponseDTO(participante));

        }
        return resultado;

    }

    public ParticipanteResponseDTO buscarPorId(Long id){
        Participante participante = buscarEntidadePorId(id);
        return converterParaResponseDTO(participante);

    }

    public void deletar(Long id){
        participanteRepository.deleteById(id);
    }

    public Participante buscarEntidadePorId(Long id){
        Optional<Participante> participanteOptional = participanteRepository.findById(id);

        if (participanteOptional.isPresent()){
            return participanteOptional.get();
        } else {
            throw new RecursoNaoEncontradoException("Participante não encontrado com id: " + id);
        }

    }


    private ParticipanteResponseDTO converterParaResponseDTO(Participante participante){
        return new ParticipanteResponseDTO(
                participante.getId(),
                participante.getNome(),
                participante.getEmail()
        );

    }


}