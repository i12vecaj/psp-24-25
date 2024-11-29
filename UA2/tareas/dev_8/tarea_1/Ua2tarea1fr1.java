public class Ua2tarea1fr1 {
    public static void main(String[] args) {
        // creo los objetos
        Ua2tarea1fr2 atraccion1 = new Ua2tarea1fr2("Montaña Rusa");
        Ua2tarea1fr2 atraccion2 = new Ua2tarea1fr2("Casa del Terror");
        Ua2tarea1fr2 atraccion3 = new Ua2tarea1fr2("Río Lento");
        Ua2tarea1fr2 atraccion4 = new Ua2tarea1fr2("DR.Calambre");
        Ua2tarea1fr2 atraccion5 = new Ua2tarea1fr2("CECOT");

        //inicio los hilos
        atraccion1.start();
        atraccion2.start();
        atraccion3.start();
        atraccion4.start();
        atraccion5.start();

        try {// esperamos a que terminen todos los hilos
            atraccion1.join();
            atraccion2.join();
            atraccion3.join();
            atraccion4.join();
            atraccion5.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Todas las Atracciones han terminado AAAAAAYPS");
    }
}
