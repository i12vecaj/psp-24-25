public class Incremento extends Thread {
    private static int contador = 0; //variable compartida
    public Incremento() {}

    //metodo ejecutor
    public static void main(String[] args) {
        //crear hilos
        Thread hilo1 = new Incremento();
        Thread hilo2 = new Incremento();
        Thread hilo3 = new Incremento();
        Thread hilo4 = new Incremento();
        Thread hilo5 = new Incremento();

        //iniciar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

        //esperar a que terminen todos los hilos
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
        } catch (InterruptedException e) {
            System.out.println("Error en la espera de los hilos: " + e.getMessage());
        }

        //mostrar el resultado final
        System.out.println("Resultado final sin sincronización: " + contador);
    }

    //metodo de incremento
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            contador++;
        }

       /*en esta version del lanzamiento de los hilos, el programa a veces SÍ que da los resultados esperados 
       por mera estadística, aunque no siempre es así.
     */
    }
}

