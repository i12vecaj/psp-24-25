import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Recibir {
    public static void main(String[] args) throws IOException {
        byte[] bufer = new byte[1024]; //Creamos una matriz de bytes para recibir el datagrama codificado

        //Creamos un socket nuevo y lo asociamos al puerto creado en el envio
        DatagramSocket socket = new DatagramSocket(6000);

        DatagramPacket recibo = new DatagramPacket(bufer, bufer.length);//Creamos el datagram de recibo
        System.out.println("Esperando mensaje...");

        socket.receive(recibo); //Recibimos el datagram con el socket

        //Lo pasamos a string y obtenemos el numero de bytes que tiene
        String mensajeFinal = new String(recibo.getData(), 0, recibo.getLength());
        mensajeFinal = mensajeFinal.toUpperCase(); //Lo pasamos a mayusculas

        System.out.println("El mensaje era: "+mensajeFinal+"\n"); //Y lo mostramos
        socket.close();
    }

}


