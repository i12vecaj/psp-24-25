import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Random;

public class Servidor {
    private static int puerto = 12345;
    private static String ip_multicast = "230.0.0.0";

    public static void main(String[] args) {
        try (MulticastSocket socket = new MulticastSocket()) {

            System.out.println("-----------------------------------");
            System.out.println("Servidor de alertas activo en el puerto " + puerto);
            System.out.println("-----------------------------------");


            while (true) {
                String alerta = recibirAlertaExterna();

                if (alerta != null) {
                    enviarAlerta(alerta, socket);
                }

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String recibirAlertaExterna() {
        Random random = new Random();
        return String.valueOf(random.nextInt(200));
    }

    private static void enviarAlerta(String alerta, MulticastSocket socket) {
        try {
            byte[] buffer = alerta.getBytes();
            InetAddress grupo = InetAddress.getByName(ip_multicast);
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, grupo, puerto);
            socket.send(paquete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
