import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String []args){
        int puertoServidor = 6000; //Puerto de escucha del servidor
        int puertoCliente = 5000; //Puerto del cliente
        int timeOut = 5000; //Cinco segundos de espera

        try(DatagramSocket socketCliente = new DatagramSocket(puertoCliente)){ //Creamos un socket para el cliente
            socketCliente.setSoTimeout(timeOut); // Establecer un tiempo de espera para recibir las respuestas
            InetAddress direccionServidor = InetAddress.getLocalHost(); //Obtenemos la direccion del servidor que al ser UDP es la del localhost
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Introduzca un mensaje (Para salir *)");
                String mensaje = scanner.nextLine(); //Leemos el mensaje del cliente

                byte[] buferEnvio = mensaje.getBytes(); //Cojemos el string y lo pasamos a byte para el envio
                DatagramPacket envio = new DatagramPacket(buferEnvio, buferEnvio.length, direccionServidor, puertoServidor); //Creando el paquete para la direccion del mensaje
                socketCliente.send(envio); //Enviamos el paquete al servidor

                if (mensaje.equals("*")){
                    System.out.println("Cerrando cliente...");
                    break;
                }

                byte[] bufferRecibo = new byte[1024]; //Buffer para la respuesta del servidor
                DatagramPacket recibo = new DatagramPacket(bufferRecibo, bufferRecibo.length);

                try{
                    socketCliente.receive(recibo); //Esperamos la recepcion de la respuesta
                    String respuesta = new String(recibo.getData(), 0, recibo.getLength()); //Pasamos los bytes a string
                    System.out.println("La respuesta del servidor es: "+respuesta);
                }catch (SocketTimeoutException e) {
                    System.out.println("Tiempo de espera agotado, no hemos recibido respuesta del servidor"); //Manejo del tiempo de espera agotado
                }
            }
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage()); // Manejo de errores
        }
    }
}
