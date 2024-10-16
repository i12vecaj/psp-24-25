import org.w3c.dom.ls.LSOutput;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println("Ciclo de recopilacion de datos " + (i + 1) + "\n-------------------------------------------");

            Thread hilo1 = new Thread(new Temperatura());
            Thread hilo2 = new Thread(new Humedad());
            Thread hilo3 = new Thread(new EstadoPlanta());

            hilo1.start();
            hilo2.start();
            hilo3.start();

            hilo1.join(); 
            hilo2.join();
            hilo3.join();

            System.out.println("");
        }
    }
}

class Temperatura implements Runnable {
    LocalTime time = LocalTime.now(); // He usa el objeto LocalTime por que me permite poner un formato de hora y minutos de manera más fácil. No usando el proporcionado.
    @Override
    public void run() {
        double celcius = Math.round(Math.random() * 35); //Establecemos una temperatura máxima aleatoria de 35º

        try {
            int timeSleep = (int) Math.round(Math.random() * 2000) + 1000;
            Thread.sleep(timeSleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("La temepratura es de: " + celcius + "º" + " | " + time.getMinute() + ":" + time.getSecond());
    }
}

class Humedad implements Runnable {
    LocalTime time = LocalTime.now();
    @Override
    public void run() {
        int porcentajeHumedad = (int) Math.round(Math.random() * 100);

        try {
            int timeSleep = (int) Math.round(Math.random() * 2000) + 1000;
            Thread.sleep(timeSleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Nivel de humedad: " + porcentajeHumedad + "%" + " | " + time.getMinute() + ":" + time.getSecond());
    }
}

class EstadoPlanta implements Runnable {
    LocalTime time = LocalTime.now();
    @Override
    public void run() {
        boolean estado = (int) Math.round(Math.random() * 1) == 1; //Como devuelve 0 o 1 se le asigna directamente de manera aleatoria el true o el false
                                                                   //En un principio habia utilizado un ternario pero el ID me recomendo abreviarlo asi.
        try {
            int timeSleep = (int) Math.round(Math.random() * 2000) + 1000;
            Thread.sleep(timeSleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("La planta sigue viva: " + estado + " | " + time.getMinute() + ":" + time.getSecond());
    }
}

/*
Se ha decidido implantar un huerto en un centro público, y se han instalado varios sensores ESP32 para monitorizar la temperatura, la humedad del
suelo y el estado de las plantas en distintas áreas del huerto. Se requiere un sistema en Java que simule la lectura de estos sensores de manera concurrente
para obtener los datos en tiempo real.

Tu tarea es diseñar una solución que simule el monitoreo de estos sensores usando hilos en Java. El sistema debe realizar lo siguiente:

Crear tres hilos para simular los sensores: uno para la temperatura, otro para la humedad del suelo y otro para el estado de las plantas.

Cada sensor (hilo) debe:

Generar un valor aleatorio para la medición correspondiente.
Dormir durante un tiempo aleatorio entre 1 y 3 segundos para simular la espera entre lecturas.
Imprimir el valor generado con un mensaje que indique qué sensor hizo la lectura y en qué momento (puedes usar System.currentTimeMillis() para esto).
Los datos de los sensores deben ser leídos de manera concurrente.

El programa debe ejecutarse durante 10 ciclos de medición (10 iteraciones por sensor).

Mejora opcional: Implementa una forma de detener todos los sensores después de que hayan hecho las 10 lecturas.

Funcionalidades Requeridas (FR):
FR1 (2 puntos): Crear un hilo para cada sensor (temperatura, humedad y estado de las plantas).
FR2 (2 puntos): Generar valores aleatorios para cada medición de sensor.
FR3 (2 puntos): Simular la espera de tiempo entre lecturas con Thread.sleep().
FR4 (2 puntos): Mostrar las lecturas de cada sensor con su correspondiente marca de tiempo.
FR5 (2 puntos): Ejecutar correctamente los 10 ciclos de medición por cada sensor.

Notas:

Los comentarios (descriptivos y concisos) en el código ... siempre son bien.
Los nombres de las variables autodescriptivos ... siempre son bien.
Las impresión por pantalla, correctamente indentada y verticalmente espaciada ... siempre es bien.
Los warnings del presente ... son los errores del futuro.


No entregues tu solución, hasta que no se indique por parte del profesorado.
*/