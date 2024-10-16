public class Main {
    public static void main(String[] args) {
        //Creamos los hilos
        Temperatura temperatura = new Temperatura();
        Humedad humedad = new Humedad();
        Estado_Plantas estado_plantas = new Estado_Plantas();

        //iniciamos los hilos
        temperatura.start();
        humedad.start();
        estado_plantas.start();
    }
}