




//este es el hilo productor
import java.util.Random;

public class Claseproducctor implements Runnable {
    private final Clasebuffer buffer;

    public Claseproducctor(Clasebuffer buffer) {
        this.buffer = buffer;
    }


    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            char c = (char) (random.nextInt(26) + 'A');
            buffer.producir(c);
            dormir();
        }
    }

    //metodo que sirve para dormir el hilo
    public void dormir(){
        Random random = new Random();
        try {
            Thread.sleep(200 + random.nextInt(300));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

