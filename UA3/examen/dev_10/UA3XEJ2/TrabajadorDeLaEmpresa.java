//Revisado por JD: 17/02/25

import java.net.*;

public class TrabajadorDeLaEmpresa {

    //Es más conveniente utilizar UDP porque no se necesita confirmar que las notificaciones lleguen en orden.
    //UDP permite enviar mensajes de manera rápida y eficiente a múltiples clientes al mismo tiempo.


    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(3307);

        // Buffer para almacenar la notificación reciboa del cliente
        byte[] buffer = new byte[1024];

        // Esperar por una notificación
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
        socket.receive(paquete);

        // Mostrar la notificación al trabajador
        String mensaje = new String(paquete.getData(), 0, paquete.getLength());
        System.out.println("Notificación recibida: " + mensaje);

        socket.close();
    }
}
