import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class JSSEExample {
    public static void main(String[] args) throws Exception {
        // URL segura con HTTPS
        URL url = new URL("https://www.google.com");
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

        // Leer respuesta
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine).append("\n");
        }
        in.close();

        // Mostrar contenido de la página
        System.out.println(response.toString().substring(0, 500)); // Muestra solo los primeros 500 caracteres
    }
}