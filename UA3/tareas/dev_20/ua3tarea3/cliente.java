package dev_20.ua3tarea3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class cliente {
  public static void main(String[] args) throws IOException {
    String host = "127.0.0.1";
    int puerto = 6000;
    Socket socket = null;
    try{
      socket = new Socket(host, puerto);

      DataOutputStream datosSalida = new DataOutputStream(socket.getOutputStream());

      datosSalida.writeUTF("hola soy el cliente en minuscula");

      DataInputStream datosEntrada = new DataInputStream(socket.getInputStream());

      System.out.println("Resultado: " + datosEntrada.readUTF());

      datosSalida.close();
      datosEntrada.close();

    } catch (Exception e) {
      System.out.println("Error: " + e);
    }
    finally {
      if(socket != null){
        socket.close();
      }
    }
  }
}
