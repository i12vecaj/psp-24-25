
public class ua2tarea3{
    public static void main(String[] args) throws InterruptedException {
        HiloContadorCaracteres contadorDeCaracteres = new HiloContadorCaracteres("AulaDeSoftwareLibreFPCordoba.txt");
        Thread hiloQueCuenta = new Thread(contadorDeCaracteres);
        hiloQueCuenta.start();
        hiloQueCuenta.join();
    }
}


