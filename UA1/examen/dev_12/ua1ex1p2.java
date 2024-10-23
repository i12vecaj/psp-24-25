
class Sensor implements Runnable {
    private String nombreSensor;
    private int tiempoLectura;

    public Sensor(String nombreSensor) {
        this.nombreSensor = nombreSensor;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            // Generar un valor aleatorio para la medición (ajustado según el tipo de sensor)
            double valorMedicion = 0;
            String unidadMedicion = "";  // Esta variable almacenará la unidad de medida adecuada
            
            if (nombreSensor.contains("Temperatura")) {
                valorMedicion = Math.round((random.nextDouble() * 30) * 100.0) / 100.0; // De 0 a 30°C
                unidadMedicion = "°C";  // Unidad de temperatura
            } else if (nombreSensor.contains("Humedad")) {
                valorMedicion = Math.round((random.nextDouble() * 100) * 100.0) / 100.0; // De 0% a 100% de humedad
                unidadMedicion = "%";  // Unidad de porcentaje de humedad
            } else if (nombreSensor.contains("Estado de Plantas")) {
                valorMedicion = Math.round((random.nextDouble() * 100) * 100.0) / 100.0; // De 0% a 100% de estado
                unidadMedicion = "%";  // Unidad de porcentaje del estado de las plantas
            }
            
            
            // Tiempo aleatorio de espera entre 1 y 3 segundos
            tiempoLectura = random.nextInt(3) + 1;
            
            
            try {
                // Simular la espera de tiempo entre lecturas
                Thread.sleep(tiempoLectura * 1000); 
            } catch (InterruptedException e) {
                System.out.println("Error en el hilo" + nombreSensor);
            }

            
            long currentTime = System.currentTimeMillis();
            System.out.println(nombreSensor + ": " + valorMedicion + " " + unidadMedicion + "| Momento de la lectura: " + currentTime + " ms");
        }
    }

    public static void main(String[] args) {
        // Crear hilos para los tres sensores: Temperatura, Humedad, Estado de las plantas
        Thread sensorTemperatura = new Thread(new Sensor("Sensor de Temperatura"));
        Thread sensorHumedad = new Thread(new Sensor("Sensor de Humedad"));
        Thread sensorEstadoPlantas = new Thread(new Sensor("Sensor de Estado de Plantas"));

        // Iniciar los hilos
        sensorTemperatura.start();
        sensorHumedad.start();
        sensorEstadoPlantas.start();
        
        try {
            // Esperar a que todos los hilos terminen
            sensorTemperatura.join();
            sensorHumedad.join();
            sensorEstadoPlantas.join();
        } catch (InterruptedException e) {
            System.out.println("La ejecución fue interrumpida.");
        }

    }
}



// PARTE PRÁCTICA 2/2

// Se ha decidido implantar un huerto en un centro público, y se han instalado varios sensores ESP32 para monitorizar la temperatura, la humedad del suelo y el estado de las plantas en distintas áreas del huerto. Se requiere un sistema en Java que simule la lectura de estos sensores de manera concurrente para obtener los datos en tiempo real.

// Tu tarea es diseñar una solución que simule el monitoreo de estos sensores usando hilos en Java. El sistema debe realizar lo siguiente:

// Crear tres hilos para simular los sensores: uno para la temperatura, otro para la humedad del suelo y otro para el estado de las plantas.

// Cada sensor (hilo) debe:

// Generar un valor aleatorio para la medición correspondiente.
// Dormir durante un tiempo aleatorio entre 1 y 3 segundos para simular la espera entre lecturas.
// Imprimir el valor generado con un mensaje que indique qué sensor hizo la lectura y en qué momento (puedes usar System.currentTimeMillis() para esto).
// Los datos de los sensores deben ser leídos de manera concurrente.

// El programa debe ejecutarse durante 10 ciclos de medición (10 iteraciones por sensor).

// Mejora opcional: Implementa una forma de detener todos los sensores después de que hayan hecho las 10 lecturas.

// Funcionalidades Requeridas (FR):
// FR1 (2 puntos): Crear un hilo para cada sensor (temperatura, humedad y estado de las plantas).
// FR2 (2 puntos): Generar valores aleatorios para cada medición de sensor.
// FR3 (2 puntos): Simular la espera de tiempo entre lecturas con Thread.sleep().
// FR4 (2 puntos): Mostrar las lecturas de cada sensor con su correspondiente marca de tiempo.
// FR5 (2 puntos): Ejecutar correctamente los 10 ciclos de medición por cada sensor.

// Notas:

// Los comentarios (descriptivos y concisos) en el código ... siempre son bien.
// Los nombres de las variables autodescriptivos ... siempre son bien.
// Las impresión por pantalla, correctamente indentada y verticalmente espaciada ... siempre es bien.
// Los warnings del presente ... son los errores del futuro.
// El nombre del fichero .c a entregar debe ser: examen\dev_X\ejercicio2/ua1ex1p2.x , es decir, el fichero ua1ex1.cdebe estar ubicado en tu carpeta dev_X\

// No entregues tu solución, hasta que no se indique por parte del profesorado.