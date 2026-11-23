/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.ArrayList;
import java.util.List;
import persistencia.ControladoraPersistencia;
import Logica.Usuario;
import java.util.Base64;
/**
 *
 * @author agude
 */
public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    public List<Usuario> getUsuario() {
        return controlPersis.getUsuario();
    }
    public List<Horario> getHorario() {
        return controlPersis.getHorario();
    }

    public void borrarUsuario(int id){
    controlPersis.borrarUsuario(id);
    }
    
    public void editarUsuario(Usuario usu) {
        controlPersis.editarUsuario(usu);
    }
 
    public Usuario traerUsuario(int id) {
        return controlPersis.traerUsuario(id); 
    }
    
    private String desencriptarpassword(String contrasenia_encript) {
        byte[] arregloByteDecodificado = Base64.getDecoder().decode(contrasenia_encript);
        String textoDecodificado = new String(arregloByteDecodificado);
        return textoDecodificado;
    }
    
    public int comprobarId(String usuarios, String contrasenia_encript) {
        List<Usuario> listaUsuario = new ArrayList<Usuario>();
       listaUsuario = controlPersis.getUsuario();
        for (Usuario usu:listaUsuario) {
            if (usu.getUsuarios().equals(usuarios)) {
                if (desencriptarpassword(usu.getContrasenia()).equals(contrasenia_encript)) {
                    return usu.getId_usuario();
                }
            }
        }
        return 0;
    }
    public boolean comprobarAcceso(String usuarios, String contrasenia) {
       List<Usuario> listaUsuario = new ArrayList<Usuario>();
       listaUsuario = controlPersis.getUsuario();
        for (Usuario usu:listaUsuario) {
            if (usu.getUsuarios().equals(usuarios)) {
                if (desencriptarpassword(usu.getContrasenia()).equals(contrasenia)) {
                    return true;
                }
            }
        }
        return false;
    }
    public String comprobarRol(String usuarios, String contrasenia) {
       List<Usuario> listaUsuario = new ArrayList<Usuario>();
       listaUsuario = controlPersis.getUsuario();
        for (Usuario usu:listaUsuario) {
            if (usu.getUsuarios().equals(usuarios)) {
                if (desencriptarpassword(usu.getContrasenia()).equals(contrasenia)) {
                    return usu.getRol();
                }
            }
        }
        return "";
    }

    public void crearUsuario(Usuario usu) {
        controlPersis.crearUsuario(usu);
    }
    public void crearHorario(String horario_ini, String horario_fin) {
        Horario hor = new Horario();
        hor.setHorario_inicio(horario_ini);
        hor.setHorario_fin(horario_fin);
        
        controlPersis.crearHorario(hor);
    }

    public void borrarHorario(int id) {
        controlPersis.borrarHorario(id);
    }

    public Horario traerHorario(int idHorario) {
       return controlPersis.traerHorario(idHorario);
    }

    public void editarHorario(Horario hor) {
        controlPersis.editarHorario(hor);
    }

    public void crearVideos(Videos vid) {
        controlPersis.crearVideos(vid);
    }

    public List<Videos> getVideos() {
        return controlPersis.getVideos();
    }

    public Datos traerDatos(int id) {
        return controlPersis.traerDatos(id);
    }

    public String comprobarImage(String usuarios, String contrasenia) {
        List<Usuario> listaUsuario = new ArrayList<Usuario>();
       listaUsuario = controlPersis.getUsuario();
        for (Usuario usu:listaUsuario) {
            if (usu.getUsuarios().equals(usuarios)) {
                if (desencriptarpassword(usu.getContrasenia()).equals(contrasenia)) {
                    return usu.getImage();
                }
            }
        }
        return "";
    }

    public void RegistrarUsuarios(String usuarios, String contrasenia, String rol) {
        Usuario usu = new Usuario();
            usu.setUsuarios(usuarios);
            usu.setContrasenia(contrasenia);
            usu.setRol(rol);
            
            controlPersis.RegistrarUsuarios(usu);
    }

    public void borrarVideo(int id) {
        controlPersis.borrarVideo(id);
    }

    public Videos traerVideo(int id) {
        return controlPersis.traerVideo(id);
    }

    public void editarVideo(Videos vid) {
        controlPersis.editarVideo(vid);
    }
    
    public List<Videos> listaVideosHoy(String fActual){
        return controlPersis.listaVideosHoy(fActual);
    }

    public boolean comprobarHorarios(int id) {
       List<Videos> listaVideos = new ArrayList<Videos>();
       listaVideos = controlPersis.getVideos();
       for (Videos vid:listaVideos) {
            if (vid.getUnHorario().getId_horario() == id) {
                return true;  
            }
        }
       return false;
    }

}
