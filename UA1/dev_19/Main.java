
public class Main {
    public static void main(String[] args) {
        try {
            // generamos un objeto de la clase Lector para poder llamar a su método
            Lector lector = new Lector();
            lector.entradaSalida();//llamamos al método del objeto
        }
        catch (Exception e) {
            System.out.println("Errosr, por favor reinicie el programa");
        }
    }
}