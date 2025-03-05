package com.example.manuelTorres;

import static spark.Spark.*;


public class Main {
    public static void main(String[] args) {
        // Obtener todas las personas
        get("/personas", (req, res) -> {
            return PersonaRepository.getPersonas().toString();
        });

        // Obtener una persona por ID
        get("/personas/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Persona persona = PersonaRepository.getPersonaById(id);
            if (persona != null) {
                return persona.getNombre();
            } else {
                res.status(404);
                return "Persona no encontrada";
            }
        });

        // Agregar una nueva persona
        post("/personas", (req, res) -> {
            String nombre = req.queryParams("nombre");
            int id = PersonaRepository.getPersonas().size() + 1; // ID único
            Persona persona = new Persona(id, nombre);
            PersonaRepository.addPersona(persona);
            res.status(201);
            return "Persona agregada con ID: " + id;
        });

        // Eliminar una persona por ID
        delete("/personas/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            PersonaRepository.deletePersonaById(id);
            return "Persona eliminada";
        });
    }
}

