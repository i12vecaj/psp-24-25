import java.util.Scanner;

public class CadenaCaracteres extends Thread {

    public void run() {
        System.out.println("Inicialización del hilo:");
        String resultado;
        resultado = funcadena();
        System.out.println("El resultado es: " + resultado);
        System.out.println("Fin del hilo.");
    }

    public String funcadena(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese una cadena de caracteres: ");
        String cadena = "";
        boolean validador = false;
        do {
            String texto = scanner.nextLine();
            if (texto.equals("*")) {
                validador = true;
            } else {
                cadena += texto + "\n ";
            }
        } while (!validador);
        scanner.close();
        return cadena;
    }
}
