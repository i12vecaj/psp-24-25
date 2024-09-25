

import java.io.*;
import java.net.*;

public class Ua3tarea2Cliente_JavierGarcía {

    
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        
        String Host = "localhost";
	int Puerto = 7000;//puerto remoto

	System.out.println("PROGRAMA CLIENTE INICIADO....");
	Socket Cliente = new Socket(Host, Puerto);

	// CREO FLUJO DE SALIDA AL SERVIDOR
	DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());

	// ENVIO UN SALUDO AL SERVIDOR
	flujoSalida.writeUTF("Saludos al SERVIDOR DESDE EL CLIENTE");

	// CREO FLUJO DE ENTRADA AL SERVIDOR
	DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());

	// EL SERVIDOR ME ENVIA UN MENSAJE
	System.out.println("Recibiendo del SERVIDOR: \n\t" + flujoEntrada.readUTF());
        
        
        // INFORMACIÓN DEL CLIENTE  
        InetAddress i = Cliente.getInetAddress();
        System.out.println("Puerto Local: " + Cliente.getLocalPort());
        System.out.println("Puerto Remoto: " + Cliente.getPort());
        System.out.println("Nombre Host/IP: " + Cliente.getInetAddress());
        
        
	// CERRAR STREAMS Y SOCKETS
	flujoEntrada.close();
	flujoSalida.close();
	Cliente.close();
    
    
    
    
    }
    
}
