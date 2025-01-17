import java.io.*;
import java.net.*;
public class Servidor {
    public static void main(String[] args) throws IOException {
        int puerto = 6000; //Asignamos el puerto 6000 a una variable

        try(ServerSocket serverSocket = new ServerSocket(puerto)){ //Y declaramos el server y su puerto con la variable anterior

            System.out.println("Esperando al cliente.....\n");

            Socket cliente1 = serverSocket.accept(); //Aceptamos al primer cliente y mostramos su informacion
            System.out.println("El puerto local del cliente 1 es: " + cliente1.getLocalPort());
            System.out.println("El puerto remoto del cliente 1 es: " + cliente1.getPort());

            //Ahora creamos el flujo de salida del cliente1
            OutputStream salidaCliente1 = cliente1.getOutputStream();
            DataOutputStream flujoSalidaCliente1 = new DataOutputStream(salidaCliente1);
            flujoSalidaCliente1.writeUTF("Saliendo..."); //Enviamos un mensaje al cliente1

            Socket cliente2 = serverSocket.accept();
            System.out.println("El puerto local del cliente 2 es: " + cliente2.getLocalPort());
            System.out.println("El puerto remoto del cliente 2 es: " + cliente2.getPort());

            //Ahora creamos el flujo de salida del cliente2
            OutputStream salidaCliente2 = cliente2.getOutputStream();
            DataOutputStream flujoSalidaCliente2 = new DataOutputStream(salidaCliente2);
            flujoSalidaCliente2.writeUTF("Saliendo..."); //Enviamos un mensaje al cliente2

            //Cerramos el servidor
            serverSocket.close();

            //Cerramos los clientes
            cliente1.close();
            cliente2.close();

            //Cerramos los flujos de salida de los clientes
            salidaCliente1.close();
            flujoSalidaCliente1.close();

            salidaCliente2.close();
            salidaCliente2.close();

        }catch(Exception e){
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}