import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        final String multicastIP = "230.0.0.1"; // Debe coincidir con el servidor
        final int puerto = 10000; // Debe coincidir con el servidor

        try (MulticastSocket socket = new MulticastSocket(puerto)) {
            InetAddress group = InetAddress.getByName(multicastIP);// Obtener la dirección IP del servidor
            socket.joinGroup(group);// Unirse al grupo multicast

            System.out.println("Cliente conectado. Esperando mensajes...");

            byte[] receiveData = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);//esperamos a un mensaje

                String mensaje = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Mensaje recibido: " + mensaje);//mostramos el mensaje

                if (mensaje.equalsIgnoreCase("SALIR")) {// Si el mensaje es "SALIR", salimos del bucle
                    System.out.println("Cerrando cliente...");
                    break;
                }
            }

            socket.leaveGroup(group);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
