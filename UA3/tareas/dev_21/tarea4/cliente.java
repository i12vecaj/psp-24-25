import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class cliente {public static void main(String[] args) {
    final String SERVIDOR = "localhost";
    final int PUERTO = 5000;
    final int TIMEOUT = 5000;  // 5 segundos de espera

    try (DatagramSocket socket = new DatagramSocket()) {
        socket.setSoTimeout(TIMEOUT);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cliente UDP iniciado. Escribe un mensaje ('*' para salir):");

        while (true) {
            System.out.print("> ");
            String mensaje = scanner.nextLine();

            byte[] datos = mensaje.getBytes();
            DatagramPacket paquete = new DatagramPacket(datos, datos.length, InetAddress.getByName(SERVIDOR), PUERTO);
            socket.send(paquete);

            if (mensaje.equals("*")) {
                System.out.println("Cliente desconectado.");
                break;
            }

            byte[] buffer = new byte[1024];
            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);

            try {
                socket.receive(respuesta);
                String recibido = new String(respuesta.getData(), 0, respuesta.getLength());
                System.out.println("Servidor responde: " + recibido);
            } catch (SocketTimeoutException e) {
                System.out.println("Tiempo de espera agotado. Paquete perdido.");
            }
        }
    } catch (Exception e) {
        System.err.println("Error en el cliente: " + e.getMessage());
    }
}

}
