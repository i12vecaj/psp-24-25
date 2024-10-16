import static java.lang.Integer.parseInt;

public class HiloTemperatura implements Runnable{

    @Override //Sobreescribimos el método para indicarle que queremos que haga
    public void run() {
        System.out.println("---Sensor Temperatura---");
        float temperatura = nuevaTemperatura(); //guardamos la temperatura generado
        int duerme = (int) tiempoDuerme(); //Generamos el tiempo que va a dormir
        try { //Intenamos mandar el hilo a dormir
            Thread.sleep(duerme * 1000);
        } catch (InterruptedException e) {
            System.out.println("Error");
        }
        System.out.println("El sensor realizó la lectura en " + System.currentTimeMillis() + " segundos"); //Mostramos cuando se realizó la lectura
        System.out.println("La temperatura actual es: " + temperatura + " grados Celsius"); //Mostramos el valor de la lectura
    }

    public float nuevaTemperatura() { //Generamos la temperatura
        return (float) Math.random() * 100;
    }

    public int tiempoDuerme(){
        return (int) (Math.random() * 3) + 1; //Generamos el tiempo que va a dormir
    }
}
