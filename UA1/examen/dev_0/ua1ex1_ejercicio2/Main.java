import java.util.*;

class Sensor implements Runnable {
    private String tipo;

    // El constructor recibe el tipo de sensor y lo guarda.
    public Sensor(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public void run() {
        Random rand = new Random();
        // Simulamos 10 lecturas.
        for (int i = 0; i < 10; i++) {

            //Generamos un valor aleatorio apropiado.
            if (tipo.equals("DATOS DE TEMPERATURA")) {
            } else if (tipo.equals("DATOS DE HUMEDAD"))
            System.out.println(tipo + " - DATO: " + valor + " - TIEMPO: " + System.currentTimeMillis());

        }
    }
}

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Creamos un hilo para cada sensor .
        Thread dato_temperatura = new Thread(new Sensor("DATOS DE TEMPERATURA"));
        Thread dato_humedad = new Thread(new Sensor("DATOS DE HUMEDAD"));
        Thread dato_plantas = new Thread(new Sensor("Estado Plantas"));

        // Arrancamos cada hilo.
        dato_temperatura.start();
        dato_humedad.start();
        dato_plantas.start();

        // Esperamos a terminen su ejecución.
        dato_temperatura.join();
        dato_humedad.join();
        dato_plantas.join();

        // Una vez que todos terminaran muestro un mensaje final.
        System.out.println("DATOS COGIDOS CORRECTAMENTE.");
    }
}
