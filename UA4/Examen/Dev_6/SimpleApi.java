import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class SimpleApi {
    private static Map<Integer, String> personas = new HashMap<>();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Endpoint para obtener todas las personas
        server.createContext("/personas", (exchange -> {
            if ("GET".equals(exchange.getRequestMethod())) {
                String response = personas.toString();
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }));

        // Endpoint para agregar una persona
        server.createContext("/agregar", (exchange -> {
            if ("POST".equals(exchange.getRequestMethod())) {
                int id = personas.size() + 1;
                personas.put(id, "Persona" + id);
                String response = "Persona agregada con ID: " + id;
                exchange.sendResponseHeaders(201, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }));

        // Endpoint para eliminar una persona
        server.createContext("/eliminar", (exchange -> {
            if ("DELETE".equals(exchange.getRequestMethod())) {
                String query = exchange.getRequestURI().getQuery();
                if (query != null && query.startsWith("id=")) {
                    int id = Integer.parseInt(query.substring(3));
                    personas.remove(id);
                    String response = "Persona con ID " + id + " eliminada.";
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                } else {
                    exchange.sendResponseHeaders(400, 0);
                }
            }
        }));

        server.setExecutor(null);
        server.start();
        System.out.println("Servidor corriendo en http://localhost:8080/");
    }
}
