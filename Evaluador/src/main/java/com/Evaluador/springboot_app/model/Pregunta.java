package com.Evaluador.springboot_app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pregunta")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPregunta;

    private String texto;

    @ManyToOne
    @JoinColumn(name = "examen_id")
    private Examen examen;

    public Pregunta() {
    }

    public Pregunta(String texto, Examen examen) {
        this.texto = texto;
        this.examen = examen;
    }

    public Long getIdPregunta() {
        return idPregunta;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }
}
