package barcerveza;

/**
 * Clase principal que ejecuta la simulación del bar.
 */
public class Main {
    public static void main(String[] args) {
        // Creamos al camarero Mou
        Camarero mou = new Camarero("Mou");

        // Creamos los clientes
        HilosClientes homer = new HilosClientes("Homer", mou);
        HilosClientes barney = new HilosClientes("Barney", mou);
        HilosClientes carl = new HilosClientes("Carl", mou);
        HilosClientes lenny = new HilosClientes("Lenny", mou);
        HilosClientes lurleen = new HilosClientes("Lurleen", mou);

        // Iniciamos los hilos (clientes empiezan a beber)
        homer.start();
        barney.start();
        carl.start();
        lenny.start();
        lurleen.start();
    }
}
