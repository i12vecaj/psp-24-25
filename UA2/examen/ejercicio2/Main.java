//con esta clase lo que hacemos es que el codigo compile  y tambien que los hilos funcionen
public class Main  extends Thread{
    public static void main(String[] args) {
        Clasebuffer buffer = new Clasebuffer(10);

        Claseproducctor productor = new Claseproducctor(buffer);
        Claseconsumidor consumidor = new Claseconsumidor(buffer);

        Thread hiloProductor = new Thread( productor);
        Thread hiloConsumidor = new Thread( consumidor);

        hiloProductor.start();
        hiloConsumidor.start();


            try {
                hiloConsumidor.join();
                hiloProductor.join();

            }catch (Exception e){
                System.out.println("este problema no esta dejando continuar el codigo"+e);
            }

    }
}