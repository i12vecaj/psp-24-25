import java.util.Random;

class Sensor implements Runnable {
    private String nombreSensor;
    private int ciclos;
    private Random random;

    //Constructor que inicia los atributos.
    public Sensor(String nombreSensor, int ciclos) {
        this.nombreSensor = nombreSensor;
        this.ciclos = ciclos;
        this.random = new Random();
    }

    @Override
    public void run() {
        for (int i = 1; i <= ciclos; i++) {
            int valorMedicion = random.nextInt(101); //Se genera un valor aleatorio.
            long tiempoActual = System.currentTimeMillis(); //Se obtiene el tiempo actual.

            System.out.println("[" + tiempoActual + "] " + nombreSensor + " - Medición " + i + ": " + valorMedicion);

            // Espera de 1 a 3 segundos
            try {
                int tiempoEspera = random.nextInt(3000) + 1000; //Este es el tiempo de espera que hay entre hilos.
                Thread.sleep(tiempoEspera);
            } catch (InterruptedException e) {
                System.out.println(nombreSensor + " ha sido interrumpido.");
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println(nombreSensor + " ha completado sus " + ciclos + " ciclos de lectura.");
    }
}

//La clase principal.
public class ua1ex1p2correcion {
    public static void main(String[] args) {
        int ciclos = 10; //Tengo 10 cilos de medición.

        //Creo hilos para los sensores.
        Thread sensorTemperatura = new Thread(new Sensor("Sensor de Temperatura", ciclos));
        Thread sensorHumedad = new Thread(new Sensor("Sensor de Humedad", ciclos));
        Thread sensorEstadoPlantas = new Thread(new Sensor("Sensor de Estado de Plantas", ciclos));

        //Inicio los hilos.
        sensorTemperatura.start();
        sensorHumedad.start();
        sensorEstadoPlantas.start();

        //Espero a que todos los hilos terminen.
        try {
            sensorTemperatura.join();
            sensorHumedad.join();
            sensorEstadoPlantas.join();
        } catch (InterruptedException e) {
            System.out.println("La ejecución fue interrumpida.");
        }

        System.out.println("Monitoreo de sensores finalizado.");
    }
}