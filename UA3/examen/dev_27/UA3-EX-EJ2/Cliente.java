import java.net.*;

public class Cliente {
    public static void main(String args[]) throws Exception {
        int Puerto = 12345;
        MulticastSocket multicastSocket = new MulticastSocket(Puerto);
        InetAddress grupo = InetAddress.getByName("225.0.0.1");
        multicastSocket.joinGroup (grupo);
        String mensaje="";
        while(!mensaje.trim().equals("*")) {
            byte[] buf = new byte[1000];
            DatagramPacket paquete = new DatagramPacket(buf, buf.length);
            multicastSocket.receive(paquete);
            mensaje = new String(paquete.getData());
            System.out.println ("Recibo: " + mensaje.trim());
        }
        multicastSocket.leaveGroup (grupo);
        multicastSocket.close ();
        System.out.println ("Socket cerrado...");
    }
}