import java.net.*;
import java.io.*;

public class MiembroToken {
    int id;
    int puerto;
    boolean tengoToken;
    boolean soyElUltimo;
    int siguientePuerto;
    long ultimaRecepcion;

    public MiembroToken(int id, int puerto, boolean tengoToken, boolean soyElUltimo) {
        this.id = id;
        this.puerto = puerto;
        this.tengoToken = tengoToken;
        this.soyElUltimo = soyElUltimo;
        this.siguientePuerto = soyElUltimo ? 10000 : puerto + 1;
        this.ultimaRecepcion = System.currentTimeMillis();
    }

    public void iniciar() {
        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            byte[] buffer = new byte[1];
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);

            if (tengoToken) {
                procesarToken(socket);
            }

            while (true) {
                socket.receive(paquete);
                long tiempoActual = System.currentTimeMillis();
                System.out.println("Miembro " + id + " ha recibido el token. Tiempo transcurrido desde la última recepción: " + (tiempoActual - ultimaRecepcion) + " ms");
                ultimaRecepcion = tiempoActual;
                tengoToken = true;
                procesarToken(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void procesarToken(DatagramSocket socket) throws InterruptedException, IOException {
        System.out.println("Miembro " + id + " está procesando el token.");
        Thread.sleep(id * 1000);
        enviarToken(socket);
    }

    public void enviarToken(DatagramSocket socket) throws IOException {
        tengoToken = false;
        byte[] buffer = new byte[1];

        int siguientePuertoFinal = soyElUltimo ? 10000 : siguientePuerto;
    
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), siguientePuertoFinal);
        socket.send(paquete);
        System.out.println("Miembro " + id + " ha enviado el token al puerto " + siguientePuertoFinal);
    }

    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Uso: java MiembroToken [id] [puerto] [token_al_inicio] [soy_el_ultimo]");
            return;
        }
        int id = Integer.parseInt(args[0]);
        int puerto = Integer.parseInt(args[1]);
        boolean tengoToken = args[2].equalsIgnoreCase("yes");
        boolean soyElUltimo = args[3].equalsIgnoreCase("yes");

        MiembroToken miembro = new MiembroToken(id, puerto, tengoToken, soyElUltimo);
        miembro.iniciar();
    }
}