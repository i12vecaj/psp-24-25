import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EjecutarLectorDeCadena {

    public static void main(String[] args) {
        // try {
        //     //Crear un proceso para ejecutar el programa LectorDeCadena
        //     Process proceso = Runtime.getRuntime().exec("java LectorDeCadena");

        //     // Leer proceso
        //     BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        //     String linea;

        //     System.out.println("Proceso LectorDeCadena:");
        //     while ((linea = lector.readLine()) != null) {
        //         System.out.println(linea);
        //     }

        //     // Esperar a que termine
        //     proceso.waitFor();

        //     System.out.println("Proceso terminado");

        // } catch (Exception e) {
        //     // Control de errores si algo falla
        //     System.out.println("Ocurrió un error al ejecutar LectorDeCadena");
        //     e.printStackTrace();
        // }
        LectorDeCadena h1 = new LectorDeCadena();
        h1.start();
    }
}
