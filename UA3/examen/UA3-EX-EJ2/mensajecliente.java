import java.net.*;
// ALEJANDRO SANCHEZ QUESADA
// EN MI CASO HE USADO UDP PORQUE: es mucho mas rapido que tcp ya que puede enviar multiples mensajes a clientes sin que tenga que confirmar ya que el tcp si tendria que confirma y seria mas lento , tambien tcp tiene un orden y llegaria mas lento.
public class mensajecliente {
    public static void main(String[] args) throws Exception {
        int puerto = 12345; // este puerto es el porque servidor debe de escuchar
        DatagramSocket socket = new DatagramSocket(puerto);
        byte[] buffer = new byte[1024]; // aqui hago un buffer para que pueda recibir los mensajes

        InetAddress direccionServidor = InetAddress.getByName("localhost");

        while (true) {

            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquete);


            String mensaje = new String(paquete.getData(), 0, paquete.getLength()); // lo covierto a string y lo muestro
            System.out.println("esta la notificacion recibida: " + mensaje);
        }
    }
}

