package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Libros;
import logica.Usuario;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-01-13T03:28:21")
@StaticMetamodel(Prestamos.class)
public class Prestamos_ { 

    public static volatile SingularAttribute<Prestamos, Usuario> unUsuario;
    public static volatile SingularAttribute<Prestamos, String> estado;
    public static volatile SingularAttribute<Prestamos, String> fecha_prestamo;
    public static volatile SingularAttribute<Prestamos, Libros> id_libro;
    public static volatile SingularAttribute<Prestamos, String> fecha_devolucion;
    public static volatile SingularAttribute<Prestamos, Integer> id_prestamos;

}