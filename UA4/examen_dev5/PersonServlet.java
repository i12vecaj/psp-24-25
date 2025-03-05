import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class PersonServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestUrl = request.getRequestURI();
        String name = requestUrl.substring("/people/".length());

        Person person = DataStore.getInstance().getPerson(name);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if(person != null) {
            String json = "{\n";
            json += "\"name\": " + JSONObject.quote(person.getName()) + ",\n";
            json += "\"about\": " + JSONObject.quote(person.getAbout()) + ",\n";
            json += "\"birthYear\": " + person.getBirthYear() + "\n";
            json += "}";
            response.getWriter().println(json);
        } else {
            response.getWriter().println("{}");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String about = request.getParameter("about");
        int birthYear = Integer.parseInt(request.getParameter("birthYear"));

        DataStore.getInstance().putPerson(new Person(name, about, birthYear));

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("{\"status\": \"success\", \"message\": \"Persona agregada\"}");
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestUrl = request.getRequestURI();
        String name = requestUrl.substring("/people/".length());

        boolean removed = DataStore.getInstance().removePerson(name);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (removed) {
            response.getWriter().println("{\"status\": \"success\", \"message\": \"Persona eliminada\"}");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Persona no encontrada");
        }
    }
}
