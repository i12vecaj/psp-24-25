/*
   ¿Es más conveniente utilizar TCP o UDP en este caso? Explica tu respuesta.


   Es mas conveniente utilizar UDP en este caso. Ya que nos han especificado que no es crítico que todas las notificaciones lleguen en orden
   o sean confirmadas, pero sí que sean enviadas de manera eficiente.

   Es decir, utilizar TCP te hace un medio de comunicacion más seguro que UDP por varios motivos, uno de ellos
   puede ser la necesidad de tener a dos dispositivos conectado como minimo para poder empezar una comunicacion, otra es que garantiza la llegada de
   esos paquetes enviados; en cambio con UDP puede ser que se pierdan por el camino, pero es un sistema más rapido.

*/

import java.net.*;
import java.util.Scanner;

public class Main {
    private static final int puerto = 10005;
    private static final String conexionIp = "255.255.255.255";

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setBroadcast(true);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Servidor de Notificaciones Iniciado...");

            while (true) {
                System.out.print("Ingrese notificación: ");
                String message = scanner.nextLine();
                byte[] buffer = message.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(conexionIp), puerto);
                socket.send(packet);
                System.out.println("Notificación enviada!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class NotificationCliente {
    private static final int puerto = 10005;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            byte[] buffer = new byte[1024];
            System.out.println("Cliente escuchando notificaciones...");

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Notificación recibida: " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
