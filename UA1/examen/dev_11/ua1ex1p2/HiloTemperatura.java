public class HiloTemperatura implements Runnable{ //Implemento la clase Runnable
    String mensajeExito;

    public HiloTemperatura() {
    this.mensajeExito = "";
    }

    @Override //Sobrescribo el metodo run.
    public void run() {
        String mensajeExito = "Temperatura obtenida.";
        double temperatura = Math.random() * 100; //Variable para guardar la temperatura.

        System.out.println("Temperatura actual: " + temperatura + "°C");
        System.out.println(mensajeExito);
        return;
    }

    public String getTemperatura() {
        return mensajeExito;
    }
}
