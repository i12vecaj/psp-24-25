import java.net.*;
import java.io.*;

public class MiembroToken implements Runnable {
    private final int id, puerto, puertoReverso, siguientePuerto, siguientePuertoReverso;
    private final boolean soyElUltimo;
    private boolean tieneToken, tieneTokenReverso;
    private final Object lock = new Object();
    private long ultimoTiempoToken = 0;
    private long ultimoTiempoTokenReverso = 0;

    public MiembroToken(int id, int puerto, boolean tieneToken, boolean tieneTokenReverso, boolean soyElUltimo, int totalMiembros) {
        this.id = id;
        this.puerto = puerto;
        this.puertoReverso = 20000 + id;
        this.tieneToken = tieneToken;
        this.tieneTokenReverso = tieneTokenReverso;
        this.soyElUltimo = soyElUltimo;
        this.siguientePuerto = soyElUltimo ? 10000 : puerto + 1;
        this.siguientePuertoReverso = (id == 1) ? 20000 + totalMiembros : puertoReverso - 1;
    }

    @Override
    public void run() {
        new Thread(this::escucharToken).start();
        new Thread(this::escucharTokenReverso).start();

        while (true) {
            synchronized (lock) {
                try {
                    while (!tieneToken && !tieneTokenReverso) {
                        lock.wait();
                    }
                    if (tieneToken) {
                        procesarToken("normal");
                        enviarToken();
                    }
                    if (tieneTokenReverso) {
                        procesarToken("reverso");
                        enviarTokenReverso();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void escucharToken() {
        recibirToken(puerto, true);
    }

    private void escucharTokenReverso() {
        recibirToken(puertoReverso, false);
    }

    private void recibirToken(int puertoEscucha, boolean normal) {
        try (DatagramSocket socket = new DatagramSocket(puertoEscucha)) {
            byte[] buffer = new byte[1];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while (true) {
                socket.receive(packet);
                synchronized (lock) {
                    long ahora = System.currentTimeMillis();
                    if (normal) {
                        if (ultimoTiempoToken != 0) {
                            System.out.println("[Nodo " + id + "] Tiempo desde último token: " + (ahora - ultimoTiempoToken) + "ms");
                        }
                        ultimoTiempoToken = ahora;
                        tieneToken = true;
                    } else {
                        if (ultimoTiempoTokenReverso != 0) {
                            System.out.println("[Nodo " + id + "] Tiempo desde último token reverso: " + (ahora - ultimoTiempoTokenReverso) + "ms");
                        }
                        ultimoTiempoTokenReverso = ahora;
                        tieneTokenReverso = true;
                    }
                    lock.notifyAll();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void procesarToken(String tipo) {
        try {
            System.out.println("[Nodo " + id + "] Procesando token (" + tipo + ")...");
            Thread.sleep(1000);
            System.out.println("[Nodo " + id + "] Token (" + tipo + ") procesado.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void enviarToken() {
        enviar(siguientePuerto, "normal");
        synchronized (lock) {
            tieneToken = false;
        }
    }

    private void enviarTokenReverso() {
        enviar(siguientePuertoReverso, "reverso");
        synchronized (lock) {
            tieneTokenReverso = false;
        }
    }

    private void enviar(int destino, String tipo) {
        try (DatagramSocket socket = new DatagramSocket()) {
            byte[] buffer = {1};
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), destino);
            socket.send(packet);
            System.out.println("[Nodo " + id + "] Token (" + tipo + ") enviado a " + destino);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
