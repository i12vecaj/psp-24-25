
public class ua2tarea1fr1 implements Runnable {
    private static int contador = 0;

    public String nombre;
    public ua2tarea1fr1 (String nombre) {
        this.nombre = nombre;
    } //etiqueta nombre

    @Override
    public void run() {
        for (int contador = 0; contador < 1000; contador++) { //contador de 0 a 1000
            System.out.println("" + nombre + "ha llegado al inicio.");
        }
    }

    public static void main (String[] args) {

        //crear objetos e inicializarlos

        ua2tarea1fr1 atraccion1 = new ua2tarea1fr1("Casa del terror");
        ua2tarea1fr1 atraccion2 = new ua2tarea1fr1("Rio lento");
        ua2tarea1fr1 atraccion3 = new ua2tarea1fr1("El raton vacilon");
        ua2tarea1fr1 atraccion4 = new ua2tarea1fr1("Alcatraz");
        ua2tarea1fr1 atraccion5 = new ua2tarea1fr1("XXL mortal");

        atraccion1.start();
        atraccion2.start();
        atraccion3.start();
        atraccion4.start();
        atraccion5.start();

        try {
            atraccion1.join();
            atraccion2.join();
            atraccion3.join();
            atraccion4.join();
            atraccion5.join();



        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Contador: " + contador);

    }

}
