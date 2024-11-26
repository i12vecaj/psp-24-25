public class Main {
    public static void main(String[] args) {

    Temperatura hilotemp = new Temperatura();
    Humedad hilohume = new Humedad();
    Estado hiloestado = new Estado();

    hilotemp.start();//Iniciamos el hilo de temperatura
    hilohume.start();//Iniciamos el hilo de humedad
    hiloestado.start();//Iniciamos el hilo de estado


    }
}

