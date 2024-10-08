import java.util.Scanner;

class MostrarUno implements Runnable{ //creamos la clase con todo el codigo que tendrá el hilo
    public void run()  {
        try{
        Scanner scanner = new Scanner(System.in);
        StringBuilder cadena = new StringBuilder(); 
        String linea;
        //abrimos el scann y hacemos la variable que guardará toda la info que almacenará el usuario
        System.out.println("Escribe la información(añade * para finalizar):");

        while (true) {//hacemos un while para que guarde toda la info que el usuario ponga
            linea = scanner.nextLine();  

            if (linea.contains("*")) {  
                cadena.append(linea.substring(0, linea.indexOf('*')));
                break; //break para terminar el while 
            }

            cadena.append(linea);  
        }
    }
    catch(Exception e){//control de errores para el hilo
        System.out.println("Error: " + e.getMessage());
    }
        
        
       
    }

    @Override
    public String toString() {
        return "MostrarUno []";
    }
}
public class  ua1tarea2 {//vamos al main para añadir el hilo
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        String respuesta;
        System.out.println("Pulse cualquier botón para continuar");
        respuesta = scanner.nextLine(); 
        //cuando el usuario pulse cualquier tecla se iniciará el hilo
        Thread hiloCero = new Thread(new MostrarUno());
        hiloCero.start();
    }
}
