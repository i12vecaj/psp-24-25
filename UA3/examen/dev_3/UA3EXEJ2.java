//Revisado por JD 17/01/2024

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

//He elegido el estilo UDP porque no me importa en este programa que se pierdan paquetes ni el orden de estos.
//Sino que queremos que el envio del mensaje sea eficiente.
public class UA3EXEJ2 {
    public static void main(String[] argv) throws Exception {
        int port = 9870;
        InetAddress grupo = InetAddress.getByName("230.0.0.1"); // Dirección Multicast

        byte[] mensaje = "Enviando Notificación ".getBytes();
        // envio el mensaje en un datagram a los clientes
        DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, grupo, port);
        DatagramSocket socket = new DatagramSocket();

        socket.send(envio);
        System.out.println("Notificación enviada a todos los clientes.");

        socket.close();
    }
}


 class reciboNotificacion {
    public static void main(String[] argv) throws Exception {
        int port = 9870;
        //les asigno a todos los clientes la direccion multicast
        //para que al enviar la notificacion a esta misma les llegue a todos  a la vez
        InetAddress grupo = InetAddress.getByName("230.0.0.1");

        MulticastSocket socket = new MulticastSocket(port);
        // con esto me uno al grupo de multidifusion
        socket.joinGroup(grupo);

        byte[] bufer = new byte[1024];

        System.out.println("Esperando la Notificación");
        //nos permite recibir la notificacion
        while (true) {
            DatagramPacket recibo = new DatagramPacket(bufer, bufer.length);
            socket.receive(recibo);

            String paquete = new String(recibo.getData(), 0, recibo.getLength());
            System.out.println("Contenido del Paquete: " + paquete);
        }
    }
}

class reciboNotificacion2 {
    public static void main(String[] argv) throws Exception {
        int port = 9870;
        //les asigno a todos los clientes la direccion multicast
        //para que al enviar la notificacion a esta misma les llegue a todos  a la vez
        InetAddress grupo = InetAddress.getByName("230.0.0.1");

        MulticastSocket socket = new MulticastSocket(port);
        // con esto me uno al grupo de multidifusion
        socket.joinGroup(grupo);

        byte[] bufer = new byte[1024];

        System.out.println("Esperando la Notificación");
        //nos permite recibir la notificacion
        while (true) {
            DatagramPacket recibo = new DatagramPacket(bufer, bufer.length);
            socket.receive(recibo);

            String paquete = new String(recibo.getData(), 0, recibo.getLength());
            System.out.println("Contenido del Paquete: " + paquete);
        }
    }
}

class reciboNotificacion3 {
    public static void main(String[] argv) throws Exception {
        int port = 9870;
        //les asigno a todos los clientes la direccion multicast
        //para que al enviar la notificacion a esta misma les llegue a todos  a la vez
        InetAddress grupo = InetAddress.getByName("230.0.0.1");

        MulticastSocket socket = new MulticastSocket(port);
        // con esto me uno al grupo de multidifusion
        socket.joinGroup(grupo);

        byte[] bufer = new byte[1024];

        System.out.println("Esperando la Notificación");
        //nos permite recibir la notificacion
        while (true) {
            DatagramPacket recibo = new DatagramPacket(bufer, bufer.length);
            socket.receive(recibo);

            String paquete = new String(recibo.getData(), 0, recibo.getLength());
            System.out.println("Contenido del Paquete: " + paquete);
        }
    }
}

