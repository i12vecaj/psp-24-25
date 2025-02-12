import java.net.*;

public class ServidorDeLaEmpresa {

    //Es más conveniente utilizar UDP porque no se necesita confirmar que las notificaciones lleguen en orden.
    //UDP permite enviar mensajes de manera rápida y eficiente a múltiples clientes al mismo tiempo.


    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        String alerta = "Alerta: Reunión en 5 minutos";

        // mensaje a bytes y establecer la dirección del cliente y el puerto
        byte[] buffer = alerta.getBytes();

        InetAddress direccionCliente = InetAddress.getByName("localhost");
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, direccionCliente, 3307);

        // Enviar la notificación al cliente
        socket.send(paquete);
        System.out.println("Notificación enviada");

        socket.close();
    }
}

