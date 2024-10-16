package ua1ex1p2;
/*
Programa que lee los datos de un huerto.
Podria haber hecho una sola clase pasandole los parametros necesarios en el cosntructor pero creo que haciendo una clase abstracta de sensor
el programa gana mucho en escalabilidad
 */
public class main {
    public static void main(String[] args) throws InterruptedException {
        //Mensaje de bienvenida al simpatico programa de lectura del huerto
        System.out.println("Estudio del huerto Lope de Vega");

        //Instanciamos los sensores en la clase Thread porque la clase base implementa la interfaz de Runnable
        Thread sensorTemperatura = new Thread(new SensorTemperatura("Temperatura", (long)1000.0));
        Thread sensorHumedad = new Thread(new SensorHumedad("Humedad", (long)2000.0));
        Thread sensorPlantas = new Thread(new SensorPlantas("Calidad plantas", (long)3000.0));

        //Lanzamos los hilos del proceso
        sensorTemperatura.start();
        sensorHumedad.start();
        sensorPlantas.start();

        //Esperamos a que los hilos termines para seguir con el proceso
        sensorTemperatura.join();
        sensorHumedad.join();
        sensorPlantas.join();

        //Despedimos el simpatico programa de lectura de huerto
        System.out.println("Fin de estudio de huerto Lope de Vega");
    }
}
