package com.examenrestapi.service;

import com.examenrestapi.model.Persona;
import java.util.HashMap;
import java.util.Map;

public class PersonaService {
    private static Map<Integer, Persona> personas = new HashMap<>();

    public static Persona agregarPersona(String nombre) {
        int id = personas.size() + 1;  // Generamos un ID único
        Persona nuevaPersona = new Persona(id, nombre);
        personas.put(id, nuevaPersona);
        return nuevaPersona;
    }

    public static Persona obtenerPersona(int id) {
        return personas.get(id);
    }

    public static Persona eliminarPersona(int id) {
        return personas.remove(id);
    }
}
