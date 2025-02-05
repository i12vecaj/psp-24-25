import java.net.*;
import java.io.*;

public class MiembroToken {
    private int id; // Identificador del miembro
    private int puerto; // Puerto en el que escucha el miembro
    @SuppressWarnings("unused")
    private boolean token; // Indica si el miembro tiene el token
    private boolean soyElUltimo; // Indica si este miembro es el último en la red
    private long ultimoTiempo; // Tiempo en el que recibió el último token

    // Constructor de la clase
    public MiembroToken(int id, int puerto, boolean token, boolean soyElUltimo) {
        this.id = id;
        this.puerto = puerto;
        this.token = token;
        this.soyElUltimo = soyElUltimo;
        this.ultimoTiempo = System.currentTimeMillis(); // Guarda el tiempo actual

        // Si el miembro tiene el token al inicio, lo envía inmediatamente
        if (token) {
            enviarToken();
        }
    }

    // Método principal que inicia la espera del token
    public void iniciar() {
        try (DatagramSocket socket = new DatagramSocket(puerto)) { // Crea un socket UDP en el puerto especificado
            byte[] buffer = new byte[256]; // Buffer para recibir datos
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            
            while (true) {
                System.out.println("Miembro " + id + " esperando el token en el puerto " + puerto);
                socket.receive(packet); // Espera la llegada del token
                token = true; // Indica que ha recibido el token
                long tiempoActual = System.currentTimeMillis();
                System.out.println("Miembro " + id + " ha recibido el token. Tiempo transcurrido: " + (tiempoActual - ultimoTiempo) + " ms");
                ultimoTiempo = tiempoActual;
                
                ejecutarTarea(); // Ejecuta su tarea antes de pasar el token
                enviarToken(); // Pasa el token al siguiente miembro
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Simula la ejecución de una tarea al recibir el token
    private void ejecutarTarea() {
        try {
            System.out.println("Miembro " + id + " ejecutando tarea...");
            Thread.sleep(id * 1000); // Simula la ejecución con un tiempo de espera variable según el ID
            System.out.println("Miembro " + id + " ha terminado su tarea.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // Método que envía el token al siguiente miembro en la red
    private void enviarToken() {
        token = false; // Indica que ya no tiene el token
        int puertoDestino = soyElUltimo ? 10000 : puerto + 1; // Determina a qué puerto enviar el token
        
        try (DatagramSocket socket = new DatagramSocket()) { // Crea un socket UDP para enviar el token
            byte[] buffer = "TOKEN".getBytes(); // Mensaje de token
            InetAddress address = InetAddress.getByName("localhost"); // Dirección local
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, puertoDestino);
            socket.send(packet); // Envía el paquete con el token
            System.out.println("Miembro " + id + " ha enviado el token al puerto " + puertoDestino);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método principal para ejecutar el programa desde la línea de comandos
    public static void main(String[] args) {
        if (args.length != 4) { // Verifica que los argumentos sean correctos
            System.out.println("Uso: java MiembroToken [id] [puerto] [token_al_inicio] [soy_el_ultimo]");
            return;
        }
        
        int id = Integer.parseInt(args[0]); // ID del miembro
        int puerto = Integer.parseInt(args[1]); // Puerto en el que escucha
        boolean token = args[2].equalsIgnoreCase("yes"); // Indica si inicia con el token
        boolean soyElUltimo = args[3].equalsIgnoreCase("yes"); // Indica si es el último nodo
        
        MiembroToken miembro = new MiembroToken(id, puerto, token, soyElUltimo);
        miembro.iniciar(); // Inicia la espera del token
    }
}