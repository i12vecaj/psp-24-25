import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

class MiembroToken implements Runnable {
    private int id;
    private int puerto;
    private boolean tengoToken;
    private boolean soyElUltimo;
    private int puertoDestino;

    public MiembroToken(int id, int puerto, boolean tengoToken, boolean soyElUltimo) {
        this.id = id;
        this.puerto = puerto;
        this.tengoToken = tengoToken;
        this.soyElUltimo = soyElUltimo;
        this.puertoDestino = soyElUltimo ? 10000 : puerto + 1;
    }

    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            System.out.println("Miembro " + id + " escuchando en el puerto " + puerto);

            while (true) {
                if (tengoToken) {
                    long tiempoInicio = System.currentTimeMillis();

                    System.out.println("Miembro " + id + " tiene el token y está ejecutando su tarea...");
                    TimeUnit.SECONDS.sleep(id);

                    long tiempoFinal = System.currentTimeMillis();
                    long tiempoTranscurrido = tiempoFinal - tiempoInicio;

                    System.out.println("Miembro " + id + " terminó su tarea en " + tiempoTranscurrido + " ms.");

                    enviarToken();
                } else {
                    recibirToken(socket);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void recibirToken(DatagramSocket socket) {
        try {
            byte[] buffer = new byte[256];
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquete);

            String mensaje = new String(paquete.getData(), 0, paquete.getLength(), StandardCharsets.UTF_8);
            if ("TOKEN".equals(mensaje)) {
                tengoToken = true;
                System.out.println("Miembro " + id + " recibió el token.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enviarToken() {
        try (DatagramSocket socket = new DatagramSocket()) {
            String mensaje = "TOKEN";
            byte[] buffer = mensaje.getBytes(StandardCharsets.UTF_8);

            InetAddress direccion = InetAddress.getByName("localhost");

            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, direccion, puertoDestino);
            socket.send(paquete);

            System.out.println("Miembro " + id + " envió el token al puerto " + puertoDestino);
            tengoToken = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class TokenRing {
    public static void main(String[] args) {
        // Crear los tres miembros en hilos
        Thread miembro1 = new Thread(new MiembroToken(1, 10000, true, false));
        Thread miembro2 = new Thread(new MiembroToken(2, 10001, false, false));
        Thread miembro3 = new Thread(new MiembroToken(3, 10002, false, true));

        // Iniciar los hilos
        miembro1.start();
        miembro2.start();
        miembro3.start();
    }
}
