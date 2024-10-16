/*PARTE PRÁCTICA 2/2

Se ha decidido implantar un huerto en un centro público, y se han instalado varios sensores ESP32 para monitorizar la temperatura, la humedad del suelo y el estado de las plantas en
distintas áreas del huerto. Se requiere un sistema en Java que simule la lectura de estos sensores de manera concurrente para obtener los datos en tiempo real.

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
El nombre del fichero .c a entregar debe ser: examen\dev_X\ ua1ex1p2.c , es decir, el fichero ua1ex1.cdebe estar ubicado en tu carpeta dev_X\

No entregues tu solución, hasta que no se indique por parte del profesorado.
*/
package Ejercicio2;

public class ua1ex1p2 {
    public static void main(String[] args) throws InterruptedException {
        int numeroDormir =3;
        int dormir = (int) (Math.random() * numeroDormir) + 1;

        //Hilo relacionado con la temperatura
        Thread temperatura = new Thread(new Temperatura());
        temperatura.start();
        temperatura.join();
        //Hilo relacionado con la humedad
        Thread humedad = new Thread(new Humedad());
        humedad.start();
        humedad.join();
        //Hilo relacionado con el estado de las plantas
        Thread estado = new Thread(new EstadoPlantas());
        estado.start();
        estado.join();


    }

}
