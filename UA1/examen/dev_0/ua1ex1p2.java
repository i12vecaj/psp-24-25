public class Main {
    public static void main(String[] args) {
        System.out.println("Sensores");
        Thread hilo1 = new Thread(new Hilo1());
        Thread hilo2 =  new Thread(new Hilo2());
        Thread hilo3 =  new Thread(new Hilo3());
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }


}

class Hilo1 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("En el check "+(i+1)+" del sensor 1, la temperatura es de  :"+Math.random()* 41);
        }
    }
}

class Hilo2 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("En el check "+(i+1)+" del sensor 2, el porcentage de humedad  es de  :"+Math.random()*101+"%");
        }
    }
}

class  Hilo3 implements  Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (Math.random()*1>0.5) {
                System.out.println("En el check " + (i + 1) + " del sensor 3,  ¿la planta esta bien? es : mala" );
            }
            else {
                System.out.println("En el check " + (i + 1) + " del sensor 3,  ¿la planta esta bien? es :buena" );

            }
        }
    }
}