import java.io.*;
import java.net.*;
/*
 * 
 * Autor: @Daniel Fernández Balsera
 * 
 */
public class PSP_UA3_Tarea2Server {
  public static void main(String[] arg) throws IOException {
	int Puerto = 10000;// Puerto

    InetAddress direccion = InetAddress.getByName("127.0.0.1");
	ServerSocket Servidor = new ServerSocket(Puerto,50,direccion);
    System.out.println("Escuchando en " + Servidor.getLocalPort());

    Socket cliente1 = Servidor.accept();
    // Realizar Acciones con cliente 1
    System.out.println("Cliente escuchado");
    Socket cliente2 = Servidor.accept();
    // Realizar Acciones con cliente 2
    System.out.println("Cliente 2 escuchado");
    Servidor.close();
  }

}
