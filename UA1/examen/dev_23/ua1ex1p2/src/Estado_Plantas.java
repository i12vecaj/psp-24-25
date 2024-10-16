import java.util.Random;
public class Estado_Plantas extends Thread{
    @Override
    public void run(){
        String[] estados_plantas = {"Buen estado", "Necesita regarse","Mal estado"};
        Random random = new Random();
        int estado_aleatorio;
        for(int i = 0; i <10; i++){
            long tiempo = System.currentTimeMillis();
            estado_aleatorio = random.nextInt(estados_plantas.length);//generamos un indice aleatorio del vector de estados
            System.out.println("Sensor3: Estado de las plantas: "+estados_plantas[estado_aleatorio]+".\nTiempo de ejecucion: "+ tiempo+"ms");
            try{
                Thread.sleep(random.nextInt((3000-1000)+1)+1000);//dormimos el hilo
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
