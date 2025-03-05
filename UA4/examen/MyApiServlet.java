package com.example.restapi;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyApiServlet extends HttpServlet {
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        // Crear un objeto Java
        MyData data = new MyData("Hello, World!", 42);

        // Convertir a JSON
        String json = gson.toJson(data);
        resp.getWriter().println(json);
    }
}

class MyData {
    private String message;
    private int number;

    public MyData(String message, int number) {
        this.message = message;
        this.number = number;
    }
}
