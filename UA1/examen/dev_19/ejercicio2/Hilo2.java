import java.util.Random;

public class Hilo2 implements Runnable {
    public void run() {
        //generacion y asignacion de un numero random
        //para que la humedad cambie cada 1/3 segundos
        Random r = new Random();
        int aleatorio = r.nextInt(3);
        System.out.println("la humedad ha sido medida a las : " + (System.currentTimeMillis())/1000+"segundos");
        try//para el uso del sleep()
        {
            Thread.sleep(1000*aleatorio);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
