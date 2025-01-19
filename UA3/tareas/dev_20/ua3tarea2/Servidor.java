package dev_20.ua3tarea2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
  public static void main(String[] args) {
    int puerto = 6000;
    ServerSocket socketServidor = null;
    try{
      socketServidor = new ServerSocket(puerto);

      for(int i = 0; i < 2; i++){
        // Creo los sockets para el primer cliente
        Socket cliente = socketServidor.accept();
        // Recibo los datos que envia el primer cliente y lo muestro
        InputStream entrada= cliente.getInputStream();
        DataInputStream infoEntrada = new DataInputStream(entrada);

        System.out.printf("El mensaje del cliente es: \n" + infoEntrada.readUTF());

        // Envio al cliente la señal de datos recogidos
        OutputStream salida = cliente.getOutputStream();
        DataOutputStream infoSalida = new DataOutputStream(salida);
        infoSalida.writeUTF("200 OK");
        // Cierro todos los sockets
        entrada.close();
        infoEntrada.close();
        salida.close();
        infoSalida.close();
        cliente.close();
      }

      socketServidor.close();

    }catch (Exception ex){
      System.out.println("No se ha podido crear el socket: " + ex);
    }
    finally {
      if(socketServidor != null){
        try{
          socketServidor.close();
        }catch (Exception ex){
          System.out.println("No se ha cerrado el socket correctamente: " + ex);
        }
      }
    }
  }
}
