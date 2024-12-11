import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class ua2ex2 {
    public static void main(String[] args) throws InterruptedException {

        Buffer buffer = new Buffer(10);

        Consumidor consumidor = new Consumidor(buffer);
        Productor productor = new Productor(buffer);
        Consumidor consumidor1 = new Consumidor(buffer);
        Productor productor1 = new Productor(buffer);


        productor.start();
        consumidor.start();
        productor1.start();
        consumidor1.start();


    }
}
class Consumidor extends Thread{
    Buffer buffer;

    Consumidor(Buffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run(){
        try {
            System.out.println("Se ha consumido el caracter "+buffer.consumir());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            espera();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void espera() throws InterruptedException {
        Random random = new Random();
        int tiempo = random.nextInt(400) + 300;
        Thread.sleep(tiempo);
    }
}

class Productor extends Thread{
    Buffer buffer;

    Productor(Buffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run(){
        char c = generarCaracter();
        System.out.println("Se ha generado el caracter "+generarCaracter());
        try {
            buffer.producir(c);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            espera();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public char generarCaracter(){
        return (char)(Math.random() * 26 + 'A');
    }
    public void espera() throws InterruptedException {
        Random random = new Random();
        int tiempo = random.nextInt(300) + 200;
        Thread.sleep(tiempo);
    }
}

class Buffer{
    private int capacidad;
    ArrayList<Character> listaCaracteres = new ArrayList<>();

    Buffer(int capacidad){
        this.capacidad = capacidad;
    }

    public synchronized void producir(char c) throws InterruptedException {
        if(capacidad > listaCaracteres.size()){
            listaCaracteres.add(c);
        }else {
            while (capacidad == listaCaracteres.size()){
                wait();
            }
        }
        notify();
    }

    public synchronized char consumir() throws InterruptedException {
        char c = 'c';
        if (listaCaracteres.isEmpty()){
            while (listaCaracteres.isEmpty()){
                wait();
            }
        }else {
            Random random = new Random();
            int posicion = random.nextInt(listaCaracteres.size());
            c = listaCaracteres.get(posicion);
            listaCaracteres.remove(posicion);
        }
        notify();
        return c;
    }
 }