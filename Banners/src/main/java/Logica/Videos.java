package Logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Videos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int id_video;
    private String titulo;
    private String archivo;
    @OneToOne
    private Horario unHorario;

    public Videos() {
    }

    public Videos(int id_video, String titulo, String archivo, Horario unHorario) {
        this.id_video = id_video;
        this.titulo = titulo;
        this.archivo = archivo;
        this.unHorario = unHorario;
    }

    public int getId_video() {
        return id_video;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArchivo() {
        return archivo;
    }

    public Horario getUnHorario() {
        return unHorario;
    }

    public void setId_video(int id_video) {
        this.id_video = id_video;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public void setUnHorario(Horario unHorario) {
        this.unHorario = unHorario;
    }

   
}
