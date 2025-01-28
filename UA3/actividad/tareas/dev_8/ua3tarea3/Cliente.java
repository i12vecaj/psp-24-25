import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            Scanner scanner = new Scanner(System.in);
            boolean activo = true;

            do {
                System.out.print("Introduce un mensaje: ");
                String mensaje = scanner.nextLine();
                byte[] buffer = mensaje.getBytes();

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 9876);
                socket.send(packet);

                byte[] bufferRecibido = new byte[1024];
                DatagramPacket packetRecibido = new DatagramPacket(bufferRecibido, bufferRecibido.length);
                socket.receive(packetRecibido);

                String mensajeRecibido = new String(packetRecibido.getData(), 0, packetRecibido.getLength());
                System.out.println("Mensaje recibido del servidor: " + mensajeRecibido);

                System.out.print("¿Desea enviar otro mensaje? (s/n): ");
                String respuesta = scanner.nextLine();
                if (!respuesta.equalsIgnoreCase("s")) {
                    activo = false;
                }
            } while (activo);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}