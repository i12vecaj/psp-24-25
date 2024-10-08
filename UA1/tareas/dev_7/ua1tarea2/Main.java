public class Main {
    public static void main(String[] args) throws Exception {
        try{ //Intentamos realizar el siguiente fragmento de código
            Hilo h = new Hilo(); //Intanciamos el nuestra clase de Hilo
            h.start(); //Iniciamos el Hilo
        }catch(Exception e){ // Si algo falla en el proceso nos devuele el mensaje que nosotros indiquemos
            System.out.println("Error: " + e.getMessage());
        }

    }
}