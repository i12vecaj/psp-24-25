import java.net.*;
import java.io.*;

public class actividad {

    //Creamos las variables para los argumentos
    private static int id;
    private static int puerto;
    private static boolean token;
    private static boolean soyElUltimo;

    public static void main(String[] args) throws IOException {
        //Indicamos el argumento de las variables id y puerto
        id = Integer.parseInt(args[0]);
        puerto = Integer.parseInt(args[1]);

        //Con un if declaramos que si el argumento esta a 1 se envia el token y si esta a 0 no
        if (args[2].equals("1")) {
            token = true;
        } else {
            token = false;
        }
        //con un if indicamos si es el ultimo o no lo es
        if (args[3].equals("1")) {
            soyElUltimo = true;
        } else {
            soyElUltimo = false;
        }

        // Si se recibe token hacemos lo siguiente
        do {
            
            if (token) {
                //Si se recibe token se indica mediante terminal
                System.out.println("Miembro " + id + " recibió el token y comenzará su tarea");
                //ejecutamos la funcion ejecutarTarea
                ejecutarTarea();
                // Se envia el token al siguiente miembro
                enviarToken();
            } else {
                //Si no se recibe el token ejecutamos la funcion esperarToken
                System.out.println("Miembro " + id + " espera el token");
                esperarToken();
            }
        } while (soyElUltimo);
    }

    //Con esta funcion hacemos que el hilo haga un sleep e indicamos por termial cuando haya terminado
    private static void ejecutarTarea() {
        try {
            Thread.sleep(id * 1000);
            System.out.println("Miembro " + id + " terminó su tarea");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
//Con esta funcion sumamos 1 a cada puerto 
    private static void enviarToken() throws IOException {
        int puertoSiguiente;
        if (soyElUltimo) {
            puertoSiguiente = 10000;
        } else {
            puertoSiguiente = puerto + 1;
        }
//Ahora se procede a enviar el token al puerto correspondiente
        System.out.println("Miembro " + id + " envía el token al puerto " + puertoSiguiente);
        DatagramSocket socket = new DatagramSocket();
        byte[] buf = new byte[256];
        buf = Boolean.toString(token).getBytes();
        InetAddress address = InetAddress.getByName("localhost");
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, puertoSiguiente);
        socket.send(packet);
        socket.close();
    }

    private static void esperarToken() throws IOException {
        //Con esta funcion esperamos a que se reciba el token y posteriormente se indica mediante terminal
        DatagramSocket socket = new DatagramSocket(puerto);
        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(packet.getData(), 0, packet.getLength());
        token = Boolean.parseBoolean(received);
        socket.close();
        if (token) {
            System.out.println("Miembro " + id + " recibió el token");
            ejecutarTarea();
            enviarToken();
        }
    }
}
