// Importamos la clase Scanner para leer la entrada del usuario desde la consola
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Definimos el puerto base para los miembros del anillo
        int puertoInicio = 10000;

        //Creamos tres instancias de MiembroToken representando los nodos de la red token ring
        //El primer miebro sera el que tenga el token ring los demas no
        MiembroToken miembro1 = new MiembroToken(puertoInicio, puertoInicio + 1, 1, true);
        MiembroToken miembro2 = new MiembroToken(puertoInicio + 1, puertoInicio + 2, 2, false);
        MiembroToken miembro3 = new MiembroToken(puertoInicio + 2, puertoInicio, 3, false);

        // Creamos hilos para cada miembro, de modo que puedan funcionar en paralelo
        Thread hilo1 = new Thread(miembro1::iniciar);
        Thread hilo2 = new Thread(miembro2::iniciar);
        Thread hilo3 = new Thread(miembro3::iniciar);

        // Iniciamos los hilos para comenzar la red token ring
        hilo1.start();
        hilo2.start();
        hilo3.start();

        //Creamos un escaner para leer la entrada del usuario y permitir detener la red
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escribe '' para detener la red TOKEN RING."); //Instruccion para detener el programa

        //Bucle que espera a que el usuario introduzca una entrada vacía para detener la red
        while (true) {
            String comando = scanner.nextLine();  //Leemos la línea que escribe el usuario
            if (comando.equalsIgnoreCase("")) { //Si el usuario presiona 'Enter' sin escribir nada:
                miembro1.detener(); //Detenemos el primer miembro
                miembro2.detener(); //Detenemos el segundo miembro
                miembro3.detener(); //Detenemos el tercer miembro
                break; //Y salimos del bucle
            }
        }

        //Esperamos a que los hilos terminen su ejecución antes de finalizar el programa
        try {
            hilo1.join();  //Espera a que el hilo1 termine
            hilo2.join();  //Espera a que el hilo2 termine
            hilo3.join();  //Espera a que el hilo3 termine
        } catch (InterruptedException e) {
            e.printStackTrace();  //En caso de error, muestramos la traza de la excepción
        }

        //Mensaje final indicando que la red se ha detenido correctamente
        System.out.println("Red TOKEN RING detenida.");
    }
}

