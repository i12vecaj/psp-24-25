import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Envio {
    public static void main(String[] args) throws UnknownHostException {

        Scanner scanner = new Scanner(System.in);//Declaramos un escaner para recoger lo que ponga el usuario
        String mensaje; //Creamos esta vaiarble para almacenar la informacion

        int puerto =  6000; //Asignamos un puerto aleatorio

        System.out.println("Indique el mensaje que desea enviar:\n");
        mensaje = scanner.nextLine(); //Recogemos el mensaje

        InetAddress destino = InetAddress.getLocalHost();//IP host local

        byte[]  mensajeCodi; //Creamos una matriz de bytes
        mensajeCodi = mensaje.getBytes(); //Y ahora codificamos el mensaje a bytes

        //Construimos el datagram con toda la informacion
        DatagramPacket envio = new DatagramPacket(mensajeCodi, mensajeCodi.length, destino, puerto);

        try {
            DatagramSocket socket = new DatagramSocket(5000); //Creamos un socket
            socket.send(envio); //Y en el envio le asignamos el datagrama
            System.out.println("El mensaje se ha enviado correctamente.");
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}