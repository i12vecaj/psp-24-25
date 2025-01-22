
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
public class Cliente {
    public static void main(String[] args) throws IOException {
        try(Socket socket = new Socket()) {
            socket.connect(new java.net.InetSocketAddress("localhost", 8080));
            System.out.println("Conectado al servidor...");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce un mensaje: ");
            String mensaje = scanner.nextLine();

            System.out.println("Enviando mensaje...");
            socket.getOutputStream().write(mensaje.getBytes());

            System.out.println("Recibiendo mensaje en mayúsculas...");
            byte[] buffer = new byte[1024];
            int bytesRecibidos = socket.getInputStream().read(buffer);
            String mensajeRecibido = new String(buffer, 0, bytesRecibidos).toUpperCase();
            System.out.println("Recibido: " + mensajeRecibido);

        }


    }
}
