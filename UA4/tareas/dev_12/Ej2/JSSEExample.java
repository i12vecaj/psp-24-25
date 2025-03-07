package UA5_T1;
import javax.net.ssl.HttpsURLConnection;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.URL;

public class JSSEExample {
    public static void main(String[] args) throws Exception {
        // URL del servidor HTTPS
        URL url = new URL("https://www.google.com");
        
        // Abrir la conexión HTTPS
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        
        // Leer la respuesta del servidor
        BufferedReader in = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        
        // Imprimir la respuesta
        System.out.println(response.toString());
    }
}
