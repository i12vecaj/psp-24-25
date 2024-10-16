public class Main {
    public static void main(String[] args) throws InterruptedException {

        HiloHumedad humedad = new HiloHumedad(); //Creamos nuestras clases que implementa runnable
        HiloTemperatura temperatura = new HiloTemperatura();
        HiloEstadoSuelo estadoSuelo = new HiloEstadoSuelo();
        try{
            for (int i = 0; i < 10; i++) {
                Thread hiloTemperatura = new Thread(temperatura); //Creamos los hilos, asignandole las clases que previamente hemos inicializado
                Thread hiloHumedad = new Thread(humedad);
                Thread hiloEstadoSuelo = new Thread(estadoSuelo);

                hiloTemperatura.start();//Iniciamos el hilo
                hiloTemperatura.join();//Esperamos a que el hilo termine para que no se solape la información
                hiloHumedad.start();
                hiloHumedad.join();
                hiloEstadoSuelo.start();
                hiloEstadoSuelo.join();

            }
        }catch (Exception e){
            System.out.println("Error: " + e);
        }



    }
}