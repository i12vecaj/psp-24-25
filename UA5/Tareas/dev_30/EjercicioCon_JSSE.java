import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class EjercicioCon_JSSE {
    public static void main(String[] args) throws Exception {
        // Enlace HTTPS donde nos conectemos
        URL url = new URL("https://www.example.com");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        //configuramos el tipo de conexion que será
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        // Muestro el contenido de la respuesta
        System.out.println("Respuesta del servidor: " + content.toString());
    }
}
