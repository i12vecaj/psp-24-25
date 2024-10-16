
import java.util.Random;

public class hilo {
    public static void main(String[] args) {
        // Creamos los hilos
        Thread temperatura = new temperatura();
        Thread humidumeda = new humidumeda();
        Thread estado = new estado();

        //enecedemos los hilos
        temperatura.start();
        humidumeda.start();
        estado.start();
    }
}

// sensor de la temperatura
class temperatura extends Thread {
    private static final int circulos = 10;
    private Random random = new Random();

    @Override
    public void run() {
        for (int i = 0; i < circulos; i++) {
            int indicadortemperatura = random.nextInt(35); //vamos agenerar una temeratura avitudada a cordoba de entre 0 y 48
            System.out.printf("Temperatura: %d °C, Temporizador: %d ms\n", indicadortemperatura, System.currentTimeMillis());
            try {
                Thread.sleep(random.nextInt(3000) + 1000); // a dormir en 1 a 3 segundos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

//  sensor de la humedad
class humidumeda extends Thread {
    private static final int circulo2 = 10;
    private Random random = new Random();

    @Override
    public void run() {
        for (int i = 0; i < circulo2; i++) {
            int humedad = random.nextInt(110); // Genera humedad aleatoria entre 0 y 100
            System.out.printf("Humedad: %d %%, temporizador: %d ms\n", humedad, System.currentTimeMillis());
            try {
                Thread.sleep(random.nextInt(3000) + 1000); // a dormir en 1 a 3 segundos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

// sensor de estado de las plantas
class estado extends Thread {
    private static final int circulo3 = 10;
    private Random random = new Random();

    @Override
    public void run() {
        for (int i = 0; i < circulo3; i++) {
            String status = random.nextBoolean() ? "Saludable" : "Enfermo" ; // vamos a saber si la tierra esta bien o mal "queria poner que tambien se supiera que si la tierra era fertil o inferti "
            System.out.printf("Estado de las plantas: %s, temporizador: %d ms\n", status, System.currentTimeMillis());
            try {
                Thread.sleep(random.nextInt(3000) + 1000); // a dormir en 1 a 3 segundos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

