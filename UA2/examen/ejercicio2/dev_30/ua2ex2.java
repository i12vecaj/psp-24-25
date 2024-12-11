import java.util.ArrayList;

public class ua2ex2 {


    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();

        Thread productor = new Thread(new Productor(buffer));
        Thread consumidor = new Thread(new Consumidor(buffer));



            productor.start();
            productor.join();

            consumidor.start();
            consumidor.join();


    }
}

class Buffer{
    private ArrayList<Character> numeros = new ArrayList<Character>();

    public Buffer() {

    }

    public ArrayList<Character> getNumeros() {
        return numeros;
    }


    public synchronized void producir(char c) throws InterruptedException {
        if (numeros.size()==10){
            wait();
        }

        numeros.add(c);
        notifyAll();
    }

    public synchronized void consumir() throws InterruptedException {
        while (numeros.isEmpty()){
            wait();
        }

        System.out.println("El consumidor ha consumido el producto: "+getNumeros());
        notifyAll();
    }

}
class Productor extends Thread{

    public Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {


        try {
            for (int i=0;i<10;i++){
                char numeroAleatorio = (char) (Math.random() * 26 + 'A');
                buffer.producir(numeroAleatorio);
                System.out.println("El productor ha producido un elemento: "+ buffer.getNumeros());
            }


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("El consumidor ha terminado");
    }
}

class Consumidor extends Thread{
    public Buffer buffer;

    public Consumidor(Buffer buffer){
        this.buffer=buffer;

    }

    @Override
    public void run() {

        try {
            for (int i=0;i<10;i++){
                buffer.consumir();
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("El productor ha terminado");
    }
}

