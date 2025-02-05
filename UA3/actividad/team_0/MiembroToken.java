import java.net.*;
import java.io.*;

public class MiembroToken implements Runnable {
    private int id;
    private int puerto;
    private boolean tengoToken;
    private boolean soyElUltimo;
    private int siguientePuerto;
    private Thread hilo;

    public MiembroToken(int id, int puerto, boolean tengoToken, boolean soyElUltimo) {
        this.id = id;
        this.puerto = puerto;
        this.tengoToken = tengoToken;
        this.soyElUltimo = soyElUltimo;
        this.siguientePuerto = soyElUltimo ? 10000 : puerto + 1;
        this.hilo = new Thread(this);
    }

    public void iniciar() {
        hilo.start();
        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            byte[] buffer = new byte[1];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while (true) {
                if (!tengoToken) {
                    System.out.println("Miembro " + id + " esperando el token en el puerto " + puerto);
                    socket.receive(packet);
                    tengoToken = true;
                }

                long tiempoInicio = System.currentTimeMillis();
                System.out.println("Miembro " + id + " tiene el token. Ejecutando tarea...");
                hilo.join(); // Espera a que termine la tarea antes de continuar
                long tiempoFin = System.currentTimeMillis();

                System.out.println("Miembro " + id + " terminó su tarea. Tiempo transcurrido: " + (tiempoFin - tiempoInicio) + " ms");
                tengoToken = false;
                enviarToken();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enviarToken() {
        try (DatagramSocket socket = new DatagramSocket()) {
            byte[] buffer = new byte[1];
            InetAddress direccion = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, direccion, siguientePuerto);
            socket.send(packet);
            System.out.println("Miembro " + id + " envió el token al puerto " + siguientePuerto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(id * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
