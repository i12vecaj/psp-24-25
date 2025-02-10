package tarea5;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
      DatagramSocket socket = new DatagramSocket(3500);
      InetAddress localHost = InetAddress.getLocalHost();

      byte[] buffer = new byte[1024];

      DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);

      socket.receive(paquete);

      ByteArrayInputStream bais = new ByteArrayInputStream(paquete.getData());
      ObjectInputStream ois = new ObjectInputStream(bais);
      Numeros numRecibido = (Numeros) ois.readObject();
      ois.close();

      System.out.println("Numero Entero:" + numRecibido.getEntero()+", Numero Grande 1: "+ numRecibido.getCuadrado()+", Numero largo 2: "+numRecibido.getCubo());


      int numCuadrado = (int) Math.pow(numRecibido.getEntero(),2);

      int numeroCubo = (int) Math.pow(numRecibido.getCubo(),3);


      numRecibido.setCuadrado(numCuadrado);
      numRecibido.setCubo(numeroCubo);

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(baos);
      oos.writeObject(numRecibido);
      oos.close();

      byte[] datos = baos.toByteArray();

      DatagramPacket paqueteEnvio = new DatagramPacket(datos, datos.length, localHost, 2500);
      socket.send(paqueteEnvio);

      System.out.println("Paquete enviado");


      socket.close();


    }
  }