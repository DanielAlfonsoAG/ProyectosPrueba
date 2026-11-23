package Logica;

import Logica.Horario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-01-13T02:54:46")
@StaticMetamodel(Videos.class)
public class Videos_ { 

    public static volatile SingularAttribute<Videos, Integer> id_video;
    public static volatile SingularAttribute<Videos, String> archivo;
    public static volatile SingularAttribute<Videos, String> titulo;
    public static volatile SingularAttribute<Videos, Horario> unHorario;

}