public class Main {
    public static void main(String[] args) {
        Hilo_temperatura hilo_temperatura = new Hilo_temperatura();
        Hilo_humedad hilo_humedad = new Hilo_humedad();
        Hilo_estado hilo_estado = new Hilo_estado();

        // Thread se usa para crear el hilo
        Thread crear_hilo_temperatura = new Thread(hilo_temperatura);
        Thread crear_hilo_humedad = new Thread(hilo_humedad);
        Thread crear_hilo_estado = new Thread(hilo_estado);

        // Iniciamos el hilo
        crear_hilo_temperatura.start();
        crear_hilo_humedad.start();
        crear_hilo_estado.start();
        System.out.println("Aqui tienes todos tus datos");
    }
    //No me ha salido bien bien, pero mi idea era que saliese 1 por 1, primero temperatura y el tiempo que ha tardado en sacar ese dato, luego humedad y etc
}