import java.util.Random;

public class ua2tarea1fr2runnable {
    public static void main (String[] args) {

        //crear objetos

        Atraccion atraccion1 = new Atraccion("Casa del terror");
        Atraccion atraccion2 = new Atraccion("Rio lento");
        Atraccion atraccion3 = new Atraccion("El raton vacilon");
        Atraccion atraccion4 = new Atraccion("Alcatraz");
        Atraccion atraccion5 = new Atraccion("XXL mortal");

        //crear hilos

        Thread hilo1 = new Thread(atraccion1);
        Thread hilo2 = new Thread(atraccion2);
        Thread hilo3 = new Thread(atraccion3);
        Thread hilo4 = new Thread(atraccion4);
        Thread hilo5 = new Thread(atraccion5);

        //inicializar hilos

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    class Atraccion implements Runnable {
        private String nombre;


        public Atraccion(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public void run() {
            Random numAleatorio = new Random();
            int duracion = 5000 + numAleatorio.nextInt(8000);

            System.out.println("" + nombre + "esta comenzando. Tiene una duracion de: " + (duracion / 1000) + " segundos.");

            try {
                System.out.println("" + Thread.currentThread().getName()); //controlar errores
                Thread.sleep(duracion); //Tiempo de espera

            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("La atraccion " + nombre + "ha terminado");
        }
    }



}
