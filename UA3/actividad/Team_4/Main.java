import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el número de nodos:");
        int numMiembros = scanner.nextInt();
        scanner.close();

        for (int i = 1; i <= numMiembros; i++) {
            boolean soyElUltimo = i == numMiembros;
            boolean tieneToken = (i == 1);  
            boolean tieneTokenReverso = (i == numMiembros); 
            new Thread(new MiembroToken(i, 10000 + (i - 1), tieneToken, tieneTokenReverso, soyElUltimo, numMiembros)).start();

            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
