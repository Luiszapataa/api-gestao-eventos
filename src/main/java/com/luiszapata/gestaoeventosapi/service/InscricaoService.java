package com.luiszapata.gestaoeventosapi.service;

import com.luiszapata.gestaoeventosapi.dto.EventoResponseDTO;
import com.luiszapata.gestaoeventosapi.dto.InscricaoRequestDTO;
import com.luiszapata.gestaoeventosapi.dto.InscricaoResponseDTO;
import com.luiszapata.gestaoeventosapi.dto.ParticipanteResponseDTO;
import com.luiszapata.gestaoeventosapi.model.Evento;
import com.luiszapata.gestaoeventosapi.model.Inscricao;
import com.luiszapata.gestaoeventosapi.model.Participante;
import com.luiszapata.gestaoeventosapi.repository.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InscricaoService{

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ParticipanteService participanteService;

    public InscricaoResponseDTO inscrever(InscricaoRequestDTO dto){
        Evento evento = eventoService.buscarEntidadePorId(dto.getEventoId());
        Participante participante = participanteService.buscarEntidadePorId(dto.getParticipanteId());

        List<Inscricao> inscricoesDoEvento = inscricaoRepository.findByEventoId(evento.getId());
        int vagasOcupadas = inscricoesDoEvento.size();

        if (vagasOcupadas >= evento.getVagasTotais()){
            throw new RuntimeException("Não há vagas disponíveis para este evento");
        }

        Inscricao inscricao = new Inscricao();
        inscricao.setEvento(evento);
        inscricao.setParticipante(participante);
        inscricao.setDataInscricao(LocalDateTime.now());

        Inscricao salva = inscricaoRepository.save(inscricao);

        return converterParaResponseDTO(salva);
    }

    public List<InscricaoResponseDTO> listarTodas(){
        List<Inscricao> inscricoes = inscricaoRepository.findAll();
        List<InscricaoResponseDTO> resultado = new ArrayList<>();

        for (Inscricao inscricao : inscricoes){
            resultado.add(converterParaResponseDTO(inscricao));
        }

        return resultado;
    }

    public List<InscricaoResponseDTO> listarPorEvento(Long eventoId){
        List<Inscricao> inscricoes = inscricaoRepository.findByEventoId(eventoId);
        List<InscricaoResponseDTO> resultado = new ArrayList<>();

        for (Inscricao inscricao : inscricoes){
            resultado.add(converterParaResponseDTO(inscricao));
        }

        return resultado;
    }

    public List<InscricaoResponseDTO> listarPorParticipante(Long participanteId){
        List<Inscricao> inscricoes = inscricaoRepository.findByParticipanteId(participanteId);
        List<InscricaoResponseDTO> resultado = new ArrayList<>();

        for (Inscricao inscricao : inscricoes){
            resultado.add(converterParaResponseDTO(inscricao));
        }

        return resultado;
    }

    public InscricaoResponseDTO buscarPorId(Long id){
        Inscricao inscricao = buscarEntidadePorId(id);
        return converterParaResponseDTO(inscricao);
    }

    public void deletar(Long id){
        inscricaoRepository.deleteById(id);
    }

    private Inscricao buscarEntidadePorId(Long id){
        Optional<Inscricao> inscricaoOptional = inscricaoRepository.findById(id);

        if (inscricaoOptional.isPresent()){
            return inscricaoOptional.get();
        } else {
            throw new RuntimeException("Inscrição não encontrada com id: " + id);
        }
    }

    private InscricaoResponseDTO converterParaResponseDTO(Inscricao inscricao){
        EventoResponseDTO eventoDTO = new EventoResponseDTO(
                inscricao.getEvento().getId(),
                inscricao.getEvento().getNome(),
                inscricao.getEvento().getDescricao(),
                inscricao.getEvento().getDataEvento(),
                inscricao.getEvento().getLocal(),
                inscricao.getEvento().getVagasTotais()
        );

        ParticipanteResponseDTO participanteDTO = new ParticipanteResponseDTO(
                inscricao.getParticipante().getId(),
                inscricao.getParticipante().getNome(),
                inscricao.getParticipante().getEmail()
        );

        return new InscricaoResponseDTO(
                inscricao.getId(),
                inscricao.getDataInscricao(),
                eventoDTO,
                participanteDTO
        );
    }

}