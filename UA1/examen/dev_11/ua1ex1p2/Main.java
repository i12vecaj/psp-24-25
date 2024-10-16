//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main { // Main - Álvaro Fernández Amaro

    public static void main(String[] args) throws InterruptedException {

        //Instancio los objetos de cada clase de cada hilo.
        HiloTemperatura temperatura = new HiloTemperatura();
        HiloHumedad humedad = new HiloHumedad();
        HiloPlantas plantas = new HiloPlantas();

        //Creo un bucle para iterar las mediciones.
        for (int i = 1; i <= 10; i++) {

            //Instancio objetos de la clase thread, con mis objetos hilo como parametro, para usar metodos como start.
            Thread h1 = new Thread(temperatura);
            Thread h2 = new Thread(humedad);
            Thread h3 = new Thread(plantas);

            System.out.println("Medición " + i);

            //Inicializo los hilos.
            h1.start();
            h2.start();
            h3.start();

            //Manejo de errores para que por cada medición, los hilos duerman durante 2s y si se interrumpe, lo muestre.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("El hilo plantas ha sido interrumpido.");
            }

            System.out.println("\n");
            System.exit(1); //Declaro un system.exit como forma de decir que si da 1, el programa finalizó correctamente.
        }



        System.out.println("\nEl programa ha finalizado");
    }
}