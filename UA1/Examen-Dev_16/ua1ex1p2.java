/*Examen hecho por Alberto Mármol Bello a día 15 de Ocutbre para la asignatura de Programación de servicios y procesos.*/

//Añadimos las librerias útlies para nuestros sensores.
import java.util.Random;

//Esta clase representa un sensor y implementa la interfaz Runnable para permitir su ejecución en un hilo.
class Sensor implements Runnable {
    private String tipo; //Tipo de sensor (ejemplo: temperatura, humedad).
    private int ciclos; //Número de ciclos de medición.
    private Random random; //Con esto generamos números aleatorios.

    //Hacemos un constructor de la clase Sensor que inicializa los atributos tipo, ciclos y el generador de números aleatorios.
    public Sensor(String tipo, int ciclos) {
        this.tipo = tipo;
        this.ciclos = ciclos;
        this.random = new Random(); //Se inicia el generador de números aleatorios.
    }

    //Tengo un método que se ejecuta al iniciar el hilo, este realiza las mediciones del sensor.
    @Override
    public void run() {
        for(int i = 0;i < ciclos; i++){
            //Genero un valor aleatorio.
            int valor = generarValor(); //Genera una valor que mide aleatoriamente.

            //Obtengo el tiempo actual.
            long tiempoActual = System.currentTimeMillis(); //Con esto obtengo la marca de tiempo actual.

            //Ahora voy a imprimir el valor y marcar del tiempo.
            System.out.println(tipo + "Medición: " + valor + "(Timestamp: " + tiempoActual + ")");

            //Dormir durante un tiempo aleatorio entre 1 y 3 segundos.
            try{
                int tiempoEspera = random.nextInt(3000) + 1000; //Se genera un tiempo de espera de entre 1000 y 3000 ms.
                Thread.sleep(tiempoEspera); //El hilo se detiene por el tiempo generado.
            } catch (InterruptedException e) {
                //Si el hilo es interrumpido, se restaura el estado de interrupción.
                Thread.currentThread().interrupt();
                break; //Sale del bucle si se interrumpe el hilo.
            }
        }
    }

    //Creo otro método que genera un valor aleatorio para la medición del sensor.
    private int generarValor() {
        //Generar un valor aleatorio entre 0 y 100.
        return random.nextInt(101); //Retorna un valor entre 0 y 100.
    }
}

//Creo la clase principal que simula el monitoreo de varios sensores (la he llamado con el nomvre del examen para que no de problemas al compilar).
public class ua1ex1p2 {
    public static void main(String[] args) {
        int ciclos = 10; //Número de ciclos de medición para cada sensor.

        //Creo los hilos para cada tipo de sensor.
        Thread sensorTemperatura = new Thread(new Sensor("Sensor de Temperatura", ciclos));
        Thread sensorHumedad = new Thread(new Sensor("Sensor de Humedad", ciclos));
        Thread sensorEstadoPlantas = new Thread(new Sensor("Sensor de Estado de Plantas", ciclos));
        
        //Justo debajo digo que se inicien los hilos.
        sensorTemperatura.start(); //Aquí se inicia el hilo del sensor de temperatura.
        sensorHumedad.start(); //Aquí se inicia el hilo del sensor de humedad.
        sensorEstadoPlantas.start(); //Y por últmo aquí se inicia el hilo del sensor de estado de plantas.

        //Seguramente tengamos que esperar a que terminen los hilos.
        try {
            sensorTemperatura.join(); //Espera a que termine el hilo del sensor de temperatura.
            sensorHumedad.join(); //Espera a que termine el hilo del sensor de humedad.
            sensorEstadoPlantas.join(); //Espera a que termine el hilo del sensor de estado de plantas.
        } catch (InterruptedException e) {
            e.printStackTrace(); //Pongo esto aquí por si hay algun problema con algun hilo.
        }
        System.out.println("Monitoreo de sensores finalizado."); //Imprimo el mensaje que indica que el monitoreo ha terminado :).
    }   
}