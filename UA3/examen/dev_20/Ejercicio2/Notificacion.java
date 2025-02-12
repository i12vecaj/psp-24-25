package Ejercicio2;

import java.io.Serializable;


// clase notificación para hacer más escalable el ejercicio
public class Notificacion implements Serializable {
    String descripcion;

    public Notificacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "descripcion='" + descripcion + '\'' +
                '}';
    }
}
