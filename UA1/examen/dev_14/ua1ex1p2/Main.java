import java.util.Random;

public class Main {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {


            hiloTemperatura hilo1 = new hiloTemperatura();
            hiloHumedad hilo2 = new hiloHumedad();
            hiloEstado hilo3 = new hiloEstado();

            hilo1.start();
            hilo2.start();
            hilo3.start();

        }
    }
}
