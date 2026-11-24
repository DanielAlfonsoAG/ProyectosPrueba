package com.Evaluador.springboot_app.repository;

import com.Evaluador.springboot_app.model.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamenRepository extends JpaRepository<Examen, Long> {
    // Consulta derivada por nombre de métodos
    Optional<Examen> findByTitulo(String titulo);
}