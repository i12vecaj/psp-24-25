import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class EjemploJSSE {
    public static void main(String[] args) {
        try {
            // URL de ejemplo que utiliza HTTPS
            URL url = new URL("https://www.example.com");
            HttpsURLConnection conexion = (HttpsURLConnection) url.openConnection();

            // Leer la respuesta
            BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String inputLine;
            StringBuilder contenido = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                contenido.append(inputLine);
            }
            in.close();

            // Mostrar el contenido de la respuesta
            System.out.println("Contenido de la respuesta:");
            System.out.println(contenido.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
