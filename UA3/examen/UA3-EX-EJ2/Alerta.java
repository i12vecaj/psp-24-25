import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Alerta {
    private static final String ipServidor = "127.0.0.1"; //ip del servidor.
    private static final int puertoServidor = 10002; //Puerto por el que mandaremos el mensaje de alerta al servidor.

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            InetAddress serverAddress = InetAddress.getByName(ipServidor);

            System.out.println("Escribe 'Alerta' para enviar una notificación:");

            while (true) {
                String input = scanner.nextLine();
                //Espera a que el usuario introduzca la palabra Alerta para mandar una alerta al servidor.
                if (input.equalsIgnoreCase("Alerta")) {
                    byte[] buffer = input.getBytes();
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, puertoServidor);
                    socket.send(packet);
                    System.out.println("⚠️  Alerta enviada al servidor.");
                } else {
                    System.out.println("Comando no reconocido. Tienes que escribe 'Alerta' para poder enviar.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
