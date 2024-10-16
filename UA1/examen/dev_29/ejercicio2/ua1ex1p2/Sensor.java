package ua1ex1p2;
/*
Clase base abstracta que define lo basico de un sensor
 */
public abstract class Sensor implements Runnable{
    private String nombre = "sin etiquetar";//Nombre del sensor
    private Long duracion = (long) 1000.0;//Intervalo en milisegundos de la lectura del sensor
    private int nLecutras = 10;//Numero de lecturas maximas
    public Sensor(String nombre, Long duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
    }

    //Trabajo del sensor, cada x milisegundos durante x iteraciones leemos del sensor
    @Override
    public void run() {
        try {
           for(int i=0; i<nLecutras; i++) {
                System.out.println(impresionSensor(lecturaSensor()));
                Thread.sleep(duracion);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Funcion que ejecuta la lectura del sensor
    //No recibe nada
    //Devuelve la lectura del sensor
    private double lecturaSensor(){
        return Math.random();
    }
    //Funcion que ejecuta la impresion del valor del sensor
    //Recibe la lectura
    //Devuelve la lectura por pantalla del sensor
    private String impresionSensor(Double lectura){
        return this.nombre + ":" + lectura + " Hora:" + System.currentTimeMillis() + ".";
    }
}
