package org.example.restapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "personas")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int edad;

    // Setter manual para el ID (necesario para actualizar registros)
    public void setId(int id) {
        this.id = id;
    }
}
