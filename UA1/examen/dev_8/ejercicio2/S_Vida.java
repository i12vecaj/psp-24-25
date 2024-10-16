import java.util.Random;

public class S_Vida {
    private int porcentaje;
    //en el contructor meto el metodo setter para que el atributo porcentaje tenga un valor
    public S_Vida (){
        setPorcentaje();
    }
    //Hago uso de la libreria Random para randomizar el porcentaje
    public  void setPorcentaje() {
        Random num = new Random (System.currentTimeMillis());
        int num_random = num.nextInt(40);
        porcentaje = num_random;
    }

    public int getporcentaje(){
        return porcentaje;
    }
}