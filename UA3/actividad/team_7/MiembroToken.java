import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.Duration;
import java.time.Instant;

public class MiembroToken {
    private int puertoEscucha; //Declaramos el puerto en el que este miembro escucha el token
    private int puertoSiguiente; //Declaramos el puerto del siguiente miembro al que se enviará el token
    private int tiempoTarea; //Declaramos el tiempo que tardara en ejecutar su tarea
    private boolean tieneToken; //Indicamos si el miembro tiene el token o no
    private Instant ultimaRecepcion; //Guardamos el instante de la última recepción del token
    private boolean enEjecucion; //Controlamos si el miembro sigue funcionando

    // Constructor de la clase que inicializa los valores necesarios
    public MiembroToken(int puertoEscucha, int puertoSiguiente, int tiempoTarea, boolean tieneTokenInicial) {
        this.puertoEscucha = puertoEscucha; //Asignamos el puerto de escucha
        this.puertoSiguiente = puertoSiguiente; //Asignamos el puerto del siguiente miembro
        this.tiempoTarea = tiempoTarea; //Definimos cuánto durará la tarea
        this.tieneToken = tieneTokenInicial; //Indicamos si el miembro empieza con el token
        this.enEjecucion = true; //Iniciamos el miembro en modo activo
    }

    //Metodo en el cual iniciamos la ejecucion del miembro
    public void iniciar() {
        try (DatagramSocket socket = new DatagramSocket(puertoEscucha)) { //Creamos un socket UDP que escucha en el puerto especificado
            System.out.println("Miembro en puerto " + puertoEscucha + " esperando TOKEN...");

            //Bucle principal que se ejecuta mientras el miembro este activo
            while (enEjecucion) {
                if (tieneToken) { //Si el miembro tiene el token:
                    ejecutarTarea(); //Ejecutamos su tarea
                    enviarToken(); //Luego pasamos el token al siguiente miembro
                } else {
                    recibirToken(socket);  //Si el miembro no tiene el token, espera a recibirlo
                }
            }
        } catch (Exception e) {  //Capturamos cualquier excepción que ocurra durante la ejecución
            e.printStackTrace();
        }
    }

    //Metodo en el que recibimos el token desde otro miembro
    private void recibirToken(DatagramSocket socket) throws Exception {
        byte[] buffer = new byte[1];  //Creamos un buffer de 1 byte para recibir el token
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);  //Preparamos el paquete para recibir datos

        socket.receive(paquete);  //El socket espera recibir un paquete
        tieneToken = buffer[0] == 1;  //Si el byte recibido es 1, significa que es el token

        if (tieneToken) {  //Si se ha recibido el token:
            if (ultimaRecepcion != null) {  //Si ya habíamos recibido el token antes:
                Instant ahora = Instant.now();  //Obtenemos el instante actual
                Duration tiempoTranscurrido = Duration.between(ultimaRecepcion, ahora);  //Calculamos el tiempo transcurrido desde la última recepcion
                System.out.println("Tiempo transcurrido desde el último TOKEN: " +
                        tiempoTranscurrido.toMillis() + " ms");  //Mostramos el tiempo que tardo en regresar el token
            }
            ultimaRecepcion = Instant.now(); //Guardamos el instante actual como la última recepcion del token
        }
    }

    //Metodo donde simulamos la ejecucion de una tarea cuando el miebro tenga el token
    private void ejecutarTarea() throws InterruptedException {
        System.out.println("Miembro en puerto " + puertoEscucha + " ejecutando tarea...");
        Thread.sleep(tiempoTarea * 1000); //Simulamos el tiempo de ejecucion de la tarea pausando el hilo
        System.out.println("Tarea completada en miembro " + puertoEscucha);
    }

    //Metodo en el cual enviamos el token al siguiente miebro del anillo
    private void enviarToken() throws Exception {
        byte[] buffer = {1};  //El token se representa con un byte que contiene el valor 1
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), puertoSiguiente);  //Creamos el paquete para enviar al siguiente miembro

        try (DatagramSocket socket = new DatagramSocket()) { //Creamos un socket temporal para enviar el paquete
            socket.send(paquete); //Enviamos el token al puerto del siguiente miembro
        }

        tieneToken = false;  //Despues de enviar el token, el miembro ya no lo tiene
        System.out.println("TOKEN enviado al puerto " + puertoSiguiente); //Mostramos un mensaje indicando que el token fue enviado
    }

    //Metodo para detener la ejecucion
    public void detener() {
        enEjecucion = false;  //Cambiamos el estado a falso para detener el bucle principal
        System.out.println("Miembro en puerto " + puertoEscucha + " detenido."); //Mostramos un mensaje indicando que el miembro se detuvo
    }
}
