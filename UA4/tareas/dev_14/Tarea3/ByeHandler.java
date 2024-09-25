import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import com.sun.net.httpserver.HttpExchange;

public class ByeHandler extends BasicHandler {
    private static final String BYEBYE_MESSAGE = "BYE! from our framework-less REST API";

    public ByeHandler(DataStorage store) {
        super(store);
        // TODO Auto-generated constructor stub
    }

    public void handle(HttpExchange exchange) throws IOException {
        String remoteAddress = exchange.getRemoteAddress().getHostString();
        System.out.println("[" + new Date() + "] Received " + exchange.getRequestMethod() + " "
                + exchange.getRequestURI() + " from client: " + remoteAddress);

        if ("GET".equals(exchange.getRequestMethod())) {

            handleGet(exchange);
        } else {
            exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
        }
        exchange.close();
    }

    private static void handleGet(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            String responseText = "BYEBYE_MESSAGE";
            exchange.sendResponseHeaders(200, responseText.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(responseText.getBytes());
            output.flush();
        } else {
            exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
        }
        exchange.close();
    }

}