import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class PersonServlet extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String requestUrl = request.getRequestURI();
        int id = Integer.parseInt(requestUrl.substring("/personas/".length()));

        Person person = DataStore.getInstance().getPerson(id);

        if (person != null) {
            String json = "{\n";
            json += "\"id\": " + person.getId() + ",\n";
            json += "\"name\": " + JSONObject.quote(person.getName()) + ",\n";
            json += "\"about\": " + JSONObject.quote(person.getAbout()) + ",\n";
            json += "\"birthYear\": " + person.getBirthYear() + "\n";
            json += "}";
            response.getOutputStream().println(json);
        } else {
            response.getOutputStream().println("{}"); // Si no existe, devuelve un JSON vacío
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try{
            String name = request.getParameter("name");
            String about = request.getParameter("about");
            int birthYear = Integer.parseInt(request.getParameter("birthYear"));

            DataStore.getInstance().putPerson(name, about, birthYear);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
