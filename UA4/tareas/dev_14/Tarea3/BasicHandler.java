
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

abstract class BasicHandler implements HttpHandler {
    protected final DataStorage store;

    public BasicHandler(DataStorage store) {
        this.store = store;
    }

    public static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();

        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }

    protected JSONObject parseRequestBody(HttpExchange exchange) throws IOException, JSONException {
        String requestBody = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        return new JSONObject(requestBody);
    }

    protected void sendResponse(HttpExchange exchange, int statusCode, String responseString2) throws IOException {
        String responseString = responseString2.toString();
        byte[] responseBytes = responseString.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, responseBytes.length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
    }

    protected String messageToJsonString(String message) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", message);

        return (jsonObject.toString());

    }

    protected String personToJsonString(Person person) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", person.getName());
        jsonObject.put("about", person.getAbout());
        jsonObject.put("birthYear", person.getBirthYear());

        return (jsonObject.toString());

    }

    protected void SendResposeToClient(int responseCode, String message, HttpExchange exchange, Person person)
            throws JSONException {
        JSONObject jsonObject = new JSONObject();

        JSONObject jsonHeaderObject = new JSONObject();
        // jsonHeaderObject.putOpt("header", jsonHeaderObject);

        jsonHeaderObject.put("api_name", "My API Name");
        jsonHeaderObject.put("api_version", "1.0.0");
        jsonHeaderObject.put("api_user", "guest");
        jsonHeaderObject.put("api_endpoint", "api/bye");
        jsonHeaderObject.put("api_request_method", "GET");
        jsonHeaderObject.put("api_request_parameters", "X.X.X");
        jsonHeaderObject.put("http_response_status", "400");
        jsonHeaderObject.put("http_response_message", "Bad request");
        jsonHeaderObject.put("response_time", "123456789");

        JSONObject jsonBodyObject = new JSONObject();
        // jsonBodyObject.putOpt("body", jsonHeaderObject);
        jsonBodyObject.put("name", person.getName());
        jsonBodyObject.put("about", person.getAbout());
        jsonBodyObject.put("birthYear", person.getBirthYear());

        jsonObject.append("header", jsonHeaderObject);
        jsonObject.append("body", jsonBodyObject);

        String responseString = jsonObject.toString();
        try {
            exchange.sendResponseHeaders(200, responseString.getBytes().length);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        OutputStream output = exchange.getResponseBody();
        try {
            output.write(responseString.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            output.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}