package com.Evaluador.springboot_app.service;

import com.Evaluador.springboot_app.model.Datos;
import com.Evaluador.springboot_app.model.Examen;
import com.Evaluador.springboot_app.model.Pregunta;
import com.Evaluador.springboot_app.model.Usuario;
import com.Evaluador.springboot_app.repository.DatosRepository;
import com.Evaluador.springboot_app.repository.ExamenRepository;
import com.Evaluador.springboot_app.repository.PreguntaRepository;
import com.Evaluador.springboot_app.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class Controladora {

    private final ExamenRepository examRepo;
    private final UsuarioRepository usuarioRepo;
    private final DatosRepository DatosRepo;
    private final PasswordEncoder passwordEncoder;
    private final PreguntaRepository preguntaRepo;


    @Autowired
    public Controladora(ExamenRepository examRepo, UsuarioRepository usuarioRepo, DatosRepository datosRepo, PasswordEncoder passwordEncoder, PreguntaRepository preguntaRepo) {
        this.examRepo = examRepo;
        this.usuarioRepo = usuarioRepo;
        this.DatosRepo = datosRepo;
        this.passwordEncoder = passwordEncoder;
        this.preguntaRepo = preguntaRepo;
    }

    public void registrar(Usuario usuario) {
        String contraseniaEncriptada = passwordEncoder.encode(usuario.getContrasenia());
        usuario.setContrasenia(contraseniaEncriptada);
        usuarioRepo.save(usuario);
    }
    public Examen buscarPorTitulo(String titulo) {
        return examRepo.findByTitulo(titulo).orElse(null);
    }

    public Pregunta buscarPorTexto(String texto) {
        return preguntaRepo.findByTexto(texto).orElse(null);
    }

    public Usuario buscarPorCorreo(String correo) {
        return usuarioRepo.findByCorreo(correo).orElse(null);
    }

    public Datos buscarRuta(String ruta) {
        return DatosRepo.findByRuta(ruta).orElse(null);
    }

    public boolean existeUsuario(String correo) {
        return usuarioRepo.findByCorreo(correo).isPresent();
    }

    public Optional<Usuario> buscarPorCorreoOptional(String correo) {
        return usuarioRepo.findByCorreo(correo);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    public void borrarUsuario(long id) {
        usuarioRepo.deleteById(id);
    }

    public Optional<Usuario> traerUsuario(long id) {
    Optional<Usuario> usu = usuarioRepo.findById(id);
        return usu;
    }

    public void editarUsuario(Usuario usu) {
        String contraseniaEncriptada = passwordEncoder.encode(usu.getContrasenia());
        usu.setContrasenia(contraseniaEncriptada);
        usuarioRepo.save(usu);
    }

    public Optional<Datos> traerDatos(long id) {
        return DatosRepo.findById(id);
    }

    public void guardarExamen(Examen examen, String participantesIds) {
        // Crear instancia nueva
        examen.getParticipantes().clear();

        if (participantesIds != null && !participantesIds.isBlank()) {
            Arrays.stream(participantesIds.split(","))
                    .map(String::trim)
                    .map(Long::parseLong)
                    .map(usuarioRepo::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(examen.getParticipantes()::add);
        }

        // Asegurar que cada pregunta esté vinculada al examen
        if (examen.getPreguntas() != null) {
            for (Pregunta p : examen.getPreguntas()) {
                p.setExamen(examen);
            }
        }
        // Verificar cómo están llegando los textos
                examen.getPreguntas().forEach(p ->
                        System.out.println("Texto recibido: " + p.getTexto()));

        examRepo.save(examen);
    }
    public List<Examen> listarExamen() {
        return examRepo.findAll();
    }

    public List<Usuario> findAllById(List<Long> ids) {
        return usuarioRepo.findAllById(ids);
    }

    public Optional<Examen> obtenerIdExamen(Long id) {
        return examRepo.findById(id);
    }
    @Transactional
    public void guardarCambiosExamen(Examen examenEditado, String participantesIds) {
        Optional<Examen> examenOriginalOpt = examRepo.findById(examenEditado.getId_examen());

        if (examenOriginalOpt.isPresent()) {
            Examen examenOriginal = examenOriginalOpt.get();

            // Participantes
            examenOriginal.getParticipantes().clear();
            if (participantesIds != null && !participantesIds.isBlank()) {
                Arrays.stream(participantesIds.split(","))
                        .map(String::trim)
                        .map(Long::parseLong)
                        .map(usuarioRepo::findById)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .forEach(examenOriginal.getParticipantes()::add);
            }

            // Campos básicos
            examenOriginal.setTitulo(examenEditado.getTitulo());
            examenOriginal.setDescripcion(examenEditado.getDescripcion());
            examenOriginal.setDificultad(examenEditado.getDificultad());
            examenOriginal.setDuracion(examenEditado.getDuracion());
            examenOriginal.setTiempoInicio(examenEditado.getTiempoInicio());
            examenOriginal.setTiempoFinal(examenEditado.getTiempoFinal());

            //  Preguntas: sincronizar cambios
            Map<Long, Pregunta> preguntasOriginalesPorId = examenOriginal.getPreguntas()
                    .stream()
                    .filter(p -> p.getIdPregunta() != null)
                    .collect(Collectors.toMap(Pregunta::getIdPregunta, p -> p));

            List<Pregunta> nuevasPreguntas = new ArrayList<>();

            for (Pregunta pNueva : examenEditado.getPreguntas()) {
                if (pNueva.getIdPregunta() != null && preguntasOriginalesPorId.containsKey(pNueva.getIdPregunta())) {
                    // Actualizar campos de pregunta existente
                    Pregunta existente = preguntasOriginalesPorId.get(pNueva.getIdPregunta());
                    existente.setTexto(pNueva.getTexto());
                    nuevasPreguntas.add(existente);
                    preguntasOriginalesPorId.remove(pNueva.getIdPregunta()); // Ya procesada
                } else {
                    // Pregunta nueva
                    pNueva.setExamen(examenOriginal);
                    nuevasPreguntas.add(pNueva);
                }
            }

            // Borrar preguntas que fueron eliminadas
            for (Pregunta pBorrada : preguntasOriginalesPorId.values()) {
                preguntaRepo.delete(pBorrada); //
            }

            // Reasignar
            examenOriginal.setPreguntas(nuevasPreguntas);

            examRepo.save(examenOriginal);
        }
    }

    public void eliminarExamen(Examen examen) {
        examRepo.delete(examen);
    }

    public boolean tituloExistente(String titulo, Long idExamenActual) {
        Optional<Examen> existente = examRepo.findByTitulo(titulo);
        return existente
                .map(ex -> idExamenActual == null || !ex.getId_examen().equals(idExamenActual))
                .orElse(false);

    }
}



