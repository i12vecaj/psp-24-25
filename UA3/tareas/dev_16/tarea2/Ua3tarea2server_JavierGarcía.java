

import java.io.*;
import java.net.*;


public class Ua3tarea2server_JavierGarcía {

    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        int numeroPuerto = 7000;// Puerto
	ServerSocket servidor = new ServerSocket(numeroPuerto);
	Socket cliente1 = null;
	System.out.println("Esperando al cliente 1");
	cliente1 = servidor.accept();

	// CREO FLUJO DE ENTRADA DEL CLIENTE
	InputStream entrada = null;
	entrada = cliente1.getInputStream();
	DataInputStream flujoEntrada = new DataInputStream(entrada);

	// EL CLIENTE ME ENVIA UN MENSAJE
	System.out.println("Recibiendo del CLIENTE 1: \n\t" + flujoEntrada.readUTF());

	// CREO FLUJO DE SALIDA AL CLIENTE
	OutputStream salida = null;
	salida = cliente1.getOutputStream();
	DataOutputStream flujoSalida = new DataOutputStream(salida);

	// ENVIO UN SALUDO AL CLIENTE
	flujoSalida.writeUTF("Adios cliente 1 del servidor");
        
        System.out.println("----------------------------");

	// CERRAR STREAMS Y SOCKETS
	entrada.close();
	flujoEntrada.close();
	salida.close();
	flujoSalida.close();
	cliente1.close();
	
        
        
        Socket cliente2 = null;
	System.out.println("Esperando al cliente 2");
	cliente2 = servidor.accept();

	// CREO FLUJO DE ENTRADA DEL CLIENTE
	InputStream entrada2 = null;
	entrada2 = cliente2.getInputStream();
	DataInputStream flujoEntrada2 = new DataInputStream(entrada2);

	// EL CLIENTE ME ENVIA UN MENSAJE
	System.out.println("Recibiendo del CLIENTE 2: \n\t" + flujoEntrada2.readUTF());

	// CREO FLUJO DE SALIDA AL CLIENTE
	OutputStream salida2 = null;
	salida2 = cliente2.getOutputStream();
	DataOutputStream flujoSalida2 = new DataOutputStream(salida2);

	// ENVIO UN SALUDO AL CLIENTE
	flujoSalida2.writeUTF("Adios al cliente 2 del servidor");

	// CERRAR STREAMS Y SOCKETS
	entrada2.close();
	flujoEntrada2.close();
	salida2.close();
	flujoSalida2.close();
	cliente2.close();
	servidor.close();
    }
    
}

