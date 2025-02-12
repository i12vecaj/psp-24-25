import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ChatCliente1 {

    public static void main(String[] args) throws IOException {

        // Creamos un socket para enviar y recibir mensajes
        DatagramSocket socket = new DatagramSocket();   
        byte[] buffer = new byte[1024];
        byte[] envio = new byte[1024];
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
        // Cerrar socket
        socket.close();
    }
}
            
