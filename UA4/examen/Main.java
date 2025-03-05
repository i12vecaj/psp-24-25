import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static final Map<Integer, String> personas = new HashMap<>();

    public static void main(String[] args) throws Exception {
        // Inicializa algunas personas para pruebas
        personas.put(1, "Juan");
        personas.put(2, "Maria");
        personas.put(3, "Carlos");

        // Crear servidor HTTP
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/personas", new PersonHandler());
        server.createContext("/personas/id", new PersonByIdHandler());
        server.start();

        System.out.println("Servidor corriendo en http://localhost:8080");
    }

    // Handler para obtener todas las personas
    static class PersonHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = personas.toString();
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    // Handler para obtener persona por ID
    static class PersonByIdHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Obtener el parámetro 'id' de la URL
            String query = exchange.getRequestURI().getQuery();
            if (query != null && query.contains("id=")) {
                String[] queryParams = query.split("=");

                if (queryParams.length == 2 && queryParams[0].equals("id")) {
                    try {
                        // Convertir el parámetro id a entero
                        int id = Integer.parseInt(queryParams[1]);

                        // Verificar si la persona existe en el mapa
                        String persona = personas.get(id);
                        String response;

                        if (persona != null) {
                            response = "Persona: " + persona;
                        } else {
                            response = "Persona no encontrada";
                        }

                        exchange.sendResponseHeaders(200, response.getBytes().length);
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    } catch (NumberFormatException e) {
                        // Si el id no es un número válido
                        String response = "ID inválido";
                        exchange.sendResponseHeaders(400, response.getBytes().length);
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }
                } else {
                    // Si el parámetro 'id' no está bien formado
                    String response = "Parámetro 'id' no proporcionado correctamente";
                    exchange.sendResponseHeaders(400, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            } else {
                // Si no se pasa el parámetro 'id' en absoluto
                String response = "Parámetro 'id' no proporcionado";
                exchange.sendResponseHeaders(400, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }
}
        