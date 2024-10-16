//*Se ha decidido implantar un huerto en un centro público, y se han instalado varios sensores ESP32 para monitorizar la temperatura, la humedad del suelo y el estado de las plantas en distintas áreas del huerto. Se requiere un sistema en Java que simule la lectura de estos sensores de manera concurrente para obtener los datos en tiempo real.

//*Tu tarea es diseñar una solución que simule el monitoreo de estos sensores usando hilos en Java. El sistema debe realizar lo siguiente:

//*Crear tres hilos para simular los sensores: uno para la temperatura, otro para la humedad del suelo y otro para el estado de las plantas.

//*Cada sensor (hilo) debe:

//*Generar un valor aleatorio para la medición correspondiente.
//*Dormir durante un tiempo aleatorio entre 1 y 3 segundos para simular la espera entre lecturas.
//*Imprimir el valor generado con un mensaje que indique qué sensor hizo la lectura y en qué momento (puedes usar System.currentTimeMillis() para esto).
//*Los datos de los sensores deben ser leídos de manera concurrente.

//*El programa debe ejecutarse durante 10 ciclos de medición (10 iteraciones por sensor).

//*Funcionalidades Requeridas (FR):
//*FR1 (2 puntos): Crear un hilo para cada sensor (temperatura, humedad y estado de las plantas).
//*FR2 (2 puntos): Generar valores aleatorios para cada medición de sensor.
//*FR3 (2 puntos): Simular la espera de tiempo entre lecturas con Thread.sleep().
//*FR4 (2 puntos): Mostrar las lecturas de cada sensor con su correspondiente marca de tiempo.
//*FR5 (2 puntos): Ejecutar correctamente los 10 ciclos de medición por cada sensor.*//
import java.util.Random;

public class Sensores implements Runnable{
  
// Tenia puesto El tipo de sensor y los ciclos en private pero me daba errores de vez en cuando
//He decidido por recomendacion de visual studio ponerlo como private final
    private final String tipodeSensor;
    private final int ciclos;

    public Sensores(String tipodeSensor, int ciclos) {
        this.tipodeSensor = tipodeSensor;
        this.ciclos = ciclos;
    }

    @Override
    public void run() {
        Random random = new Random();

           
        for (int i = 0; i < ciclos; i++) {

            // Valor random para la temperatura. Puesto entre 1 y 100 grados. si es 100 pues x_x
            int valor = random.nextInt(100); 
            
            //Funcion long cogida de uno de los ejercicios de la pagina que te comente antes:https://retosdeprogramacion.com/ejercicios
            //Funcion que te dice el tiempo actual
            long tiempoActual = System.currentTimeMillis();
            
            System.out.println("El sensor de tipo " + tipodeSensor + ". Da una temperatura de : " + valor + " (El tiempo que hace hoy es de: " + tiempoActual + ")");
            
            try {// Tiempo de descanso puento en ms
                Thread.sleep(random.nextInt(3000) + 1000); // Es decir 3000ms= 3s y 1000ms= 1s
            } catch (InterruptedException e) { 
                Thread.currentThread().interrupt();
            }
        }
    }
}




