import java.net.*;
import java.util.Scanner;

public class ServidorUDP {
    public static void main(String[] args) {
        final String multicastIP = "230.0.0.1"; // Dirección de multicast
        final int puerto = 10000; // Puerto de comunicación

        try (MulticastSocket socket = new MulticastSocket()) {//creamos un socket multicast
            InetAddress group = InetAddress.getByName(multicastIP);//creamos un grupo multicast
            Scanner scanner = new Scanner(System.in);

            System.out.println("Servidor iniciado...");

            while (true) {
                System.out.print("Escribe un mensaje: ");
                String mensaje = scanner.nextLine();

                byte[] sendData = mensaje.getBytes();
                DatagramPacket packet = new DatagramPacket(sendData, sendData.length, group, puerto);//creamos un paquete multicast
                socket.send(packet);//enviamos el paquete

                if (mensaje.equalsIgnoreCase("SALIR")) {
                    System.out.println("Servidor apagado.");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
