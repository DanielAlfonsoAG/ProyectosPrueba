package com.Evaluador.springboot_app.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "examen")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_examen;

    @Column(name = "titulo", nullable = false, unique = true, length = 250)
    private String titulo;

    @Column(name = "descripcion", nullable = false, length = 250)
    private String descripcion;

    @Column(name = "dificultad", nullable = false, length = 250)
    private String dificultad;

    @ManyToMany
    @JoinTable(
            name = "examen_participantes",
            joinColumns = @JoinColumn(name = "examen_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> participantes;

    private int numeroPreguntas;

    @OneToMany(mappedBy = "examen", cascade = CascadeType.ALL)
    private List<Pregunta> preguntas = new ArrayList<>();

    @Column(name = "tiempo_inicio", nullable = false, length = 250)
    private LocalDateTime  tiempoInicio;

    @Column(name = "tiempo_final", nullable = false, length = 250)
    private LocalDateTime tiempoFinal;

    @Column(name = "duracion", nullable = false, length = 250)
    private String duracion;

    @ManyToOne
    @JoinColumn(name = "creado_por", nullable = false)
    private Usuario creadoPor;

    public Examen() {
    }

    public Examen(Long id_examen, String titulo, String descripcion, String dificultad, List<Usuario> participantes, int numeroPreguntas, List<Pregunta> preguntas, LocalDateTime tiempoInicio, LocalDateTime tiempoFinal, String duracion, Usuario creadoPor) {
        this.id_examen = id_examen;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.participantes = participantes;
        this.numeroPreguntas = numeroPreguntas;
        this.preguntas = preguntas;
        this.tiempoInicio = tiempoInicio;
        this.tiempoFinal = tiempoFinal;
        this.duracion = duracion;
        this.creadoPor = creadoPor;
    }

    public Long getId_examen() {
        return id_examen;
    }

    public void setId_examen(Long id_examen) {
        this.id_examen = id_examen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public List<Usuario> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Usuario> participantes) {
        this.participantes = participantes;
    }

    public int getNumeroPreguntas() {
        return numeroPreguntas;
    }

    public void setNumeroPreguntas(int numeroPreguntas) {
        this.numeroPreguntas = numeroPreguntas;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public LocalDateTime getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(LocalDateTime tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    public LocalDateTime getTiempoFinal() {
        return tiempoFinal;
    }

    public void setTiempoFinal(LocalDateTime tiempoFinal) {
        this.tiempoFinal = tiempoFinal;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public Usuario getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Usuario creadoPor) {
        this.creadoPor = creadoPor;
    }

    public void agregarPreguntaVacia() {
        Pregunta pregunta = new Pregunta();
        pregunta.setExamen(this);
        this.preguntas.add(pregunta);
    }

    public void generarPreguntasVacias() {
        if (this.preguntas == null) {
            this.preguntas = new ArrayList<>();
        } else {
            this.preguntas.clear();
        }

        for (int i = 0; i < numeroPreguntas; i++) {
            agregarPreguntaVacia();
        }
    }
}
