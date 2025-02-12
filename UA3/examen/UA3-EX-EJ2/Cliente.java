//Código hecho por Alberto Mármol Bello para la asignatura de Procesos y Servicios con fecha 12/02/2025
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Cliente {
    public static void main(String[] args) {
        //Defino el puerto en el que el cliente escuchará las notificaciones del servidor.
        final int PUERTO = 12345;  
        
        //Creo un socket UDP para escuchar en el puerto 12345.
        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {  
            System.out.println("Cliente escuchando notificaciones...");
            //Luego creo un buffer de 1024 bytes para almacenar los datos recibidos.
            byte[] buffer = new byte[1024];  
            //Hago un bucle infinito para mantener al cliente escuchando continuamente.
            while (true) {  
                //Sigo crean un paquete para recibir los datos.
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                //Recibo un paquete de datos y lo almacena en el paquete recibido.
                socket.receive(paquete);  
                //Convierto los bytes del paquete en un mensaje.
                String mensaje = new String(paquete.getData(), 0, paquete.getLength());
                //Por último muestro el mensaje recibido en la consola.
                System.out.println("Notificación recibida: " + mensaje);
            }
        } catch (Exception e) {
            //Si ocurre algún error, lo imprime en la consola.
            e.printStackTrace();  
        }
    }
}