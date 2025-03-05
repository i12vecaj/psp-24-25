package com.examenrestapi;

import static spark.Spark.*;
import com.examenrestapi.service.PersonaService;
import com.examenrestapi.model.Persona;

public class Server {

    public static void main(String[] args) {
        port(4567);

        // Ruta para la raíz (bienvenida)
        get("/", (req, res) -> "¡Bienvenido a la API REST!");

        // Ruta para agregar personas (para prueba inicial)
        post("/personas", (req, res) -> {
            String nombre = req.queryParams("nombre");
            Persona nuevaPersona = PersonaService.agregarPersona(nombre);
            res.status(201); // Código de éxito
            return "Persona agregada: " + nuevaPersona;
        });

        // Ruta para obtener una persona por su ID
        get("/personas/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Persona persona = PersonaService.obtenerPersona(id);
            if (persona == null) {
                res.status(404);  // No se encontró
                return "Persona no encontrada";
            }
            return persona.toString();
        });

        // Ruta para eliminar una persona por su ID
        delete("/personas/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Persona personaEliminada = PersonaService.eliminarPersona(id);
            if (personaEliminada == null) {
                res.status(404);  // No se encontró
                return "Persona no encontrada";
            }
            return "Persona eliminada: " + personaEliminada;
        });
    }
}
