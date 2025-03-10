import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class JSSEExample {
  public static void main(String[] args) {
    try {
      // URL con HTTPS
      URL url = new URL("https://www.google.com");

      // Abrimos conexión SSL
      HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
      con.setRequestMethod("GET");

      // Leer la respuesta del servidor
      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine).append("\n");
      }
      in.close();

      System.out.println("Respuesta del servidor:");
      System.out.println(response.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

