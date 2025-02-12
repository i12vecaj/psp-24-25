

//como observaras jose david los dos codigos son iguales , pero porque la gestion de envios tanto con conexiones como sin conexiones el udp lo gestiona muy bien , este caso he invertido los papeles y he cambiado la ip a un ip statica para que la gestion de envios de mensajes rapidos se haga directamente sobre el servidor en la ip que se da ,es por donde accede el servidor  asi que el cliente solo esta a la escucha con la escusa de estar conectado

/*
/*Pregunta para el estudiante:
¿Es más conveniente utilizar TCP o UDP en este caso? Explica tu respuesta.
*  usar udp es mas conveniente a la hora de mensajeria rapdia , y sin necesidad de tener un cliente conectado continuamente
* */
import java.net.*;
import java.io.*;
import java.util.Scanner;
//primeramente este es el servidor donde todo lo vamos a hacer
public class UDPClientes {

  public static void main(String[] args) {
    //definimos el puerto por el que vamos a hacer el chat el cual no puede pertenecer a los 1024 primeros puertos tiene que ser un puerto alto
    final String PORT= "192.158.1.38";
    final int PORTo = 8000;
    byte[] buffer = new byte[1024];
//iniciamos un control de errores el cual nos permite que el codigo se ejecute perfectamente
    try (DatagramSocket serverSocket = new DatagramSocket(PORTo)) {
      System.out.println("Cliente escuchando al servidor por : " + PORT);

      while (true) {
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        serverSocket.receive(receivePacket);

        //aqui con estas dos siguienetes linea recogemos el mensaje y decimos de quien es el mensaje el cual sera del servidor proque tendra acceso desde una ip estatica en este caso lo gestionamos por ip la cual es una que podran acceeder los clientes.
        String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Mensaje recibido de " + receivePacket.getAddress() + ": " + message);
        //ordenes de cerrado
        if (message.equals("*")) {
          System.out.println("Se recibió la orden 66 , cerrando cliente...");
          break;
        }
        if(message.equals( "salir")){
          System.out.println("Se recibió la orden 67, cerrando cliente...");
          break;
        }
        String response = message.toLowerCase();

        byte[] responseData = response.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(responseData, responseData.length, receivePacket.getAddress(), receivePacket.getPort());
        serverSocket.send(sendPacket);
      }
    } catch (IOException e) {
      System.out.println("Error en el cliente: " + e.getMessage());
    }
  }
}
//este es el cliente
class UDPServer {
  public static void main(String[] args) {
    //difinimos los puertos
    final String SERVER_ADDRESS = "192.158.1.38";
    final int SERVER_PORT = 8000;
    byte[] buffer = new byte[1024];
    Scanner scanner = new Scanner(System.in);
 //empezamos con el control de errores
    try (DatagramSocket clientSocket = new DatagramSocket()) {
      clientSocket.setSoTimeout(5000);
      System.out.println("Servidor listo. Escriba mensajes para enviar (use '*' para salir o ponga salir):");
//enviamos el mensaje que nosotros queremos dar
      while (true) {
        System.out.print("Mensaje: ");
        String message = scanner.nextLine();

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(SERVER_ADDRESS), SERVER_PORT);
        clientSocket.send(sendPacket);

        if (message.equals("*")) {
          System.out.println("Se recibió  la orden 66, cerrando servidor...");
          break;
        }
        if (message.equalsIgnoreCase("salir")) {
          System.out.println("Se recibió  la orden 66, cerrando servidor...");
          break;
        }

        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        try {
          clientSocket.receive(receivePacket);
          String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
          System.out.println("Respuesta del cliente: " + receivedMessage);
        } catch (SocketTimeoutException e) {
          System.out.println("Tiempo de espera agotado. El cliente  no responde.");
        }
      }
    } catch (IOException e) {
      System.out.println("Error en el servidor : " + e.getMessage());
    }
  }
}
