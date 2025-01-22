import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {

    public static void main(String[] args) throws IOException {

        //Se crea un socket UDP en el puerto 6000 para que el servidor escuche las conexiones.
        DatagramSocket serverSocket = new DatagramSocket(6000);

        //Mensaje indicando que el servidor está en espera de recibir datos.
        System.out.println("Servidor esperando...");
        DatagramPacket paquete;

        //Buffer donde se almacenarán los datos recibidos desde el cliente.
        byte[] buffer = new byte[1024];
        paquete = new DatagramPacket(buffer, buffer.length);

        //El servidor se queda bloqueado esperando un paquete UDP entrante.
        serverSocket.receive(paquete);

        //Se convierte el mensaje recibido (byte[]) en un String y se elimina el espacio sobrante.
        String mensajeRecibido = new String(paquete.getData(), 0, paquete.getLength()).trim();
        System.out.println("Servidor recibió: " + mensajeRecibido);

        //El mensaje recibido se convierte a mayúsculas como respuesta.
        String mensajeMayus = mensajeRecibido.toUpperCase();

        //Se obtienen la dirección IP y el puerto del cliente que envió el mensaje.
        InetAddress clienteIP = paquete.getAddress();
        int puertoCliente = paquete.getPort();

        //El mensaje en mayúsculas se convierte en un byte[] para enviarlo de vuelta al cliente.
        byte[] enviar = mensajeMayus.getBytes();

        //Se crea un paquete UDP para enviar la respuesta al cliente.
        DatagramPacket paqueteEnviado = new DatagramPacket(enviar, enviar.length, clienteIP, puertoCliente);

        //Se envía la respuesta al cliente.
        System.out.println("Enviando respuesta: " + mensajeMayus);
        serverSocket.send(paqueteEnviado);

        //Se cierra la conexión del socket una vez finalizada la comunicación.
        System.out.println("Cerrando conexión...");
        serverSocket.close();
    }
}