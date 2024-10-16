public class HiloPlantas implements Runnable{ //Implemento la clase Runnable
    String mensajeExito;

    public HiloPlantas() {
        this.mensajeExito = "";
    }

    @Override //Sobrescribo el metodo run.
    public void run() {
        mensajeExito = "Plantas obtenidas.";
        double temperaturaPlantas = Math.random() * 100; //Variable para guardar la temperatura de las plantas.
        System.out.println("Plantas: Las plantas se encuentran a " + temperaturaPlantas + "ºC.");
        System.out.println(mensajeExito);
        return;
    }

    public String getPlantas() {
        return mensajeExito;
    }
}
