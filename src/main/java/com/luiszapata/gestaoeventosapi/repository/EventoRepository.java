package com.luiszapata.gestaoeventosapi.repository;

import com.luiszapata.gestaoeventosapi.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {


}
