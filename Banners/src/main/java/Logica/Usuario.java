package Logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int id_usuario;
    private String image;
    private String usuarios;
    private String contrasenia;
    private String rol;

    public Usuario() {
    }

    public Usuario(int id_usuario, String image, String usuarios, String contrasenia, String rol) {
        this.id_usuario = id_usuario;
        this.image = image;
        this.usuarios = usuarios;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public String getImage() {
        return image;
    }

    public String getUsuarios() {
        return usuarios;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUsuarios(String usuarios) {
        this.usuarios = usuarios;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
}
