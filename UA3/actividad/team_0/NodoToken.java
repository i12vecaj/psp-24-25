import java.io.*;
import java.net.*;

public class NodoToken implements Runnable {
    private int miembroId;
    private int puertoEscucha;
    private boolean tieneTokenInicial;
    private boolean esUltimoMiembro;
    private Token tokenEnMano;
    private long ultimoTiempoRecibido;

    public NodoToken(int miembroId, int puertoEscucha, boolean tieneTokenInicial, boolean esUltimoMiembro) {
        this.miembroId = miembroId;
        this.puertoEscucha = puertoEscucha;
        this.tieneTokenInicial = tieneTokenInicial;
        this.esUltimoMiembro = esUltimoMiembro;
        this.tokenEnMano = null;
        this.ultimoTiempoRecibido = System.currentTimeMillis();
    }

    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(puertoEscucha)) {
            if (tieneTokenInicial) {
                this.tokenEnMano = new Token(miembroId, String.valueOf(miembroId), "Inicio");
                transferirToken();
            }

            while (true) {
                recibirToken(socket);
                procesarToken();
                transferirToken();
            }
        } catch (Exception e) {
            System.out.println("Error en Nodo " + miembroId + ": " + e.getMessage());
        }
    }

    private void recibirToken(DatagramSocket socket) throws IOException, ClassNotFoundException {
        byte[] buffer = new byte[1024];
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
        socket.receive(paquete);

        ByteArrayInputStream bais = new ByteArrayInputStream(paquete.getData());
        ObjectInputStream ois = new ObjectInputStream(bais);
        this.tokenEnMano = (Token) ois.readObject();
        ois.close();

        long ahora = System.currentTimeMillis();
        System.out.println("Nodo " + miembroId + " recibió el token. Tiempo desde la última recepción: "
                + (ahora - ultimoTiempoRecibido) + "ms");
        ultimoTiempoRecibido = ahora;
    }

    private void procesarToken() throws InterruptedException {
        System.out.println("Nodo " + miembroId + " procesando token...");
        Thread.sleep(miembroId * 1000);  // Simula el tiempo de procesamiento según el ID
    }

    private void transferirToken() throws IOException {
        int siguientePuerto = esUltimoMiembro ? 10000 : puertoEscucha + 1;
        tokenEnMano.usuarioAnterior = tokenEnMano.usuarioActual;
        tokenEnMano.usuarioActual = String.valueOf(miembroId);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(tokenEnMano);
        oos.close();

        byte[] datosToken = baos.toByteArray();
        DatagramPacket paquete = new DatagramPacket(datosToken, datosToken.length, InetAddress.getLocalHost(), siguientePuerto);

        try (DatagramSocket socketEnvio = new DatagramSocket()) {
            socketEnvio.send(paquete);
        }

        System.out.println("Nodo " + miembroId + " transfirió el token al puerto " + siguientePuerto);
    }
}