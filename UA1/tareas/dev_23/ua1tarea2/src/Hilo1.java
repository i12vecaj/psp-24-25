import java.util.Scanner;

public class Hilo1 extends Thread{
    @Override
    public void run(){
        StringBuilder input = empezar();
        escribir_input(input);
    }
    public StringBuilder empezar(){
        Scanner scanner = new Scanner(System.in);
        StringBuilder input = new StringBuilder();
        char terminationChar = '*'; // Carácter de terminación

        System.out.println("Introduce una cadena de caracteres (termina con '*'):");

        while (true) {
            // Leer una línea completa
            String line = scanner.nextLine();

            // Comprobar si la línea contiene el carácter de terminación
            if (line.indexOf(terminationChar) != -1) {
                // Si se encuentra el carácter de terminación, solo se añade hasta el carácter
                input.append(line, 0, line.indexOf(terminationChar));
                break; // Salir del bucle
            } else {
                // Si no se encuentra el carácter de terminación, se añade la línea completa
                input.append(line).append(System.lineSeparator());
            }
        }
        scanner.close(); // Cerrar el escáner
        return input;
    }
    public void escribir_input(StringBuilder input){
        System.out.println("Cadena ingresada:");
        System.out.println(input.toString());
    }
}
