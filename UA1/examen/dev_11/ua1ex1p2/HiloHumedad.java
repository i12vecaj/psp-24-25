public class HiloHumedad implements Runnable{ //Implemento la clase Runnable
    String mensajeExito;

    public HiloHumedad() {
        this.mensajeExito = "";
    }

    @Override //Sobrescribo el metodo run.
    public void run() {
        mensajeExito = "Humedad obtenida.";
        double humedad = Math.random() * 100; //Variable donde guardo el porcentaje de humedad.
        System.out.println("Humedad: " + humedad + "%");
        System.out.println(mensajeExito);

        return;
    }

    public String getHumedad() {
        return mensajeExito;
    }
}
