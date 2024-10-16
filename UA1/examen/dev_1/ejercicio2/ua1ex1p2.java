//daniel acosta castilla


import java.util.Random;

public class ExamenJava {

    //variable universal para los ciclos

    private static final int ciclos = 10;

    static class Temperatura extends Thread {
        private final Random random = new Random();

        @Override // si no se hace uso de override, en caso de llamar al hilo no ocurrirá nada

        public void run() {
            for (int i = 0; i < ciclos; i++) { //bucle que itera la variable ciclos por hasta 10. se implementa en todos los sensores
                int temperatura = random.nextInt(41); //generacion de numero aleatorio para la temperatura entre 0 y 40
                System.out.println("Temperatura: " + temperatura + "°C - Tiempo: " + System.currentTimeMillis());
                try {
                    Thread.sleep(random.nextInt(2000) + 1000); //duerme entre 1 y 3 segundos
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); //errores de interrupcion
                }
            }
        }
    }

    //sensor de la humedad en el suelo
    static class Humedad extends Thread {
        private final Random random = new Random();

        @Override
        public void run() {
            for (int i = 0; i < ciclos; i++) {
                int humedad = random.nextInt(101); //simulacion de humedad, porcentajes de 1 a 100%
                System.out.println("Humedad: " + humedad + "% - Tiempo: " + System.currentTimeMillis());
                try {
                    Thread.sleep(random.nextInt(2000) + 1000); //duerme
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // control de errores en caso de interrupcion
                }
            }
        }
    }

    //hilo estado de las plantas
    static class Plantas extends Thread {
        private final Random random = new Random();

        @Override
        public void run() {
            for (int i = 0; i < ciclos; i++) {
                int estado = random.nextInt(3); //simulacion de estados con case aleatorio con la funcion random
                String estadoPlanta = switch (estado) {
                    case 0 -> "Bueno";
                    case 1 -> "Decente";
                    case 2 -> "Malo";
                    default -> "0";
                };
                System.out.println("Estado de las plantas: " + estadoPlanta + " - Tiempo: " + System.currentTimeMillis());
                try {
                    Thread.sleep(random.nextInt(2000) + 1000); //duerme
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); //errores
                }
            }
        }
    }

    public static void main(String[] args) {
        //instanciamiento de los sensores
        Temperatura temperatura = new Temperatura();
        Humedad humedad = new Humedad();
        Plantas estado = new Plantas();

        //inicio de los hilos con la funcion .start()
        temperatura.start();
        humedad.start();
        estado.start();

        //.join espera hasta que uno de los hilos termina para comenzar otro
        try {
            temperatura.join();
            humedad.join();
            estado.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); //errores
        }

        System.out.println("Informacion de los sensores registrada con éxito");
    }
}

