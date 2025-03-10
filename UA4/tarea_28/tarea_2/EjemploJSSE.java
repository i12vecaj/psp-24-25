import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class EjemploJSSE {
    public static void main(String[] args) {
        try {

            URL url = new URL("https://ejemplo.com");


            HttpsURLConnection conexion = (HttpsURLConnection) url.openConnection();


            conexion.setRequestMethod("GET");


            BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String linea;
            StringBuilder respuesta = new StringBuilder();
            while ((linea = lector.readLine()) != null) {
                respuesta.append(linea);
            }
            lector.close();


            System.out.println("Respuesta del servidor: " + respuesta.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

