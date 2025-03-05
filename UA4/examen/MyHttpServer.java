import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class MyHttpServer {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
        Personas personas = new Personas();

        server.createContext("/personas", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = personas.getAllPersonas().toString();
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });

        server.createContext("/personas/id", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = "Persona no encontrada";
                String query = exchange.getRequestURI().getQuery();
                if (query != null && query.startsWith("id=")) {
                    String idStr = query.split("=")[1];
                    try {
                        int id = Integer.parseInt(idStr);
                        String persona = personas.getPersona(id);
                        if (persona != null) {
                            response = "Persona: " + persona;
                        }
                    } catch (NumberFormatException e) {
                        response = "ID inválido";
                    }
                }

                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });

        server.start();
        System.out.println("Servidor corriendo en http://localhost:8081");
    }
}
