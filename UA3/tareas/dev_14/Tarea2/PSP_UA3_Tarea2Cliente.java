import java.io.*;
import java.net.*;
/*
 * 
 * Autor: @Daniel Fernández Balsera
 * 
 */
public class PSP_UA3_Tarea2Cliente {
    public static void main(String[] arg) throws IOException {


 
        String Host = "192.168.1.28";
          int Puerto = 10000;// Puerto
    
        Socket Cliente = new Socket(Host, Puerto);
        InetAddress i = Cliente.getInetAddress();
/*
 * 
 * 
 *  Aquí mostramos los datos del cliente
 * 
 *  Puerto Local -> Obtiene el puerto local del cliente
 *  Puerto remoto -> Obtiene el puerto remoto
 *  Nombre Host/IP -> Obtiene en este caso la dirección IP
 *  Host Remoto -> Obtiene el host remoto
 *  IP -> Obtiene la IP del host remoto
 * 
 */

        System.out.println("Puerto Local: " + Cliente.getLocalPort());
        System.out.println("Puerto Remoto: " + Cliente.getPort());
        System.out.println("Nombre Host/IP: " + Cliente.getInetAddress());
        System.out.println("Host Remoto: " + i.getHostName().toString());
        System.out.println("IP Host Remoto: " + i.getHostAddress().toString());
    
        Cliente.close();
      }
}
