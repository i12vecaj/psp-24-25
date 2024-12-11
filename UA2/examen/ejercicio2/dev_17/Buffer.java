import java.util.ArrayList;

public class Buffer {
    private ArrayList<Character> buffer = new ArrayList<>(); //Declaro la arraylist que va a contener las letras
    private int capacidad; //La variable capacidad para mostrar la capacidad del la arraylist

    public Buffer() {//Constructor vacio
    }

    public synchronized void producir(char letra) { //Declaramos el metodo producir con syncronized para sincronizar los hilos
        if (buffer.isEmpty()) { //Si el buffer esta vacio
            for (int i = 1; i < 11; i++) { //Hacemos un bucle de 10 para introducir 10 caracteres
                buffer.add(letra); //Añadimos la variable letra del hilo productoe
                System.out.println("Producimos la letra numero "+i+": " + letra); //Y mostramois la letra
            capacidad = buffer.size(); //Y asignamos el tamaño del buffer a la variable capacidad
            System.out.println("La capacidad del buffer es de: " + capacidad);//Mostramos como la capacidad del buffer va aumentando
            }
            System.out.println("\n");//Espacio

        } else {
            System.out.println("El buffer esta lleno espere un momento");//Si el bbuffer esta lleno mostraremos esto
        }
    }

    public synchronized void consumir() { //Declaramos el metodo producir con syncronized para sincronizar los hilos
        if (!buffer.isEmpty()) { //Si el buffer no esta vacio entramos en el bucle
            for (int i = 1; i < 11; i++) {
                System.out.println("Consumimos la letra numero "+i+": " + buffer.remove(0)); //Vamos consumiendo cada letra
            capacidad = buffer.size(); //Y asignamos de nuevo el tamaño del buffer a capacidad
            System.out.println("La capacidad del buffer es de: " + capacidad);//Y lo mostramos
            }

        } else {
            System.out.println("El buffer esta vacio");//Si el bufer esta vacio mostraremos esto
        }
    }
}
