/**
 * @file        MiembroToken.java
 *
 * @brief       Solución para la actividad evaluable de la UA3.
 * @date        14/12/2022
 * @author      Manuel Torres Alcázar Miembro 1 - manolotorraz@gmail.com (dev_30)
 * @author      Oscar Muñoz Molinero Miembro 2 - oscar.3598@gmail.com (dev_21)
 * @author      Adrián Cruz Barranco Miembro 3 - adriancb2023@gmail.com (dev_8)
 * @author      Nombre Apellidos Miembro 4 - github@email (dev_D)
 *
 * @note        ¡Pero qué diablos!
 * @note        https://docs.google.com/presentation/d/e/2PACX-1vQzxbSl2IXLxelLggksAWEQd2tDT-5sNqETQCpIHCnNBqjoSmbvlJdCcOfu_rjQLz_BN6lsoOjimqSO/pub?start=false&loop=false&delayms=3000
 */

/*
* @class    MiembroToken
* @brief    Clase para simular el comportamiento de una red token ring
*
* @todo FR1 [5 puntos]: Implementa la clase MiembroToken con la funcionalidad descrita previamente. Para comprobar la correcta ejecución del sistema, la clase MiembroToken debe imprimir por pantalla la suficiente información para ver el estado de cada Miembro de la red.
* @todo FR2 [2,5 puntos]: Mejora la clase MiembroToken para poder ejecutar su funcionalidad, en este caso, dormir una cantidad de tiempo determinada, como un hilo.
* @todo FR4 [1 punto]: Mejora la clase MiembroToken para crear una red token ring de anillo doble, es decir, se puede tener otro token en otro anillo virtual, en sentido contrario.
*/

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
            System.out.println("Miembro 1 recibido");
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
