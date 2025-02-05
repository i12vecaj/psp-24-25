import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.Duration;

public class MiembroToken implements Runnable {
    private int id;
    private int puerto;
    private boolean tieneToken;
    private boolean esUltimo;
    private int puertoSiguiente;
    private long tiempoEspera;
    private LocalDateTime ultimaRecepcion;

    public MiembroToken(int id, int puerto, boolean tieneToken, boolean esUltimo) {
        this.id = id;
        this.puerto = puerto;
        this.tieneToken = tieneToken;
        this.esUltimo = esUltimo;
        this.puertoSiguiente = esUltimo ? 10000 : puerto + 1;
        this.tiempoEspera = id * 1000;
        this.ultimaRecepcion = LocalDateTime.now();
    }

    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            System.out.println("Miembro " + id + " escuchando en el puerto " + puerto);

            if (tieneToken) {
                procesarToken();
            }

            byte[] buffer = new byte[256];
            while (true) {
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete);

                String mensaje = new String(paquete.getData(), 0, paquete.getLength(), StandardCharsets.UTF_8);
                if (mensaje.equals("TRUE")) {
                    procesarToken();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void procesarToken() {
        LocalDateTime ahora = LocalDateTime.now();
        if (ultimaRecepcion != null) {
            Duration duracion = Duration.between(ultimaRecepcion, ahora);
            System.out.println("Miembro " + id + " recibió el TOKEN. Tiempo desde la última recepción: " + duracion.toMillis() + " ms");
        }
        ultimaRecepcion = ahora;

        try {
            System.out.println("Miembro " + id + " está procesando durante " + (tiempoEspera / 1000) + " segundos.");
            Thread.sleep(tiempoEspera);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        enviarToken();
    }

    private void enviarToken() {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress direccion = InetAddress.getByName("localhost");
            byte[] buffer = "TRUE".getBytes(StandardCharsets.UTF_8);
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, direccion, puertoSiguiente);

            socket.send(paquete);
            System.out.println("Miembro " + id + " envió el TOKEN al puerto " + puertoSiguiente);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println
                    ("Uso: java MiembroToken [id] [puerto] [token_al_inicio] [soy_el_ultimo]");
            return;
        }

        int id = Integer.parseInt(args[0]);
        int puerto = Integer.parseInt(args[1]);
        boolean tieneToken = args[2].equalsIgnoreCase("yes");
        boolean esUltimo = args[3].equalsIgnoreCase("yes");

        MiembroToken miembro = new MiembroToken(id, puerto, tieneToken, esUltimo);
        new Thread(miembro).start();
    }
}
