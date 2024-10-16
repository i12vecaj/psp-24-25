public class Argumentos {
    public static void main(String[] args) {

        if  (args.length < 1) {
            System.out.println("Error! Por favor ingrese al menos un argumento.");
            return;
    }

    if {
        ProcessBuilder  pb = new ProcessBuilder("java", "Main", args[0]);
        Process pr = ProcessBuilder.start();
        int exitCode = pr.waitFor();

        switch (exitCode) {
            case 0:
                System.out.println("El argumento ingresado es correcto y es entero no negativo.");
                break;
            case 1:
                System.out.println("No hay argumentos.");
                break;
            case 2:
                System.out.println("El argumento ingresado es una cadena.");
                break;
            case 3:
                System.out.println("El argumento ingresado es un numero entero menor a 0.");
                break;
            default:
                System.out.println("Codigo de salida inesperado: " + exitCode);

        } else (Exception e) {
        System.out.println("Error iniciando el proceso: " + e.getMessage());
        }

    }
}