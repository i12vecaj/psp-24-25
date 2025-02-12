//Código hecho por Alberto Mármol Bello para la asignatura de Procesos y Servicios con fecha 12/02/2025
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {
    public static void main(String[] args) {
        //Defino el puerto en el que el servidor escuchará los mensajes entrantes.
        final int PUERTO = 54321;  
        
        //Creo un socket UDP en el puerto 12345.
        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {  
            System.out.println("Servidor de notificaciones en ejecución...");
            
            //Creo un buffer para almacenar los datos recibidos como máximo tiene que tener 1024 bytes.
            byte[] buffer = new byte[1024];  
            
            //Hago un bucle infinito para que el servidor siga escuchando continuamente.
            while (true) {  
                //Aquí creo un paquete para recibir los datos y se especifica el buffer y su tamaño.
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                //Por aquí recibo un paquete de datos y lo almacena en el paquete recibido.S
                socket.receive(paqueteRecibido);  
                //Lo Convierto los bytes del paquete a un mensaje
                String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                //Luego muestro el mensaje que ha recibido en la consola.
                System.out.println("Alerta recibida: " + mensaje);
                //Se establece la dirección de broadcast para enviar el mensaje a todos los clientes.
                InetAddress direccionBroadcast = InetAddress.getByName("255.255.255.255");
                //Creo un nuevo paquete con el mensaje que será enviado a los clientes por broadcast.
                DatagramPacket paqueteEnviado = new DatagramPacket(mensaje.getBytes(), mensaje.length(), direccionBroadcast, PUERTO);
                //Luego envío el paquete a todos los clientes conectados.
                socket.send(paqueteEnviado);
            }
        } catch (Exception e) {
            //Si para algo malo lo imprime en la consola.
            e.printStackTrace();  
        }
    }
}