public class MainTarea1 {
    public static void main(String[] args) {

        try{
            leerString lector = new leerString();

            lector.lectura();
        }
        catch (Exception e){
            System.out.println("Error, el programa no se ha ejecutado");
        }

    }
}
