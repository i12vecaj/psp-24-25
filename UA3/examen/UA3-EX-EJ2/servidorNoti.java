import javax.xml.crypto.Data;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class servidorNoti {
    private static final String entregaSimultanea = "224.0.0.1";//Ip por la que realizara la entrega de las notificaciones.
    private static final int entregaPuertoCliente = 10001;//Puerto del lcliente.
    private static final int entregaPuertoAlertas = 10002;//Puerto de las Alertas.

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(entregaPuertoAlertas)){
            InetAddress grupo = InetAddress.getByName(entregaSimultanea);
            byte[] nuevoBuffer = new byte[1024];

            System.out.println("✅ Servidor de Notificaciones iniciado correctamente ✅");

            while (true) {
                DatagramPacket packet = new DatagramPacket(nuevoBuffer, nuevoBuffer.length);
                socket.setSoTimeout(3000);

                String mensaje;

                // Recibe datos de FuenteExterna
                try {
                    socket.receive(packet);
                    mensaje = new String(packet.getData(), 0, packet.getLength());
                } catch (Exception e) {
                    mensaje = "Todo está OK";
                }

                // Enviar mensaje a todos los clientes
                byte[] sendData = mensaje.getBytes();
                DatagramPacket multicastPacket = new DatagramPacket(sendData, sendData.length, grupo, entregaPuertoCliente);
                socket.send(multicastPacket);

                System.out.println("📢 Enviado: " + mensaje);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

