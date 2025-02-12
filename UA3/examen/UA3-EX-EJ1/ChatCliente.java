import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ChatCliente {

    public static void main(String[] args) throws IOException {

        // Creamos un socket para enviar y recibir mensajes
        DatagramSocket socket = new DatagramSocket();

        // Creamos un búfer para recibir mensajes
        byte[] buffer = new byte[1024];

        // Creamos un búfer para enviar mensajes
        byte[] envio = new byte[1024];

        // Creamos un Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Pedimos al usuario que introduzca un mensaje
            System.out.print("Introduce un mensaje: ");
            String mensaje = scanner.nextLine();

            // Codificamos el mensaje en bytes
            envio = mensaje.getBytes();

            // Creamos un paquete de datagrama con el mensaje y lo enviamos al servidor
            DatagramPacket packet = new DatagramPacket(envio, envio.length, InetAddress.getLocalHost(), 12345);
            socket.send(packet);

            // Si el mensaje es "SALIR", salimos del bucle
            if (mensaje.equals("Cerrar")) {
                break;
            }
        }
        // Cerramos el socket
        socket.close();
    }
}
            
