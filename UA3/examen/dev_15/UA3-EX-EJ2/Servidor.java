import java.net.*;
import java.util.Scanner;

public class Servidor {

    //DATOS DE CONEXION
    private static final int PORT = 33;

    public static void main(String[] args) {
        //INICIO DEL SERVIDOR
        try (DatagramSocket socket = new DatagramSocket()) {
            Scanner scanner = new Scanner(System.in);
            InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");

            //MENSAJE DE INICIO DEL SERVIDOR
            System.out.println("Servidor de notificaciones iniciado...");
            while (true) {
                System.out.print("Escribe un mensaje de alerta: ");
                String message = scanner.nextLine();
                byte[] buffer = message.getBytes();

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, broadcastAddress, PORT);
                socket.send(packet);
                System.out.println("Notificación enviada: " + message);
            }
        } catch (Exception e) {
            //MENSAJE DE ERROR EN CASO DE FALLO
            e.printStackTrace();
        }
    }
}