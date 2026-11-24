package com.Evaluador.springboot_app.repository;

import com.Evaluador.springboot_app.model.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
    Optional<Pregunta> findByTexto(String texto);
}


