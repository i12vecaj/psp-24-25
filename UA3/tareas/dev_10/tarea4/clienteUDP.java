package tarea4;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;
  public class clienteUDP {
    public static void main(String[] args) throws IOException {
      Scanner scanner = new Scanner(System.in);

      String frMinusculaDelCliente;
      int puertoCliente = 3500;

      InetAddress localHost = InetAddress.getLocalHost();
      while (true){

        byte[] paquete = new byte[1024];

        System.out.println("Envie un mensaje al servidor: ");
        frMinusculaDelCliente = scanner.nextLine();

        paquete = frMinusculaDelCliente.getBytes();

        DatagramPacket enviarFrase = new DatagramPacket(paquete,paquete.length,localHost,puertoCliente);

        DatagramSocket socket = new DatagramSocket(16000);

        socket.send(enviarFrase);

        socket.close();
      }
    }
  }

