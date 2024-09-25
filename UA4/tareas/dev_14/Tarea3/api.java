import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class api {
    public static void main(String[] args) throws IOException {
        DataStorage store = new DataStorage();
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);

        server.createContext("/api/greeting", new GreetingHandler(store));
        server.createContext("/api/bye", new ByeHandler(store));
        server.createContext("/api/person", new PersonHandler(store));

        // All contexts have been created
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("The framework-less REST API server is listening on " + server.getAddress().getAddress()
                + ":" + server.getAddress().getPort());
    }
}
