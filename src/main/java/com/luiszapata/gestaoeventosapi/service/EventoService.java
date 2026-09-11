package com.luiszapata.gestaoeventosapi.service;

import com.luiszapata.gestaoeventosapi.model.Evento;
import com.luiszapata.gestaoeventosapi.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public Evento salvar(Evento evento){
        return eventoRepository.save(evento);
    }

    public List<Evento> listarTodos(){
        return eventoRepository.findAll();
    }

    public Evento buscarPorId(Long id) {
        Optional<Evento> eventoOptional = eventoRepository.findById(id);

        if (eventoOptional.isPresent()) {
            return eventoOptional.get();

        } else {
            throw new RuntimeException("Evento não encontrado pelo id " + id);
        }
    }

    public void deletar(Long id){
        eventoRepository.deleteById(id);
    }
}
