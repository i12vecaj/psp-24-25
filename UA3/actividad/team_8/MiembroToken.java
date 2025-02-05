package Actividad_Cliente_Servidor_Token;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MiembroToken extends Thread {
    private DatagramSocket socket;
    private int puertoEscucha;
    private int tiempoEspera;
    private boolean tieneToken;
    private boolean esUltimoMiembro;
    private InetAddress direccionSiguiente;
    private int puertoSiguiente;
    private int id;

    public MiembroToken(int puertoEscucha, int tiempoEspera, boolean tieneToken, boolean esUltimoMiembro, InetAddress direccionSiguiente, int puertoSiguiente, int id) throws Exception {
        this.puertoEscucha = puertoEscucha;
        this.tiempoEspera = tiempoEspera;
        this.tieneToken = tieneToken;
        this.esUltimoMiembro = esUltimoMiembro;
        this.direccionSiguiente = direccionSiguiente;
        this.puertoSiguiente = puertoSiguiente;
        this.socket = new DatagramSocket(puertoEscucha);
        this.id = id;
    }

    @Override
    public void run() {
        try {
            // 10 ciclos de envío y recepción de token
            for (int i = 0; i < 10; i++) {
                if (tieneToken) {
                    System.out.println("Soy el miembro " + id);
                    manejarToken();
                    estadoToken();
                } else {
                    miembroRecibido();
                    recibirToken();
                    estadoToken();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void miembroRecibido() {
        if (puertoEscucha == 10000) {
            System.out.println("Miembro 3 recibido");
        } else if (puertoEscucha == 10001) {
            System.out.println("Miembro 1 recibido ");
        } else if (puertoEscucha == 10002) {
            System.out.println("Miembro 2 recibido");
        }
    }

    private void estadoToken() {
        System.out.println("Tiene token: " + tieneToken);
    }

    private void recibirToken() throws Exception {
        byte[] buffer = new byte[256];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        tieneToken = true;
        System.out.println("Token recibido en el puerto " + puertoEscucha);
    }

    private void manejarToken() throws Exception {
        long tiempoInicio = System.currentTimeMillis();
        System.out.println("Token manejado en el puerto " + puertoEscucha);
        Thread.sleep(tiempoEspera * 1000);
        long tiempoFin = System.currentTimeMillis();
        System.out.println("Tiempo con el token: " + (tiempoFin - tiempoInicio) + " ms");

        if (!esUltimoMiembro) {
            enviarToken();
        }
        tieneToken = false;
    }

    private void enviarToken() throws Exception {
        byte[] buffer = "TOKEN".getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, direccionSiguiente, puertoSiguiente);
        socket.send(packet);
        System.out.println("Token enviado al puerto " + puertoSiguiente);
    }
}