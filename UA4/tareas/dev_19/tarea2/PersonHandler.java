import java.io.OutputStream;
import java.util.Map;
import java.io.IOException;
import java.util.Date;
import com.sun.net.httpserver.HttpExchange;

class PersonHandler extends BasicHandler {
    public PersonHandler(DataStore store) {
        super(store);
    }

    public void handle(HttpExchange exchange) throws IOException {
        try {
            String remoteAddress = exchange.getRemoteAddress().getHostString();
            System.out.println(
                    "[" + new Date() + "] Received " + exchange.getRequestMethod() + " " + exchange.getRequestURI() +
                            " from client: " + remoteAddress);

            if ("GET".equals(exchange.getRequestMethod())) {
                String responseString = "{";

                Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
                String personName = params.get("name");
                Person person = store.getPerson(personName);

                if (person == null) {
                    responseString += "Info not found";
                    responseString += "}";
                    exchange.sendResponseHeaders(404, responseString.getBytes().length);
                    OutputStream output = exchange.getResponseBody();
                    output.write(responseString.getBytes());
                    output.flush();
                } else {
                    responseString += "\"name\": \"" + person.getName() + "\",";
                    responseString += "\"about\": \"" + person.getAbout() + "\",";
                    responseString += "\"birthYear\": " + person.getBirthYear() + "";
                    responseString += "}";
                    exchange.sendResponseHeaders(200, responseString.getBytes().length);
                    OutputStream output = exchange.getResponseBody();
                    output.write(responseString.getBytes());
                    output.flush();
                }

            } else if ("POST".equals(exchange.getRequestMethod())) {
                String responseString = "{";
                /* 1. obtengo los datos de la persona de la URL */
                Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
                String personaNombre = params.get("name");
                String personaInformacion = params.get("about");
                int personaNacimiento = Integer.parseInt(params.get("birthYear"));
                /* 2. creo el objeto persona */
                Person persona = new Person(personaNombre, personaInformacion, personaNacimiento);
                responseString += "\"name\": \"" + persona.getName() + "\",";
                responseString += "\"about\": \"" + persona.getAbout() + "\",";
                responseString += "\"birthYear\": " + persona.getBirthYear() + "";
                responseString += "}";
                if (persona.getName().equals("")) {
                    throw new HttpException(400, "Bad Request");
                } else {
                    /* 3. Guardo la nueva persona en el almacen */
                    store.putPerson(persona);
                    /* 4. Devuelvo 200 OK */
                    exchange.sendResponseHeaders(200, responseString.getBytes().length);
                    OutputStream output1 = exchange.getResponseBody();
                    output1.write(responseString.getBytes());
                    output1.flush();
                }
            } else if ("PUT".equals(exchange.getRequestMethod())) {
                String responseString = "{";
                Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
                String personaNombre = params.get("name");
                String personaInformacion = params.get("about");
                int personaNacimiento = Integer.parseInt(params.get("birthYear"));

                Person personaPUT = new Person(personaNombre, personaInformacion, personaNacimiento);
                responseString += "\"name\": \"" + personaPUT.getName() + "\",";
                responseString += "\"about\": \"" + personaPUT.getAbout() + "\",";
                responseString += "\"birthYear\": " + personaPUT.getBirthYear() + "";
                responseString += "}";
                if (personaPUT.getName().equals("")) {
                    throw new HttpException(400, "Bad Request");
                } else {

                    store.putPerson(personaPUT);

                    exchange.sendResponseHeaders(200, responseString.getBytes().length);
                    OutputStream output1 = exchange.getResponseBody();
                    output1.write(responseString.getBytes());
                    output1.flush();
                }
            } else if ("DELETE".equals(exchange.getRequestMethod())) {
                String responseString = "";
                Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
                String personaNombre = params.get("name");

                store.deletePerson(personaNombre);

                exchange.sendResponseHeaders(200, responseString.getBytes().length);
                OutputStream output1 = exchange.getResponseBody();
                output1.write(responseString.getBytes());
                output1.flush();
            } else {
                throw new HttpException(405, "Method Not Allowed");
            }
        } catch (HttpException e) {
            /*
             * si se produce una excepción de tipo HttpException, se envía la respuesta de
             * error correspondiente
             */
            sendErrorResponse(exchange, e.getStatus(), e.getMessage());
        } catch (Exception e) {
            /*
             * si se produce una excepción no controlada, se envía una respuesta de error
             * genérica
             */
            sendErrorResponse(exchange, 400, "Bad Request");
            e.printStackTrace();
        } finally {
            exchange.close();
        }
    }

    /* envía una respuesta de error al cliente */
    private void sendErrorResponse(HttpExchange exchange, int status, String message) throws IOException {
        exchange.sendResponseHeaders(status, message.getBytes().length);
        OutputStream output = exchange.getResponseBody();
        output.write(message.getBytes());
        output.flush();
    }
}

class HttpException extends Exception {
    private final int status;

    public HttpException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
