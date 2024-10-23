public class ua1ex1ep2 {
    public static void main(String[] args) {
        System.out.println("Sensores");
        Thread hilo1 = new Thread(new Hilo1());
        Thread hilo2 =  new Thread(new Hilo2());
        Thread hilo3=  new Thread(new Hilo3());
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}

class  Hilo1 implements  Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
                System.out.println("En el check " + (i + 1) + " del sensor de la temperatura,  la temperatura es : "+ Math.random()*41 );
            }
        System.out.println("Sensor del estado de las plantas ha completado sus 10 ciclos de lectura.");
    }
}

class  Hilo2 implements  Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
                System.out.println("En el check " + (i + 1) + " del sensor de las plantas,  la humedad es : "+ Math.random()*101 );
            
        }
        System.out.println("Sensor de la humedad ha completado sus 10 ciclos de lectura.");
    }
}



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