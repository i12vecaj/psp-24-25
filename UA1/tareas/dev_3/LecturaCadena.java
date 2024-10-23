import java.util.Scanner;

public class LecturaCadena implements Runnable {
    private String texto;

    public LecturaCadena() {
        this.texto = "";
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String input;

        System.out.println("Introduce texto (termina con *):");


        do {
            input = scanner.nextLine();
            sb.append(input).append("\n");
        } while (input.indexOf('*') == -1);

        
        texto = sb.toString();
        int indexAsterisco = texto.indexOf('*');
        if (indexAsterisco != -1) {
            texto = texto.substring(0, indexAsterisco); 
        }

       
        System.out.println("Texto leído:\n" + texto);
    }

    public String getTexto() {
        return texto;
    }

    public static void main(String[] args) {

        LecturaCadena lecturaCadena = new LecturaCadena();
        Thread hiloLectura = new Thread(lecturaCadena);
        hiloLectura.start();

        try {
            hiloLectura.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
