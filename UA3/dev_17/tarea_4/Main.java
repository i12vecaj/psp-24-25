import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Main {
    public static void main(String[] args) {
        int puerto  = 6000; //Puerto por deonde el server escucha
        byte[] bufer = new byte[1024]; //Creamos un buffer para la informacion que recibimos

        try(DatagramSocket socketServidor = new DatagramSocket(puerto)){ //Creamos un socket UDP y le asignamos el puerto
            System.out.println("El servidor espera el mensaje...");

            while (true) {
                DatagramPacket recibido = new DatagramPacket(bufer, bufer.length); //Creamos el datagram para recibir el mensaje
                socketServidor.receive(recibido); //Esperamos a recibir el mensaje

                String mensaje = new String(recibido.getData(), 0, recibido.getLength()); //Convertimos los bytes a String
                System.out.println("El mensaje recibido es: " + mensaje); //Imprimimos el mensaje recibido

                if (mensaje.equals("*")){ //Si el mensaje es * se cerrara el servidor
                    System.out.println("Servidor cerrandose...");
                    break;
                }
                String respuesta = mensaje.toUpperCase(); //Convertimos el mensaje a mayúsculas
                byte[] bufferRespuesta = respuesta.getBytes(); //Convertimos la respuesta en bytes

                InetAddress direccionCliente = recibido.getAddress(); //Obtenemos la dirección del cliente
                int puertoCliente = recibido.getPort(); //Obtenemos el puerto del cliente
                DatagramPacket envio = new DatagramPacket(bufferRespuesta, bufferRespuesta.length, direccionCliente, puertoCliente); //Creamos un paquete para la respuesta
                socketServidor.send(envio); //Enviamos la respuesta al cliente
            }
        }catch(IOException e){
            System.out.println("Error en el servidor"+e.getMessage());
        }
    }
}