import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Cliente1 {
    public static void main(String[] args) throws IOException {

        int Puerto = 12345;//Puerto multicast
        MulticastSocket cliente1 = new MulticastSocket(Puerto);

        InetAddress grupo = InetAddress.getByName("225.0.0.1");//Grupo

        //Nos unimos al grupo
        cliente1.joinGroup (grupo);

        String mensaje="";

        //
        while(!mensaje.trim().equals("*")) {
            byte[] buf = new byte[1000];
            //Recibe el paquete del servidor multicast
            DatagramPacket paquete = new DatagramPacket(buf, buf.length);
            cliente1.receive(paquete);

            mensaje = new String(paquete.getData());
            System.out.println ("Mensaje recibido del servidor: " + mensaje.trim());
        }
        cliente1.leaveGroup (grupo); //abandonamos grupo
        cliente1.close (); //cierra socket
        System.out.println ("Cliente 1 cerrado...");
    }
}
