class MostrarCero implements Runnable {
    
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.print("0 ");
        }
    }
}
class MostrarUno implements Runnable {
    
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.print("1 ");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        
        Thread hiloCero = new Thread(new MostrarCero());
        Thread hiloUno = new Thread(new MostrarUno());

        
        hiloCero.start();
        hiloUno.start();

        
       
       
    }
}