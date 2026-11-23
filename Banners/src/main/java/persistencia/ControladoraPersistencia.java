/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;


import Logica.Datos;
import Logica.Horario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Logica.Usuario;
import Logica.Videos;
import java.sql.Timestamp;
import java.util.Base64;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author agude
 */
public class ControladoraPersistencia {

    UsuarioJpaController usuJPA = new UsuarioJpaController();
    HorarioJpaController horJPA = new HorarioJpaController();
    VideosJpaController vidJPA = new VideosJpaController();
    DatosJpaController datJPA = new DatosJpaController();

    public List<Usuario> getUsuario() {
        return usuJPA.findUsuarioEntities();
    }
    
    
    public void crearUsuario(Usuario usu) {
        Usuario usuarioExistente = findUsuarios(usu.getUsuarios());
        
        if (usuarioExistente == null) {
            String tmp = encriptarpassword(usu.getContrasenia());
            usu.setContrasenia(tmp);

            usuJPA.create(usu);
        
    }
        // Si el usuario ya existe, no lo creamos
        System.out.println("El usuario con el nombre '" + usu.getUsuarios() + "' ya existe."); 
    }
    public void crearHorario(Horario hor) {
        Horario horarioExistente = findHorarios(hor.getHorario_fin(), hor.getHorario_inicio());
        if (horarioExistente == null) {
            horJPA.create(hor);
        }
    }
    private Horario findHorarios(String horario_fin, String horario_inicio) {
       EntityManager em = horJPA.getEntityManager();
       
       Query query = em.createQuery(
               "SELECT h FROM Horario h " +
        "WHERE (:horario_inicio >= h.horario_inicio AND :horario_fin < h.horario_fin)" + // Nuevo horario abarca completamente el horario existente
        "OR (:horario_inicio BETWEEN h.horario_inicio AND h.horario_fin) " + // El nuevo horario de inicio debe estar dentro del rango de otro horario
        "OR (h.horario_inicio BETWEEN :horario_inicio AND :horario_fin) " +  // Verifica si el horario existente empieza dentro del nuevo rango
        "OR (h.horario_fin BETWEEN :horario_inicio AND :horario_fin)"  //verifica si el fin del horario existente se encuentra dentro del rango del nuevo horario que estás intentando agregar
        );
        query.setParameter("horario_fin", horario_inicio);
        query.setParameter("horario_inicio", horario_fin);
        try {
            return (Horario) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } 
    }
    
    public Usuario findUsuarios (String usuarios) {
         EntityManager em = usuJPA.getEntityManager();
        // Consulta para encontrar un usuario por nombre
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.usuarios = :usuarios");
        query.setParameter("usuarios", usuarios);

        try {
            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } 
    }
    
    public void editarUsuario(Usuario usu) {
        try {
            String tmp = encriptarpassword(usu.getContrasenia());
        usu.setContrasenia(tmp);
            usuJPA.edit(usu);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex );
        }
    }
    public void borrarUsuario(int id) {
        try {
            usuJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex );
        }
    }     
    
    public Usuario traerUsuario(int id) {
        Usuario usuEnviar= usuJPA.findUsuario(id);
        String tmp = desencriptarpassword(usuEnviar.getContrasenia());
        usuEnviar.setContrasenia(tmp);
        return usuEnviar;
    }

   

    private String encriptarpassword(String contrasenia) {
        String codigo = Base64.getEncoder().encodeToString(contrasenia.getBytes());
       return codigo;
    }

    private String desencriptarpassword(String contrasenia) {
        byte[] arregloByteDecodificado = Base64.getDecoder().decode(contrasenia);
        String textoDecodificado = new String(arregloByteDecodificado);
        return textoDecodificado;
    }

    public List<Horario> getHorario() {
        return horJPA.findHorarioEntities();
    }

    public void borrarHorario(int id) {
        try {
            horJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Horario traerHorario(int idHorario) {
       return horJPA.findHorario(idHorario);
    }    

    public void editarHorario(Horario hor) {
        try {
            horJPA.edit(hor);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Videos> getVideos() {
        return vidJPA.findVideosEntities();
    }

    public void crearVideos(Videos vid) {
        vidJPA.create(vid);
    }

    public Datos traerDatos(int id) {
        return datJPA.findDatos(id);
    }

    public void RegistrarUsuarios(Usuario usu) {
        Usuario usuarioExistente = findUsuarios(usu.getUsuarios());
        
        if (usuarioExistente == null) {
            String tmp = encriptarpassword(usu.getContrasenia());
            usu.setContrasenia(tmp);

            usuJPA.create(usu);
        
    }
        // Si el usuario ya existe, no lo creamos
        System.out.println("El usuario con el nombre '" + usu.getUsuarios() + "' ya existe.");
    }

    public void borrarVideo(int id) {
        try {
            vidJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Videos traerVideo(int id) {
        return vidJPA.findVideos(id);
    }

    public void editarVideo(Videos vid) {
        try {
            vidJPA.edit(vid);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Videos> listaVideosHoy(String fActual) {
        EntityManager em = vidJPA.getEntityManager();
        String fechaBuscar = fActual; // Obtiene solo la fecha en formato "yyyy-MM-dd"
        
        Query query = em.createQuery("SELECT v FROM Videos v WHERE :fechaBuscar BETWEEN v.unHorario.horario_inicio AND v.unHorario.horario_fin ORDER BY v.unHorario.horario_inicio");
        query.setParameter("fechaBuscar", fechaBuscar + "%");
        
        List<Videos> lista= query.getResultList();
        return lista;
    }
    
}