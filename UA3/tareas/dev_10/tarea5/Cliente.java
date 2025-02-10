package tarea5;

import java.io.*;
import java.net.*;
import java.util.Scanner;


  public class Cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
      Scanner scanner = new Scanner(System.in);

      DatagramSocket socket = null;

      int puerto = 3500;

      InetAddress localHost = InetAddress.getLocalHost();
      System.out.println("Introduce un numero entero.");
      int numEntero = scanner.nextInt();

      Numeros numObjeto = new Numeros(numEntero, numEntero, numEntero);

      socket = new DatagramSocket(2500);

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(baos);
      oos.writeObject(numObjeto);
      oos.close();

      byte[] datos = baos.toByteArray();

      DatagramPacket paqueteClienteenviado = new DatagramPacket(datos, datos.length, localHost, puerto);
      socket.send(paqueteClienteenviado);


      byte[] buffer = new byte[1024];

      DatagramPacket paqueteServidorRecivido = new DatagramPacket(buffer, buffer.length);
      socket.receive(paqueteServidorRecivido);

      ByteArrayInputStream bais = new ByteArrayInputStream(paqueteServidorRecivido.getData());
      ObjectInputStream ois = new ObjectInputStream(bais);
      Numeros numRecibido = (Numeros) ois.readObject();
      ois.close();

      System.out.println("Objeto enviado: Numero entero:  " + numRecibido.getEntero()+", Numero cuadrado: "+ numRecibido.getCuadrado()+",Numero Cubo: "+ numRecibido.getCubo());

      socket.close();


    }
  }