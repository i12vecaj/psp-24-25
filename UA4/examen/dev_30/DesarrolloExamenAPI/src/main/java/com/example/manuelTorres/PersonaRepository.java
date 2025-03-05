package com.example.manuelTorres;

import java.util.ArrayList;
import java.util.List;

public class PersonaRepository {
    private static List<Persona> personas = new ArrayList<>();

    public static List<Persona> getPersonas() {
        return personas;
    }

    public static void addPersona(Persona persona) {
        personas.add(persona);
    }

    public static Persona getPersonaById(int id) {
        for (Persona persona : personas) {
            if (persona.getId() == id) {
                return persona;
            }
        }
        return null;
    }

    public static void deletePersonaById(int id) {
        personas.removeIf(persona -> persona.getId() == id);
    }
}
