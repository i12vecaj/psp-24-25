package Ejercicio2;

public class Temperatura extends Thread {
    @Override
    public void run() {
        int n=50;
        for(int i=0;i<10;i++){

            int grados = (int) (Math.random() * n) + 1;
            System.out.println("La temperatura que hace ahora mismo es: "+grados+" grados Celsius");
            System.out.println("Lectura realizada por el sensor: " + System.currentTimeMillis());
            System.out.println("");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("La lectura de la temepratura ha fallado");
            }


        }



    }
}
