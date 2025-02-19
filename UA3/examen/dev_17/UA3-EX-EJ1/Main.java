//Revisado por JD: 19/02/25

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*Mi aplicacion no hace  todo a la vez para poder enviar informacion desde los demas clientes debes de cerrar la conexion anterior
* igualmente en este ejercicio lo correcto seria usar el modelo TCP, ya que este modelo es el que se suelle utilizar en los programas
* de tipo chat.*/

public class Main {
    public static void main(String[] args) {
    int puerto =  6000;
        ArrayList<Socket> listaSockets = new ArrayList<Socket>();

    try (ServerSocket serverSocket = new ServerSocket(puerto)) {
        System.out.println("Esperando clientes...");

        Socket cliente1 =serverSocket.accept();
        System.out.println("Cliente 1 conectado");
        DataInputStream entradaCliente1 = new DataInputStream(cliente1.getInputStream());
        DataOutputStream salidaCliente1 = new DataOutputStream(cliente1.getOutputStream());
        String mensaje1;

        while (true) {
            mensaje1 = entradaCliente1.readUTF(); // Leer mensaje correctamente
            if (mensaje1.equalsIgnoreCase("salir")) {
                System.out.println("El cliente1 ha cerrado la conexión.");
                break;
            }
            System.out.println("Mensaje recibido del cliente 1: " + mensaje1);
            String respuesta = "El mensaje del cliente 1 se ha recibido en el servidor"; // Convertir a mayúsculas
            salidaCliente1.writeUTF(respuesta); // Enviar respuesta
            System.out.println("Respuesta enviada al cliente 1");
        }

        Socket cliente2 = serverSocket.accept();
        System.out.println("Cliente 2 conectado");
        DataInputStream entradaCliente2 = new DataInputStream(cliente2.getInputStream());
        DataOutputStream salidaCliente2 = new DataOutputStream(cliente2.getOutputStream());
        String mensaje2;

        while (true) {
            mensaje2 = entradaCliente2.readUTF(); // Leer mensaje correctamente
            if (mensaje2.equalsIgnoreCase("salir")) {
                System.out.println("El cliente2 ha cerrado la conexión.");
                break;
            }

            System.out.println("Mensaje recibido del cliente 2: " + mensaje2);
            String respuesta = "El mensaje del cliente 2 se ha recibido en el servidor"; // Convertir a mayúsculas
            salidaCliente2.writeUTF(respuesta); // Enviar respuesta
            System.out.println("Respuesta enviada al cliente 2");
        }


        Socket cliente3 = serverSocket.accept();
        System.out.println("Cliente 3 conectado");
        DataInputStream entradaCliente3 = new DataInputStream(cliente3.getInputStream());
        DataOutputStream salidaCliente3 = new DataOutputStream(cliente3.getOutputStream());

        String mensaje3;
        while (true) {
            mensaje3 = entradaCliente3.readUTF(); // Leer mensaje correctamente
            if (mensaje3.equalsIgnoreCase("salir")) {
                System.out.println("El cliente 3 ha cerrado la conexión.");
                break;
            }

            System.out.println("Mensaje recibido del cliente 3: " + mensaje3);
            String respuesta = "El mensaje del cliente 3 se ha recibido en el servidor"; // Convertir a mayúsculas
            salidaCliente3.writeUTF(respuesta); // Enviar respuesta
            System.out.println("Respuesta enviada al cliente 3");
        }

        salidaCliente1.close();
        entradaCliente1.close();
        cliente1.close();

        salidaCliente2.close();
        entradaCliente2.close();
        cliente2.close();

        salidaCliente3.close();
        entradaCliente3.close();
        cliente3.close();
        System.out.println("El servidor se ha cerrado");
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    }
}
