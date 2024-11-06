public class ua2tarea1fr1 extends Thread {
    
    //Creo una variable estática que está compartida con todos los hilos.
    static int contador = 0;

    @Override
    public void run() {

        //Los hilos incrementan el contador en 1000 unidades.
        for (int i = 0; i < 1000; i++) {
            contador++;
        }
    }

    public static void main(String[] args) {
        
        //Uso un array para guardar las instacias de los hilos.
        ua2tarea1fr1[] hilos = new ua2tarea1fr1[5];

        //Creo y lanzo los 5 hilos.
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new ua2tarea1fr1();
            hilos[i].start();
        }

        //Ahora esperamos a que todos los hilos terminen.
        for (ua2tarea1fr1 hilo : hilos) {
            try {
                hilo.join(); //El "join" se saegura de que el hilo principal espere a los hilos hijos.
            } catch (InterruptedException e) {
                System.err.println("Error al unir el hilo: " + e.getMessage());
            }
        }

        //Para terminar este código muestro el valor final del contador.
        System.out.println("Valor final del contador (sin sincronización): " + contador);
    }
}