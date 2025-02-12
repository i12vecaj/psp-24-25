package UA3_EX_EJ2;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Miembro {
    private MulticastSocket socket;
    private InetAddress grupo;
    private int puerto;

    public Miembro(String multicastAddress, int puerto) {
        try {
            this.puerto = puerto;
            socket = new MulticastSocket(puerto);
            grupo = InetAddress.getByName(multicastAddress);
            socket.joinGroup(grupo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void esperandoNotificaciones() {
        try {
            System.out.println("Esperando notificaciones...");
            while (true) {
                byte[] buffer = new byte[256];
                DatagramPacket pakete = new DatagramPacket(buffer, buffer.length);
                socket.receive(pakete);
                String mrecivido = new String(pakete.getData(), 0, pakete.getLength());
                System.out.println("Recivido: " + mrecivido);
                if (mrecivido.equalsIgnoreCase("salir")) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}