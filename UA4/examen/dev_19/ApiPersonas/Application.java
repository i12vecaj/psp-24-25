package main.com.example.personapi;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Optional;

public class Application {

    private static PersonService personService = new PersonService();

    public static void main(String[] args) throws Exception {
        // Crear el servidor HTTP en el puerto 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/persons", new PersonHandler());
        server.createContext("/api/persons/", new PersonHandler());
        server.setExecutor(null); // Crear un ejecutor predeterminado
        System.out.println("Server started at http://localhost:8080");
        server.start();
    }

    // Handler para gestionar las solicitudes de personas
    static class PersonHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "";

            // Obtén el método HTTP (GET, POST, DELETE)
            String method = exchange.getRequestMethod();

            // Obtener la URL de la solicitud
            String path = exchange.getRequestURI().getPath();
            System.out.println("Request Path: " + path);

            if (method.equals("GET")) {
                // Obtener todas las personas
                if (path.equals("/api/persons")) {
                    response = getAllPersons();
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                } else if (path.startsWith("/api/persons/")) {
                    // Obtener una persona por ID
                    int id = Integer.parseInt(path.split("/")[3]);
                    response = getPersonById(id);
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                }
            } else if (method.equals("POST")) {
                // Crear una persona nueva
                response = createPerson(exchange);
                exchange.sendResponseHeaders(201, response.getBytes().length);
            } else if (method.equals("DELETE")) {
                // Eliminar persona por ID
                int id = Integer.parseInt(path.split("/")[3]);
                response = deletePerson(id);
                exchange.sendResponseHeaders(200, response.getBytes().length);
            } else {
                exchange.sendResponseHeaders(405, -1); // Método no permitido
                return;
            }

            // Enviar la respuesta
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        // Obtener todas las personas como JSON
        private String getAllPersons() {
            List<Person> persons = personService.getAllPersons();
            StringBuilder response = new StringBuilder("[");
            for (int i = 0; i < persons.size(); i++) {
                response.append("{")
                        .append("\"id\":").append(persons.get(i).getId()).append(",")
                        .append("\"name\":\"").append(persons.get(i).getName()).append("\",")
                        .append("\"about\":\"").append(persons.get(i).getAbout()).append("\",")
                        .append("\"birthYear\":").append(persons.get(i).getBirthYear())
                        .append("}");
                if (i < persons.size() - 1) {
                    response.append(",");
                }
            }
            response.append("]");
            return response.toString();
        }

        // Obtener una persona por ID
        private String getPersonById(int id) {
            Optional<Person> person = personService.getPersonById(id);
            if (person.isPresent()) {
                return "{"
                        + "\"id\":" + person.get().getId() + ","
                        + "\"name\":\"" + person.get().getName() + "\","
                        + "\"about\":\"" + person.get().getAbout() + "\","
                        + "\"birthYear\":" + person.get().getBirthYear()
                        + "}";
            } else {
                return "{\"error\":\"Persona no encontrada\"}";
            }
        }

        // Crear una nueva persona
        private String createPerson(HttpExchange exchange) throws IOException {
            String requestBody = new String(exchange.getRequestBody().readAllBytes());
            // Para este ejemplo, se asume que el cuerpo es JSON, parsear y extraer valores.
            String[] fields = requestBody.split(",");
            String name = fields[0].split(":")[1].replace("\"", "");
            String about = fields[1].split(":")[1].replace("\"", "");
            int birthYear = Integer.parseInt(fields[2].split(":")[1].replace("\"", "").trim());

            Person newPerson = personService.createPerson(name, about, birthYear);
            return "{"
                    + "\"id\":" + newPerson.getId() + ","
                    + "\"name\":\"" + newPerson.getName() + "\","
                    + "\"about\":\"" + newPerson.getAbout() + "\","
                    + "\"birthYear\":" + newPerson.getBirthYear()
                    + "}";
        }

        // Eliminar persona por ID
        private String deletePerson(int id) {
            boolean deleted = personService.deletePerson(id);
            if (deleted) {
                return "{\"message\":\"Persona eliminada\"}";
            } else {
                return "{\"error\":\"Persona no encontrada\"}";
            }
        }
    }
}
