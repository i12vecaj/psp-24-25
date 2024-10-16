public class sensorMonitorizacion {
        public static void main(String[] args) {

            // Crearemos los hilos para cada sensor:
            Thread STemperatura = new Thread(new STemperatura());
            Thread SHumedad = new Thread(new SHumedad());
            Thread SPlantasEstado = new Thread(new SPlantasEstado());

            // Iniciamos los hilos:
            STemperatura.start();
            SHumedad.start();
            SPlantasEstado.start();

            // Esperar a que todos los hilos terminen
            try {
                STemperatura.join();
                SHumedad.join();
                SPlantasEstado.join();
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("La sesion de monitoreo ha terminado.");
        }
    }
