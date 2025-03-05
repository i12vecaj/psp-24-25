package org.example.api;

import com.google.gson.Gson;
import static spark.Spark.*;

public class RestAPI {
    public static void main(String[] args) {
        Gson gson = new Gson();

        // Habilitar CORS
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*"); // Permitir solicitudes desde cualquier origen
            response.header("Access-Control-Request-Method", "GET, POST, PUT, DELETE");
            response.header("Access-Control-Allow-Headers", "Content-Type");
        });

        // Crear una persona
        post("/people", (req, res) -> {
            String name = req.queryParams("name");
            Person person = new Person(0, name); // El ID se asignará en el repositorio
            PersonRepository.addPerson(person);
            return "Person added with ID: " + person.getId();
        });

        // Obtener todas las personas
        get("/people", (req, res) -> {
            res.type("application/json");
            return PersonRepository.getAllPeople();
        }, gson::toJson);

        // Obtener una persona por ID
        get("/people/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Person person = PersonRepository.getPerson(id);
            if (person != null) {
                res.type("application/json");
                return person;
            } else {
                res.status(404);
                return "Person not found";
            }
        }, gson::toJson);

        // Actualizar una persona por ID
        put("/people/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            String name = req.queryParams("name");
            Person person = new Person(id, name);
            PersonRepository.updatePerson(id, person);
            return "Person updated";
        });

        // Eliminar una persona por ID
        delete("/people/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            PersonRepository.deletePerson(id);
            return "Person deleted";
        });
    }
}
