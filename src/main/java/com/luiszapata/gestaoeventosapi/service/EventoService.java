package com.luiszapata.gestaoeventosapi.service;

import com.luiszapata.gestaoeventosapi.dto.EventoRequestDTO;
import com.luiszapata.gestaoeventosapi.dto.EventoResponseDTO;
import com.luiszapata.gestaoeventosapi.exception.RecursoNaoEncontradoException;
import com.luiszapata.gestaoeventosapi.model.Evento;
import com.luiszapata.gestaoeventosapi.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService{

    @Autowired
    private EventoRepository eventoRepository;

    public EventoResponseDTO salvar(EventoRequestDTO dto){

        Evento evento = new Evento();
        evento.setNome(dto.getNome());
        evento.setDescricao(dto.getDescricao());
        evento.setDataEvento(dto.getDataEvento());
        evento.setLocal(dto.getLocal());
        evento.setVagasTotais(dto.getVagasTotais());

        Evento salvo = eventoRepository.save(evento);

        return converterParaResponseDTO(salvo);

    }

    public List<EventoResponseDTO> listarTodos(){

        List<Evento> eventos = eventoRepository.findAll();
        List<EventoResponseDTO> resultado = new ArrayList<>();

        for (Evento evento : eventos) {
            resultado.add(converterParaResponseDTO(evento));

        }
        return resultado;
    }

    public EventoResponseDTO buscarPorId(Long id) {
        Evento evento = buscarEntidadePorId(id);
        return converterParaResponseDTO(evento);
    }

    public void deletar(Long id){

        eventoRepository.deleteById(id);
    }

    public Evento buscarEntidadePorId(Long id){

        Optional<Evento> eventoOptional = eventoRepository.findById(id);

        if (eventoOptional.isPresent()){
            return eventoOptional.get();
        } else{
            throw new RecursoNaoEncontradoException("Evento não encontrado com id: " + id);
        }
    }

    private EventoResponseDTO converterParaResponseDTO(Evento evento){

        return new EventoResponseDTO(
                evento.getId(),
                evento.getNome(),
                evento.getDescricao(),
                evento.getDataEvento(),
                evento.getLocal(),
                evento.getVagasTotais()

        );

    }

    public EventoResponseDTO atualizar(Long id, EventoRequestDTO dto){
        Evento evento = buscarEntidadePorId(id);

        evento.setNome(dto.getNome());
        evento.setDescricao(dto.getDescricao());
        evento.setDataEvento(dto.getDataEvento());
        evento.setLocal(dto.getLocal());
        evento.setVagasTotais(dto.getVagasTotais());

        Evento atualizado = eventoRepository.save(evento);

        return converterParaResponseDTO(atualizado);
    }


}