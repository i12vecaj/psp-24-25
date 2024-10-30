import java.util.*;

class Sensor implements Runnable {
    private String tipoSensor;

    // El constructor recibe el tipo de sensor y lo guarda.
    public Sensor(String tipoSensor) {
        this.tipoSensor = tipoSensor;
    }

    @Override
    public void run() {
        Random randomGenerador = new Random();
        // Simulamos 10 lecturas.
        for (int lectura = 1; lectura <= 10; lectura++) {
            int valorMedicion = randomGenerador.nextInt(100);
            int tiempoEspera = randomGenerador.nextInt(3) + 1;

            System.out.println("[" + System.currentTimeMillis() + "] " + tipoSensor + " - Lectura " + lectura + ": " + valorMedicion);

            try {
                // Simular el tiempo de espera entre lecturas
                Thread.sleep(tiempoEspera * 1000);
            } catch (InterruptedException e) {
                System.out.println(tipoSensor + " ha sido interrumpido.");
            }
        }
        System.out.println(tipoSensor + " ha completado sus 10 ciclos de lectura.");
    }
}

public class Main {

    public static void main(String[] args) {
        // Creamos un hilo para cada sensor.
        Thread hiloTemperatura = new Thread(new Sensor("Datos de Temperatura"));
        Thread hiloHumedad = new Thread(new Sensor("Datos de Humedad"));
        Thread hiloEstadoPlantas = new Thread(new Sensor("Estado de Plantas"));

        // Arrancamos cada hilo.
        hiloTemperatura.start();
        hiloHumedad.start();
        hiloEstadoPlantas.start();

        try {
            // Esperar a que todos los hilos terminen
            hiloTemperatura.join();
            hiloHumedad.join();
            hiloEstadoPlantas.join();
        } catch (InterruptedException e) {
            System.out.println("La ejecución fue interrumpida.");
        }

        System.out.println("Monitoreo del huerto completado.");
    }
}
