public class Main {
    public static void main(String[] args) {
        System.out.println("Sensores");
        Thread sensorTemperatura = new Thread(new Sensor("Sensor de Temperatura"));
        Thread sensorHumedad =  new Thread(new Sensor("Sensor de Humedad"));
        Thread sensorEstadoDePlantas=  new Thread(new Hilo3());
        sensorTemperatura .start();
        sensorHumedad.start();
        sensorEstadoDePlantas.start();
    }
}
// he implementado el ejemplo que nos has puesto en el sensor 1 y 2.
class Sensor implements Runnable{
    private String nombreSensor;

    public  Sensor(String nombreSensor){
        this.nombreSensor = nombreSensor;
    }
    @Override
    public void  run(){
        for (int i = 0; i < 10; i++) {
            System.out.println(nombreSensor+" -Checkeo "+i+" : "+Math.random()*100);

        }
        System.out.println(nombreSensor + " ha completado sus 10 ciclos de lectura.");
    }
}


// lo he dejado igual que lo tenia antes ya que no me gusta catalogar el estado de las plantas del 1 al 100
// y creo que la forma que lo he hecho es bastante correcta.
class  Hilo3 implements  Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (Math.random()*1>0.5) {
                System.out.println("En el check " + (i + 1) + " del sensor de las plantas,  ¿la planta esta bien? es : mala" );
            }
            else {
                System.out.println("En el check " + (i + 1) + " del sensor de las plantas,  ¿la planta esta bien? es :buena");

            }
        }
        System.out.println("Sensor del estado de las plantas ha completado sus 10 ciclos de lectura.");
    }
}
