/**
Feedback JD: 12/12/2024

Rubén no está correcta la solución: el modelo productor / consumidor no está del todo bien representado en tu código. Además, hay problemas sintácticos, más algunas mejoras que te comento:

Problemas de compilación:

    El método Producir utiliza buffer.get(i), pero este puede lanzar IndexOutOfBoundsException cuando i es mayor que el tamaño actual de la lista.
    El código en Consumidor tiene errores al intentar mostrar caracteres desde el buffer sin un manejo adecuado de índices válidos.

Lógica del buffer:

    El constructor de Buffer ignora el parámetro Capacidad y tiene inicializaciones incorrectas (como this.Capacidad=10).
    No se respeta el tamaño máximo del buffer en las operaciones de añadir y eliminar elementos.
    Los índices (i y o) no se reinician correctamente tras alcanzar su límite, lo que puede causar comportamientos erráticos.

Sincronización ausente:

    No se han implementado mecanismos de sincronización (synchronized, wait, notifyAll), lo cual es crítico para evitar condiciones de carrera en un entorno multihilo.

Lógica incompleta en Consumidor:

    El consumidor no elimina elementos del buffer; solo intenta imprimirlos, lo que contradice su propósito.

Errores en el productor:

    El método Producir no genera caracteres aleatorios correctamente ((Math.random() * 26 + "A") no produce un carácter, sino una concatenación inválida).

**/


import java.util.ArrayList;
import java.util.Random;

public class ua2ex2 {
    public static void main(String[] args) {

    }
}
class Buffer {
    public ArrayList<String> buffer;
    int Capacidad;
    int indiceEscritura;
    int indiceLectura;
    int i;
    int o;

    public Buffer(ArrayList<String> buffer, int Capacidad, int indiceEscritura, int indiceLectura) {
        this.buffer = buffer;
        this.Capacidad=10;
        this.indiceEscritura=indiceEscritura;
        this.indiceLectura=indiceLectura;
    }
    public void Producir(String c) {
        try {
            while (i != 10) {
                buffer.get(i);
                if (buffer.get(i).isEmpty()) {
                    buffer.add(c);
                    System.out.println("Caracter añadido con exito, en la posicion " + i);
                    i = 10;
                }
                i++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Consumir() {
        try {
            while (o != 10) {
                buffer.get(o);
                if (!buffer.get(o).isEmpty()) {
                    buffer.remove(o);
                    System.out.println("Caracter eliminado con exito, de la posicion" + o);
                    o = 10;
                }
                o++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
class Productor implements Runnable{
        Buffer buffer;

        public Productor(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            try{
                Random random = new Random();
                Thread.sleep(random.nextInt(1));
                String caracter_aleatorio=(Math.random()*26+"A");
                buffer.Producir(caracter_aleatorio);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
}
class Consumidor implements Runnable{
        Buffer buffer;
        int posicion_buffer;

        public Consumidor(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {

            try{
                Random random = new Random();
                Thread.sleep(random.nextInt(1));
                String mostrar_caracter = buffer.buffer.get(posicion_buffer);
                System.out.println("La posicion "+posicion_buffer+"tiene el caracter"+mostrar_caracter);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
}
