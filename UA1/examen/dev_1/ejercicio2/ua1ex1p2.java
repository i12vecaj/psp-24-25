import java.util.Random;

public class solucionExamen2 {

    //variable universal para los ciclos
    private static final int ciclos = 10;

    // se agrupan los 3 sensores
    static class Sensor implements Runnable {
        private final Random random = new Random();

        @Override
        public void run() {
            for (int i = 0; i < ciclos; i++) { //bucle que itera la variable ciclos por hasta 10. se implementa en todos los sensores

                int temperatura = random.nextInt(41); //generacion de numero aleatorio para la temperatura entre 0 y 40
                System.out.println("Temperatura: " + temperatura + "°C - Tiempo: " + System.currentTimeMillis());

                int humedad = random.nextInt(101); //simulacion de humedad, porcentajes de 1 a 100%
                System.out.println("Humedad: " + humedad + "% - Tiempo: " + System.currentTimeMillis());

                int estado = random.nextInt(3); //simulacion de estados con case aleatorio con la funcion random
                String estadoPlanta = switch (estado) {
                    case 0 -> "Bueno";
                    case 1 -> "Decente";
                    case 2 -> "Malo";
                    default -> "0";
                };
                System.out.println("Estado de las plantas: " + estadoPlanta + " - Tiempo: " + System.currentTimeMillis());

                try {
                    Thread.sleep(random.nextInt(2000) + 1000); //duerme entre 1 y 3 segundos
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); //errores de interrupcion
                }
            }
        }
    }

    public static void main(String[] args) {
        //instanciamiento del monitor de sensores
        Sensor sensor = new Sensor();

        //instancia del hilo que implementa runnable
        Thread thread = new Thread(sensor);

        //inicio del hilo con la funcion .start()
        thread.start();

        //.join espera hasta que el hilo termina para comenzar otro
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("La ejecución de los sensores fue interrumpida"); //errores
        }

        System.out.println("Informacion de los sensores registrada con éxito");
    }
}
