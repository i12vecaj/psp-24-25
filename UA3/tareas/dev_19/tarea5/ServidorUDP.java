import java.io.*;
import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) {
        int puerto = 9000;
        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            System.out.println("Servidor iniciado en el puerto " + puerto);

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete);

                ByteArrayInputStream bais = new ByteArrayInputStream(paquete.getData());
                ObjectInputStream ois = new ObjectInputStream(bais);
                Numeros numeros = (Numeros) ois.readObject();

                numeros.setCuadrado(numeros.getNumero());
                numeros.setCubo(numeros.getNumero());

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(numeros);
                oos.flush();

                byte[] datosEnviados = baos.toByteArray();
                DatagramPacket paqueteEnviado = new DatagramPacket(datosEnviados, datosEnviados.length, paquete.getAddress(), paquete.getPort());
                socket.send(paqueteEnviado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
