package tarea3;
import java.io.*;
import java.net.*;
public class ua3tarea3Servidor {
  public static void main(String[] args) throws IOException {
    int puerto = 3307;
    ServerSocket servidor = new ServerSocket(puerto);
    Socket clienteConectado = null;

    System.out.println("Esperando a mi compadre el cliente...");

    clienteConectado = servidor.accept();//Esperando al cliente :=)

    //Flujo de entrada del cliente al servidor
    InputStream entradaAlServidor = null ;
    entradaAlServidor = clienteConectado.getInputStream();
    DataInputStream flujoEntrada = new DataInputStream(entradaAlServidor);

    //Almacenado y Convertimos el dato a mayusculas del cliente!
    String paqueteMayuscula =flujoEntrada.readUTF().toUpperCase();

    //Flujo de salida al cliente desde el servidor
    OutputStream devueltaAlClienteElPAquete = null;
    devueltaAlClienteElPAquete = clienteConectado.getOutputStream();
    DataOutputStream flujoSalida = new DataOutputStream(devueltaAlClienteElPAquete);
    flujoSalida.writeUTF(paqueteMayuscula);//Paquete devuelta al cliente

    entradaAlServidor.close();
    flujoEntrada.close();
    devueltaAlClienteElPAquete.close();
    flujoSalida.close();
    clienteConectado.close();
    servidor.close();
    System.out.println("Conexión cerrada");
  }
}
