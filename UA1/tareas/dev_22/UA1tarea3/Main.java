
public class Main {

    public static void main(String[] args) {
        System.out.println("Ingresa los argumentos de entrada:");

        if (args.length < 1) {
            System.exit(1);
        }

        String argumento = args[0];

        if (argumento.matches("-?\\d+")) {
            int numero = Integer.parseInt(argumento);
            if (numero < 0) {
                System.exit(3);
            }
        } else {
            System.exit(2);
        }
    }
}
