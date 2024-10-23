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
//Comparando mi codigo con el proporcionado por el profesor, no eh sido capaz de encontrar ninguna mejorla la cual hacer a micodigo
//puesto a que mientras el del profesor se trara de 1 sola clase, donde se crean los hilos y se les asigna valores random
//el mio esta compuesto por 4 clases, 1 para el ejecutor de los sensres (main) y el resto para los hilos, de esta manera puedo
//manejar los valores que asignare a estos hilos, como por ejemplo al de temperatura, teniendo en cuenta que es raro que en
//mi ciudad la temperatura este por encima de los 50ºC.