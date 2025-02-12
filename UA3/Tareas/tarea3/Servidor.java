import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServidor {
    public static void main(String[] args) {
        final int PUERTO = 6000;
        byte[] buffer = new byte[1024];

        try {

            DatagramSocket socket = new DatagramSocket(PUERTO);
            System.out.println("Servidor UDP escuchando en el puerto " + PUERTO + "...");

            while (true) {

                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);


                String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                InetAddress direccionCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();

                System.out.println("Mensaje recibido de " + direccionCliente.getHostAddress() + ":" + puertoCliente + " -> " + mensaje);


                String respuesta = mensaje.toUpperCase();
                byte[] mensajeEnviado = respuesta.getBytes();


                DatagramPacket paqueteEnviado = new DatagramPacket(mensajeEnviado, mensajeEnviado.length, direccionCliente, puertoCliente);
                socket.send(paqueteEnviado);

                System.out.println("Respuesta enviada: " + respuesta);
            }
        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}