import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();

            // Pedimos un mensaje al usuario para enviarlo al servidor
            Scanner scanner = new Scanner(System.in);
            System.out.print("Escribe un mensaje para enviarlo al servidor: ");
            String mensaje = scanner.nextLine();
            byte[] mensajeBytes = mensaje.getBytes();

            // DATOS DE CONEXION
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;

            // Enviamos el mensaje al servidor
            DatagramPacket packet = new DatagramPacket(mensajeBytes, mensajeBytes.length, serverAddress, serverPort);
            clientSocket.send(packet); // ¡Mandamos el mensaje como si fuera una carta!
            System.out.println("Mensaje enviado al servidor.");

            // Preparamos el buffer
            byte[] buffer = new byte[1024];
            DatagramPacket respuestaPacket = new DatagramPacket(buffer, buffer.length);

            // Recibimos la respuesta del servidor
            clientSocket.receive(respuestaPacket); // ¡Esperamos la carta de vuelta!
            String respuesta = new String(respuestaPacket.getData(), 0, respuestaPacket.getLength());
            System.out.println("Respuesta del servidor: " + respuesta);

            // Cerramos el socket
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}