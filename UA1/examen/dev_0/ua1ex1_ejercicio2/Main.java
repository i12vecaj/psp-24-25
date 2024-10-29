import java.util.Random;

class Sensor implements Runnable {
    private String tipo;

    public Sensor(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public void run() {
        Random rand = new Random();
        for (int i = 1; i <= 10; i++) {
            int valor = tipo.equals("DATOS DE TEMPERATURA") ? rand.nextInt(41) :
                    tipo.equals("DATOS DE HUMEDAD") ? rand.nextInt(101) :
                            rand.nextInt(2);
            int tiempoLectura = rand.nextInt(3) + 1;
            System.out.println("[" + System.currentTimeMillis() + "] " + tipo + " - Lectura " + i + ": " + valor);

            try {
                Thread.sleep(tiempoLectura * 1000);
            } catch (InterruptedException e) {
                System.out.println(tipo + " ha sido interrumpido.");
            }
        }
        System.out.println(tipo + " ha completado sus 10 ciclos de lectura.");
    }
}

public class Main {
    public static void main(String[] args) {
        Thread sensorTemperatura = new Thread(new Sensor("DATOS DE TEMPERATURA"));
        Thread sensorHumedad = new Thread(new Sensor("DATOS DE HUMEDAD"));
        Thread sensorEstadoPlantas = new Thread(new Sensor("Estado Plantas"));

        sensorTemperatura.start();
        sensorHumedad.start();
        sensorEstadoPlantas.start();

        try {
            sensorTemperatura.join();
            sensorHumedad.join();
            sensorEstadoPlantas.join();
        } catch (InterruptedException e) {
            System.out.println("La ejecución fue interrumpida.");
        }

        System.out.println("Monitoreo del huerto completado.");
    }
}