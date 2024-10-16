import java.util.Random;
public class Humedad extends Thread{
    @Override
    public void run(){
        int humedad_aleatoria;
        Random random = new Random();
        for(int i = 0; i <10; i++){
            long tiempo = System.currentTimeMillis();
            humedad_aleatoria= random.nextInt(100+1);//generamos humedad aleatoria
            System.out.println("Sensor 2: Humedad: "+humedad_aleatoria+"º\n tiempo de ejecución: "+tiempo+"ms");
            try{
                Thread.sleep(random.nextInt((3000-1000)+1)+1000);//dormimos el hilo
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
