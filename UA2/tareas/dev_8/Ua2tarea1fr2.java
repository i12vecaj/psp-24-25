import java.util.Random;

public class Ua2tarea1fr2 extends Thread{
    private String nombre;

    public Ua2tarea1fr2(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        Random random = new Random();
        int tiempo = 5000 + random.nextInt(8000);


        System.out.println("La atracción '" + nombre + "' comienza, tiempo: " + (tiempo / 1000) + " segundos.");

        try {
            System.out.println("Hilo en ejecucion " + Thread.currentThread().getName()); // control de errores
            Thread.sleep(tiempo);//aqui hago que espere el tiempo que muestra por pantalla
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("La atracción '" + nombre + "' ha terminado.");
    }
}
