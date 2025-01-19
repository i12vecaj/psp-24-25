package dev_20.ua3tarea2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente2 {
  public static void main(String[] args) throws IOException {
    String host = "127.0.0.1";
    int puerto = 6000;
    Socket cliente = null;
    try{

      cliente = new Socket(host,puerto);

      InetAddress ipLocal = cliente.getLocalAddress();
      InetAddress ipServidor = cliente.getInetAddress();

      DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
      salida.writeUTF("{puertoLocal:" + cliente.getLocalPort()
              + "\n puertoRemoto:" + cliente.getPort()
              + "\n ip:" + ipLocal.getHostAddress()
              + "\n ipServidor:" + ipServidor.getHostAddress() + "}");

      DataInputStream entrada = new DataInputStream(cliente.getInputStream());
      System.out.println("Mensaje del servidor: " +entrada.readUTF());

      salida.close();
      entrada.close();

    }catch (Exception ex){
      System.out.println("Error al crear el socket");
    }
    finally {
      if(cliente != null){
        cliente.close();
      }
    }
  }
}
