package TCPEjemplo;

import java.io.*;
import java.net.*;

public class TCPCliente {
  public static void main(String[] args) throws Exception {
    String Host = "localhost";
    int Puerto = 6000;

    Socket Cliente1 = new Socket(Host, Puerto);
    Socket Cliente2 = new Socket(Host, Puerto);

    DataInputStream in1 = new DataInputStream(Cliente1.getInputStream());
    DataInputStream in2 = new DataInputStream(Cliente2.getInputStream());

    DataOutputStream out1 = new DataOutputStream(Cliente1.getOutputStream());
    DataOutputStream out2 = new DataOutputStream(Cliente2.getOutputStream());

    System.out.println("CLIENTES INICIADOS....");

    System.out.println("Cliente 1 - Puerto local: " + Cliente1.getLocalAddress().getHostAddress() + ":" + Cliente1.getLocalPort());
    System.out.println("Cliente 1 - Puerto remoto: " + Cliente1.getInetAddress().getHostAddress() + ":" + Cliente1.getPort());

    System.out.println("Cliente 2 - Puerto local: " + Cliente2.getLocalAddress().getHostAddress() + ":" + Cliente2.getLocalPort());
    System.out.println("Cliente 2 - Puerto remoto: " + Cliente2.getInetAddress().getHostAddress() + ":" + Cliente2.getPort());

    out1.writeUTF("Saludos al SERVIDOR DESDE EL CLIENTE 1");
    out2.writeUTF("Saludos al SERVIDOR DESDE EL CLIENTE 2");

    System.out.println("Recibiendo del SERVIDOR al cliente 1: \n\t" + in1.readUTF());
    System.out.println("Recibiendo del SERVIDOR al cliente 2: \n\t" + in2.readUTF());

    in1.close();
    in2.close();

    out1.close();
    out2.close();

    Cliente1.close();
    Cliente2.close();
  }
}
