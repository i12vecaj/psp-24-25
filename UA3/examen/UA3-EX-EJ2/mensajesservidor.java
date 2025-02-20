import java.net.*;
////Revisado por JD: 20/02/2025 
//ALEJANDRO SANCHEZ QUESADA
// EN MI CASO HE USADO UDP PORQUE: es mucho mas rapido que tcp ya que puede enviar multiples mensajes a clientes sin que tenga que confirmar ya que el tcp si tendria que confirma y seria mas lento , tambien tcp tiene un orden y llegaria mas lento.
public class mensajesservidor {
    public static void main(String[] args) throws Exception {
        int puerto = 12345;
        DatagramSocket socket = new DatagramSocket(puerto);
        System.out.println("servidor inciado estoy esperando alguna alerta.");

        // aqui le pongo direccion de los clientes
        InetAddress cliente2 = InetAddress.getByName("localhost");

        byte[] mensaje; // este es el mensaje que voy enviar

        while (true) {

            String alerta = "HAY UNA REUNION EN 5 MIN";
            mensaje = alerta.getBytes();


            DatagramPacket paquete1 = new DatagramPacket(mensaje, mensaje.length, cliente1, puerto); //Hago que envie la alerta a los clientes
            DatagramPacket paquete2 = new DatagramPacket(mensaje, mensaje.length, cliente2, puerto);


            socket.send(paquete1);
            socket.send(paquete2);

            System.out.println("esta notificacon ha sido enviadda: " + alerta);


            Thread.sleep(5000);
        }
    }
}

