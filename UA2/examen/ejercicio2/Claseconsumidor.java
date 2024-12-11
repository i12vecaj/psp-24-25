


//este es el hilo consumidor


import java.util.Random;


public class Claseconsumidor implements Runnable {
    private final Clasebuffer buffer;


    public Claseconsumidor(Clasebuffer buffer) {
        this.buffer = buffer;
    }


    @Override
    public void run() {

        while (true) {
            char c = buffer.consumir();
            System.out.println("Consumiendo caracter: " + c);
          dormir();
        }
    }


//metodo que sirve para dormir el hilo

    public void dormir() {
        Random random = new Random();
        try {
            Thread.sleep(300 + random.nextInt(401));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


