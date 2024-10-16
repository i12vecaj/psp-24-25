import java.util.*;
import java.util.concurrent.*;

// Ahora que lo veo medio s eme va ocurriendi gracias a un ejemplo que vi pero em resulta dificil implementarlos completamente  

public class Main {
    public static void main(String[] args) {
      
        Thread hiloTemperatura = new Thread(new SensorTemperatura()); 
        Thread hiloHumedad = new Thread(new SensorHumedad())

        hiloHumedad.start();
        hiloTemperatura.start();
    }

}

// Aqui en la main e intentado llamr y ejecutar los dos hilos que tengo se que tendria que referenciarlos pero ahora mismo no em acuerdo