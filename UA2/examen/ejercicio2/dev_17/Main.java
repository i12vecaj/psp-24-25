public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(); //Creamos el objeto buffer de la clase Buffer
        //Implementamos los hilos
        Thread hiloProductor = new Thread(new Productor(buffer));
        Thread hiloConsumidor = new Thread(new Consumidor(buffer));

        hiloProductor.start();//Inicio el primer hilo que va a añadir letras

        try{
            hiloProductor.join();//Hago que finalice su tarea por que no he podido sincronizarlo adecuadamente

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        hiloConsumidor.start();//Inicio el primer hilo que va a añadir letras
        try{
            hiloConsumidor.join();//Hago que finalice su tarea por que no he podido sincronizarlo adecuadamente

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Los hilos han finalizado.");//Mensaje final
    }
}