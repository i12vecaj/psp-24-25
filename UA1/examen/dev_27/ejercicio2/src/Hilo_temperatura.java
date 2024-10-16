public class Hilo_temperatura implements Runnable{
    @Override
    public void run(){
        int i=0;
        while (i!=10){
            i++;
            long startTime = System.currentTimeMillis();
        // Producir un numero aleatorio entre 0 y 99
            int temperatura_aleatorio = (int) (Math.random() * 98) + 1;
        System.out.println("La temperatura es de "+temperatura_aleatorio);
            long endTime = System.currentTimeMillis() -startTime;
            System.out.println("El tiempo que ha tardado en darnos este dato es de:"+endTime+"s");
            try {
                int tiempo_dormir = (int) (Math.random()*2)+1;
                //Ponemos a "Dormir" el programa durante los ms que queremos
                Thread.sleep(tiempo_dormir*1000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
