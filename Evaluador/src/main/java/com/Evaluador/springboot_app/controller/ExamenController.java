package com.Evaluador.springboot_app.controller;

import com.Evaluador.springboot_app.model.Datos;
import com.Evaluador.springboot_app.model.Examen;
import com.Evaluador.springboot_app.model.Pregunta;
import com.Evaluador.springboot_app.model.Usuario;
import com.Evaluador.springboot_app.repository.UsuarioRepository;
import com.Evaluador.springboot_app.service.Controladora;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ExamenController {

    @Autowired
    private Controladora control;

    // Mostrar el formulario para crear examen
    @GetMapping("/crearExamen")
    public String mostrarFormularioExamen(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();

        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        Examen examen = new Examen();

        examen.setPreguntas(new ArrayList<>());
        examen.getPreguntas().add(new Pregunta()); // una pregunta vacía por defecto

        model.addAttribute("examen", examen);
        model.addAttribute("usuarios", control.listarUsuarios());

        return "crearExamen";
    }

    // Procesar el formulario POST con el examen y las preguntas
    @PostMapping("/crearExamen")
    public String procesarExamen(@ModelAttribute("examen") Examen examen,
                                 HttpSession session, @RequestParam("participantes") String participantesIds, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioObj");

        if (usuario == null) {
            throw new IllegalStateException("No hay usuario en la sesión");
        }
        // DEBUG: Revisa las preguntas que llegan
        for (int i = 0; i < examen.getPreguntas().size(); i++) {
            System.out.println("Pregunta " + i + ": " + examen.getPreguntas().get(i).getTexto());
        }
        if (control.tituloExistente(examen.getTitulo(), examen.getId_examen())){
            model.addAttribute("errorTitulo", "Ya existe un examen con ese título.");
            model.addAttribute("examen", examen);
            model.addAttribute("participantesString", participantesIds);
            model.addAttribute("usuarios", control.listarUsuarios());
            return "crearExamen";
        }

        // Evitar errores por preguntas nulas
        if (examen.getPreguntas() != null) {
            for (int i = 0; i < examen.getPreguntas().size(); i++) {
                System.out.println("Pregunta " + i + ": " + examen.getPreguntas().get(i).getTexto());
            }
        }

        examen.setCreadoPor(usuario);
        control.guardarExamen(examen, participantesIds);

        return "redirect:/verExamen";
    }

    @GetMapping("/editarExamen")
    public String mostrarFormularioEdicion(@RequestParam("id_examen") Long id, Model model) {
        Optional<Examen> examenOpt = control.obtenerIdExamen(id);
        if (examenOpt.isEmpty()) {
            return "redirect:/verExamen"; // o muestra error
        }
        Examen examen = examenOpt.get();
        List<Usuario> usuarios = control.listarUsuarios(); // Para el select

        // Convertir participantes existentes a una cadena de IDs separados por coma
        String participantesString = examen.getParticipantes().stream()
                .map(p -> p.getId().toString())
                .collect(Collectors.joining(","));

        model.addAttribute("examen", examen);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("participantesString", participantesString);
        return "editarExamen"; // Thymeleaf HTML
    }

    @PostMapping("/editarExamen/{id}")
    public String guardarCambiosExamen(@PathVariable("id") Long id, @ModelAttribute Examen examen, Model model,
                                       HttpSession session, @RequestParam("participantes") String participantesIds) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioObj");

        if (usuario == null) {
            throw new IllegalStateException("No hay usuario en la sesión");
        }

        // Evitar errores por preguntas nulas
        if (examen.getPreguntas() != null) {
            for (int i = 0; i < examen.getPreguntas().size(); i++) {
                System.out.println("Pregunta " + i + ": " + examen.getPreguntas().get(i).getTexto());
            }
        }

        examen.setCreadoPor(usuario);
        examen.setId_examen(id); // Muy importante: asegurar el ID correcto
        control.guardarCambiosExamen(examen, participantesIds); // Tu lógica de servicio
        return "redirect:/verExamen";
    }

    // (opcional) Vista para listar exámenes existentes
    @GetMapping("/verExamen")
    public String verExamenes(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();

        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        List<Examen> examenes = control.listarExamen();
        model.addAttribute("examenes", examenes);

        return "verExamen";
    }
    @PostMapping("/eliminarExamen/{id_examen}")
    public String eliminarExamen(@PathVariable("id_examen") Long id) {
        Optional<Examen> examenOpt = control.obtenerIdExamen(id);

        if (examenOpt.isPresent()) {
            Examen examen = examenOpt.get();

            // Limpiar relación con participantes (si es ManyToMany)
            examen.getParticipantes().clear();

            // Preguntas se eliminarán automáticamente si tienes cascade + orphanRemoval
            control.eliminarExamen(examen);
        }

        return "redirect:/verExamen";
    }

}
