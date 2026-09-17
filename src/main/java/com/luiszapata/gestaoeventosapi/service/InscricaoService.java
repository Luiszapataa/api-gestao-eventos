package com.luiszapata.gestaoeventosapi.service;

import com.luiszapata.gestaoeventosapi.model.Evento;
import com.luiszapata.gestaoeventosapi.model.Inscricao;
import com.luiszapata.gestaoeventosapi.model.Participante;
import com.luiszapata.gestaoeventosapi.repository.EventoRepository;
import com.luiszapata.gestaoeventosapi.repository.InscricaoRepository;
import com.luiszapata.gestaoeventosapi.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ParticipanteRepository participanteRepository;

    public Inscricao inscrever(Inscricao inscricao) {
        Long eventoId = inscricao.getEvento().getId();
        Long participanteId = inscricao.getParticipante().getId();

        Optional<Evento> eventoOptional = eventoRepository.findById(eventoId);
        Evento evento;

        if (eventoOptional.isPresent()) {
            evento = eventoOptional.get();
        } else {
            throw new RuntimeException("Evento não encontrado com id: " + eventoId);
        }

        Optional<Participante> participanteOptional = participanteRepository.findById(participanteId);
        Participante participante;

        if (participanteOptional.isPresent()) {
            participante = participanteOptional.get();
        } else {
            throw new RuntimeException("Participante não encontrado com id: " + participanteId);
        }

        List<Inscricao> inscricoesDoEvento = inscricaoRepository.findByEventoId(eventoId);
        int vagasOcupadas = inscricoesDoEvento.size();

        if (vagasOcupadas >= evento.getVagasTotais()) {
            throw new RuntimeException("Não há vagas disponíveis para este evento");
        }

        inscricao.setEvento(evento);
        inscricao.setParticipante(participante);
        inscricao.setDataInscricao(LocalDateTime.now());

        return inscricaoRepository.save(inscricao);
    }

    public List<Inscricao> listarTodas() {
        return inscricaoRepository.findAll();
    }

    public List<Inscricao> listarPorEvento(Long eventoId) {
        return inscricaoRepository.findByEventoId(eventoId);
    }

    public List<Inscricao> listarPorParticipante(Long participanteId) {
        return inscricaoRepository.findByParticipanteId(participanteId);
    }

    public Inscricao buscarPorId(Long id) {
        Optional<Inscricao> inscricaoOptional = inscricaoRepository.findById(id);

        if (inscricaoOptional.isPresent()) {
            return inscricaoOptional.get();
        } else {
            throw new RuntimeException("Inscrição não encontrada com id: " + id);
        }
    }

    public void deletar(Long id) {
        inscricaoRepository.deleteById(id);
    }

}