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