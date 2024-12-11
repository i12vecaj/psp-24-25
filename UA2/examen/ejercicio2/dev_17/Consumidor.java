import java.util.Random;

public class Consumidor extends Thread{
    private Buffer buffer; //Creo un buffer de la clase Buffer

    public Consumidor(Buffer buffer){//Lo declaro en el constructor
        this.buffer = buffer;
    }

    @Override
    public void run() {//Creo el metodo run

            Random numeroRandom = new Random();//Creo una variable Random

            try{
                buffer.consumir(); //Uso el metodo consumir de la clase Buffer
                Thread.sleep(numeroRandom.nextInt(300,700));//Duermo el hilo un tiempo especifico
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }
}
