import java.util.Random;

public class SensorSimulator {
    static class SensorThread extends Thread { // esta clase la hecho para simular el sensor
        private String sensorName;
        private Random random;

        
        public SensorThread(String sensorName) {  //aqui hemos hecho el contructor para el sensor 
            this.sensorName = sensorName;
            this.random = new Random();
        }

       
        private void simuladordesensor() { // esta funcion la hago para generar un valor aleatorio
            //el valor sera entre 0 y 100
            int sensorValue = random.nextInt(101);  
            long timestamp = System.currentTimeMillis();
            System.out.println(sensorName + " - Lectura: " + sensorValue + " - Timestamp: " + timestamp);
        }

        
        @Override //aqui hacemos la ejecucion del hilo.
        public void run() {
            for (int i = 0; i < 10; i++) {
                simuladordesensor();
                try {
                    Thread.sleep(1000);  // Pausa de 1 segundo
                } catch (InterruptedException e) {
                    System.err.println(sensorName + " - tenemos un erro en el hilo");
                }
            }
        }
    }

    public static void main(String[] args) {
        
        Thread sensortemperatura = new sensorThread("medimos con el sensor la temperatura"); // creamos el hilo para cada sensor que hecho
        Thread sensorhumedad = new sensorThread("medimos el sensor para la humedad del suelo");
        Thread sensorplatas = new sensorThread("sensor para la plantas");

        sensortemperatura.start();
        sensorHumedad.start();
        sensorplatas.start();
    }
}
