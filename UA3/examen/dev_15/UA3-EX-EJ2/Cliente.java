import java.net.*;

public class Cliente {
    //DATOS DE CONEXION
    private static final int PORT = 33;

    public static void main(String[] args) {
        //INICIO DEL CLIENTE
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            //MENSAJE DE INICIO DEL CLIENTE
            System.out.println("Cliente escuchando notificaciones...");
            
            //BUCLE DE ESCUCHA DE NOTIFICACIONES DEL SERVIDOR
            while (true) {
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Notificación recibida: " + message);
            }
        } catch (Exception e) {
            //MENSAJE DE ERROR EN CASO DE FALLO
            e.printStackTrace();
        }
    }
}
