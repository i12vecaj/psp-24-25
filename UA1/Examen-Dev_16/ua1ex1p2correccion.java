import java.util.Random;

// Clase que representa un sensor y permite su ejecución en un hilo.
class Sensor implements Runnable {
    private String nombreSensor; // Nombre del sensor.
    private int ciclos; // Número de ciclos de medición.
    private Random random; // Generador de números aleatorios.

    // Constructor de la clase Sensor que inicializa los atributos.
    public Sensor(String nombreSensor, int ciclos) {
        this.nombreSensor = nombreSensor; // Inicializa el nombre del sensor.
        this.ciclos = ciclos; // Inicializa el número de ciclos.
        this.random = new Random(); // Inicializa el generador de números aleatorios.
    }

    // Método que se ejecuta al iniciar el hilo.
    @Override
    public void run() {
        for (int i = 1; i <= ciclos; i++) {
            // Generar un valor aleatorio para la medición.
            int valorMedicion = generarValor(); // Genera un valor de medición aleatorio.
            
            // Imprimir la medición junto con la marca de tiempo actual.
            System.out.println("[" + System.currentTimeMillis() + "] " + nombreSensor + " - Lectura " + i + ": " + valorMedicion);
            
            // Generar un tiempo de espera aleatorio entre 1 y 3 segundos.
            int tiempoLectura = random.nextInt(3) + 1; // Se genera el tiempo de espera.
            try {
                // Dormir el hilo durante el tiempo generado.
                Thread.sleep(tiempoLectura * 1000); 
            } catch (InterruptedException e) {
                System.out.println(nombreSensor + " ha sido interrumpido.");
                Thread.currentThread().interrupt(); // Restablecer el estado de interrupción.
                break; // Salir del bucle si se interrumpe el hilo.
            }
        }
        System.out.println(nombreSensor + " ha completado sus " + ciclos + " ciclos de lectura."); // Indica que se han completado los ciclos.
    }

    // Método que genera un valor aleatorio para la medición del sensor.
    private int generarValor() {
        return random.nextInt(101); // Retorna un valor entre 0 y 100.
    }

    public static void main(String[] args) {
        int ciclos = 10; // Número de ciclos de medición para cada sensor.

        // Crear hilos para los tres sensores.
        Thread sensorTemperatura = new Thread(new Sensor("Sensor de Temperatura", ciclos));
        Thread sensorHumedad = new Thread(new Sensor("Sensor de Humedad", ciclos));
        Thread sensorEstadoPlantas = new Thread(new Sensor("Sensor de Estado de Plantas", ciclos));

        // Iniciar los hilos.
        sensorTemperatura.start();
        sensorHumedad.start();
        sensorEstadoPlantas.start();
        
        try {
            // Esperar a que todos los hilos terminen.
            sensorTemperatura.join();
            sensorHumedad.join();
            sensorEstadoPlantas.join();
        } catch (InterruptedException e) {
            System.out.println("La ejecución fue interrumpida.");
        }

        System.out.println("Monitoreo del huerto completado."); // Indica que el monitoreo ha terminado.
    }
}