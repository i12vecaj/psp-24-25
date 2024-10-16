package Ejercicio2;

public class EstadoPlantas extends Thread{
    @Override
    public void run() {
        int contador=0;

        int n=100;

        for (int i=0;i<10;i++){
            //genera un numero random para la temperatura
            int estado = (int) (Math.random() * n) + 1;
            System.out.println("El estado actual de las plantas es del: "+estado+"%.");
            System.out.println("Lectura realizada por el sensor: " + System.currentTimeMillis());
            System.out.println("");
        }
    }
}
