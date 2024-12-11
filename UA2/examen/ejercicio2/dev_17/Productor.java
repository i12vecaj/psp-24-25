import java.util.Random;

public class Productor extends Thread{
    private Buffer buffer; //Creo un buffer de la clase Buffer

    public Productor(Buffer buffer) { //Lo declaro en el constructor
        this.buffer = buffer;
    }

    @Override
    public void run(){ //Creo el metodo run
        char letra = (char) (Math.random() * 26 + 'A'); //Pongo la letra de forma aleatoria

        Random numeroRandom = new Random(); //Creo una variable Random

        try {//Creo un try catch
            buffer.producir(letra);//Uso el metodo producir de la clase buffer
            Thread.sleep(numeroRandom.nextInt(200,500));//Duermo el hilo un tiempo especifico
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
