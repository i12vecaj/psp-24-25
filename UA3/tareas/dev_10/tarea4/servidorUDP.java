package tarea4;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class servidorUDP {
  public static void main(String[] args) throws IOException {
    String frMayusculas = "";
    DatagramSocket socket = new DatagramSocket(3500);

    do{
      byte[] buffer = new byte[300];

      try {

        DatagramPacket paqueteCliente = new DatagramPacket(buffer,buffer.length);
        System.out.println("Esperando a un datagrama....");
        socket.receive(paqueteCliente);

        String paqueteRecibido = new String(paqueteCliente.getData(),0, paqueteCliente.getLength());
        frMayusculas = paqueteRecibido.toUpperCase();

        System.out.println(frMayusculas);

      }catch (IOException error){
        System.out.println("Se han perdido los paquetes. "+ error);
      }
    }while (!frMayusculas.equals("*"));

  }
}
