package tarea3;

import java.io.*;
import java.net.*;

public class ua3tarea3cliente {

  public static void main(String[] args) throws Exception {

    String servidorIp = "localhost";
    int puerto = 3307;

    System.out.println("Cliente Activo!");
    Socket cliente = new Socket(servidorIp,puerto);

    //Creando Flujo de salida del cliente al servidor para empezar a enviar paquetes con el protocolo TCP/IP.
    DataOutputStream flujoClienteAlServidor = new DataOutputStream(cliente.getOutputStream());

    flujoClienteAlServidor.writeUTF("Hola buen Servidor Soy un cliente razonable (Esto deberia salir en mayusculas jeje) :) ");

    // Creando el Flujo de entrada del servidor al Cliente.
    DataInputStream flujoServidorAlCliente = new DataInputStream(cliente.getInputStream());

    // Mostrando la informacion que sale del servidor al cliente
    System.out.println("Recibiendo del SERVIDOR: \n" + flujoServidorAlCliente.readUTF());

    // Cerrando los flujos y el socket
    flujoClienteAlServidor.close();
    flujoServidorAlCliente.close();
    cliente.close();
  }
}
