import java.util.Random;
public class Temperatura extends Thread{
    @Override
    public void run(){
        Random random = new Random();
        int temperatura_aleatoria;
        for(int i = 0; i <10; i++) {
            long tiempo = System.currentTimeMillis();
            temperatura_aleatoria = random.nextInt(40+1); //generamos temperatura aleatoria
            System.out.println("Sensor 1: Temperatura: "+temperatura_aleatoria+"º\n tiempo de ejecución: "+tiempo+"ms");
            try{
                Thread.sleep(random.nextInt((3000-1000)+1)+1000);//dormimos el hilo
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
