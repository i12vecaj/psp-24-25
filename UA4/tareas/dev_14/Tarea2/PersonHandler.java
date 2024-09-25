import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.Date;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class PersonHandler extends BasicHandler {
    public PersonHandler(DataStore store) {
        super(store);
    }

    public void handle(HttpExchange exchange) throws IOException {
        String remoteAddress = exchange.getRemoteAddress().getHostString();
        System.out.println(
                "[" + new Date() + "] Received GET " + exchange.getRequestURI() + " from client: " + remoteAddress);

        if ("GET".equals(exchange.getRequestMethod())) {
            try {
                String responseString = "{";

                Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
                String personName = params.get("name");
                Person person = store.getPerson(personName);

                /*
                 * In the real world this part should be implemented this way:
                 * JSONObject jsonObject = new JSONObject();
                 * jsonObject.put("name", person.getName());
                 * jsonObject.put("about", person.getAbout());
                 * jsonObject.put("birthYear", person.getBirthYear());
                 * responseString = jsonObject.toString();
                 */
                responseString += "\"name\": \"" + person.getName() + "\",";
                responseString += "\"about\": \"" + person.getAbout() + "\",";
                responseString += "\"birthYear\": " + person.getBirthYear() + "";
                responseString += "}";
                exchange.sendResponseHeaders(200, responseString.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(responseString.getBytes());
                output.flush();
            } catch (Exception e) {
                String responseString = "Persona no disponible";
                exchange.sendResponseHeaders(404, responseString.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(responseString.getBytes());
                output.flush();
            }
        }

        else if ("POST".equals(exchange.getRequestMethod())) {
            // OBTENEMOS LOS DATOS DE LA URL

            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
            String personName = params.get("name");
            String personAbout = params.get("about");
            String personcCum = params.get("birthYear");
            String responseString = "{La persona con los siguientes datos: " + "name: " + personName + ", about: "
                    + personAbout + ", birthYear: " + personcCum + ", INSERTADA" + "}";
            int birthYearPerson = Integer.parseInt(personcCum);
            // CREAMOS EL OBJETO PERSONA

            Person persona = new Person(personName, personAbout, birthYearPerson);
            // GUARDAMOS EL OBJETO PERSONA
            store.putPerson(persona);
            // DEVOLVEMOS EL CODIGO 200
            exchange.sendResponseHeaders(200, responseString.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();
        }

        else if ("DELETE".equals(exchange.getRequestMethod())) {
            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
            String personName = params.get("name");
            String responseString = "{" + "name: " + personName + "CORRECTAMENTE BORRADA" + "}";
            store.deletePerson(personName);
            exchange.sendResponseHeaders(200, responseString.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();
        }

        else if ("PUT".equals(exchange.getRequestMethod())) {
            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
            String personName = params.get("name");
            String personAbout = params.get("about");
            String personcCum = params.get("birthYear");
            String responseString = "{La persona con los siguientes datos:" + "name: " + personName + ", about: "
                    + personAbout + ", birthYear: " + personcCum + ", MODIFICADA" + "}";
            int birthYearPerson = Integer.parseInt(personcCum);
            // CREAMOS EL OBJETO PERSONA
            Person persona = new Person(personName, personAbout, birthYearPerson);
            store.updatePerson(persona);
            exchange.sendResponseHeaders(200, responseString.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();
        } else {
            exchange.sendResponseHeaders(405, -1); 
        }
        exchange.close();
    }
}