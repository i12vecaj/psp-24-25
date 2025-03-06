package org.example;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class JSSEEjemplo {
    public static void main(String[] args) throws Exception {
        // URL a la que se hará la conexión HTTPS
        URL url = new URL("https://www.example.com");

        // Abrir la conexión HTTPS
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        // Configuración de la conexión
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        // Leer la respuesta
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Mostrar la respuesta de la conexión HTTPS
        System.out.println(response.toString());
    }
}