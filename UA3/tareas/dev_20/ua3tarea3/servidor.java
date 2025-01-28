package dev_20.ua3tarea3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor {
  public static void main(String[] args) {
    int puerto = 6000;

    ServerSocket socket = null;
    try{
      socket = new ServerSocket(puerto);

      Socket socketCliente = socket.accept();

      InputStream flujoEntrada = socketCliente.getInputStream();
      DataInputStream infoEntrada = new DataInputStream(flujoEntrada);
      String mensajeCliente = infoEntrada.readUTF();
      String mensajeFormateado = mensajeCliente.toUpperCase();

      OutputStream flujoSalida = socketCliente.getOutputStream();
      DataOutputStream infoSalida = new DataOutputStream(flujoSalida);
      infoSalida.writeUTF(mensajeFormateado);

      infoSalida.close();
      infoEntrada.close();

      flujoSalida.close();
      flujoEntrada.close();

      socketCliente.close();

    }catch (Exception ex){
      System.out.println("ERROR: " + ex);
    }
    finally {
      if(socket != null){
        try{
          socket.close();
        }catch (Exception ex){
          System.out.println("No se ha cerrado el socket correctamente: " + ex);
        }
      }
    }
  }
}
