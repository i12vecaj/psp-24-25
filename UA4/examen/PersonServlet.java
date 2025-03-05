package org.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class PersonServlet extends HttpServlet {

    // 🔹 Constructor vacío necesario para Jetty
    public PersonServlet() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestUrl = request.getRequestURI();
        String name = requestUrl.substring("/people/".length());

        Person person = DataStore.getInstance().getPerson(name);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (person != null) {
            JSONObject json = new JSONObject();
            json.put("name", person.getName());
            json.put("about", person.getAbout());
            json.put("birthYear", person.getBirthYear());
            response.getWriter().write(json.toString());
        } else {
            response.getWriter().write("{}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String about = request.getParameter("about");
        int birthYear = Integer.parseInt(request.getParameter("birthYear"));

        DataStore.getInstance().putPerson(new Person(name, about, birthYear));

        response.getWriter().write("{\"message\": \"Person added successfully\"}");
    }
}
