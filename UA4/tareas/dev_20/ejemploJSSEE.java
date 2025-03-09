import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class ejemploJSSEE {
    public static void main(String[] args) throws Exception {

        //En el caso de los JSSEE genera conexiones HTTPS los cuales son conexiones seguras
        String httpsURL = "https://www.google.com";

        // Crear conexión HTTPS
        URL url = new URL(httpsURL);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Leer respuesta del servidor
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Imprimir una parte de la respuesta
        System.out.println("Respuesta HTTPS: " + response.substring(0, 100) + "...");
    }
}
