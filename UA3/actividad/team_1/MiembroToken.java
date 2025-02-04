import java.io.*;
import java.net.*;

public class MiembroToken implements Runnable {
    int id;
    int port;
    boolean tokenAlInicio;
    boolean soyElUltimo;
    Token token;
    long ultimaRecepcion;

    public MiembroToken(int id, int port, boolean tokenAlInicio, boolean soyElUltimo) {
        this.id = id;
        this.port = port;
        this.tokenAlInicio = tokenAlInicio;
        this.soyElUltimo = soyElUltimo;
        this.token = null;
        this.ultimaRecepcion = System.currentTimeMillis();
    }

    public void run() {
        try (DatagramSocket socket = new DatagramSocket(port)) {
            if (tokenAlInicio) {
                this.token = new Token(id, String.valueOf(id), "Inicio");
                enviarToken();
            }

            while (true) {
                recibirToken(socket);
                procesarToken();
                enviarToken();
            }
        } catch (Exception e) {
            System.out.println("Error en Miembro " + id + ": " + e.getMessage());
        }
    }

    private void recibirToken(DatagramSocket socket) throws IOException, ClassNotFoundException {
        byte[] buffer = new byte[1024];
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
        socket.receive(paquete);

        ByteArrayInputStream bais = new ByteArrayInputStream(paquete.getData());
        ObjectInputStream ois = new ObjectInputStream(bais);
        this.token = (Token) ois.readObject();
        ois.close();

        long ahora = System.currentTimeMillis();//Calcula los milisegundos de respuesta
        System.out.println("Miembro " + id + " recibió el token. Tiempo desde la última recepción: "
                + (ahora - ultimaRecepcion) + "ms");
        ultimaRecepcion = ahora;
    }

    private void procesarToken() throws InterruptedException {
        System.out.println("Miembro " + id + " procesando token...");
        Thread.sleep(id * 1000);  // Simula el tiempo de procesamiento según el ID
    }

    private void enviarToken() throws IOException {
        int puertoDestino = soyElUltimo ? 10000 : port + 1;
        token.usuarioAnterior = token.usuarioActual;
        token.usuarioActual = String.valueOf(id);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(token);
        oos.close();

        byte[] datos = baos.toByteArray();
        DatagramPacket paquete = new DatagramPacket(datos, datos.length, InetAddress.getLocalHost(), puertoDestino);

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.send(paquete);
        }

        System.out.println("Miembro " + id + " envió el token al puerto " + puertoDestino);
    }
}
