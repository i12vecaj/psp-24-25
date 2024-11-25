public class Main {
    public static void main(String[] args) {
        Lector lector1 = new Lector("Lector1","Quijote");
        Lector lector2 = new Lector("Lector2","inflacion");

        try {
            lector1.run();
            lector2.run();
            lector1.join();
            lector2.join();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
//la conclusion a la que llego con ambas lecturas es que el tiempo que tardan en leer
//el archivo es proporcional al tamaño del archivo, ya que la lacetura es carácter por carácter
//de manera secuencial
