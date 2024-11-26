import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //Lista de los nombre de los archivos
        ArrayList<String> archivos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String archivo;
        do {
            System.out.println("Introduce los nombres de los archivos que quieras contar(Pulsa * para salir)");
            archivo = scanner.nextLine();
            if (!archivo.equals("*"))
                archivos.add(archivo);
        }while (!archivo.equals("*"));

        List<Thread> listaHilos  = new ArrayList<>();

        for (String arch : archivos){
            Hilo hilo = new Hilo(arch);
            listaHilos.add(hilo);
            hilo.start();
        }

        for (Thread hilo : listaHilos){
            try{
                hilo.join();
            }catch (Exception e){

            }
        }
    }
}