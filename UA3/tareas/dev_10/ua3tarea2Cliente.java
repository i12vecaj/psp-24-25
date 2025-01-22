import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ua3tarea2Cliente {
    public static void main(String[] args) {

      String servidorIP = "127.0.0.1";
      int servidorPuerto = 3307;

      try (Socket socket = new Socket(servidorIP, servidorPuerto)) {
        System.out.println("Conectado al servidor " + servidorIP + " en el puerto " + servidorPuerto);

        System.out.println("  - Puerto local (Cliente): " + socket.getLocalPort());
        System.out.println("  - Puerto remoto (Servidor): " + socket.getPort());
        System.out.println("  - IP remota (Servidor): " + socket.getInetAddress());


      } catch (UnknownHostException error) { // UnknownHostException indica que no se pudo resolver el nombre del host (DNS).
        System.err.println("No se pudo resolver el host: " + error.getMessage());
      } catch (IOException error) { // IOException captura errores de entrada/salida más generales.
        System.err.println("Error de E/S: " + error.getMessage());
      }
    }
  }

