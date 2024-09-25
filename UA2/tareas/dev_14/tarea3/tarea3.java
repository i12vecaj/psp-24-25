

import java.io.FileReader;

class Reader extends Thread {
 private String fichero;

 public Reader(String fichero) {
     this.fichero = fichero;
     setName(fichero);
    }

 public void run() {
 int contCaracteres = 0;

 FileReader fr = null;

     try {
         fr = new FileReader(fichero);
          int caracter = fr.read();

     while (caracter != -1) {
          contCaracteres++;
          caracter = fr.read();
            }
        }  catch (Exception e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());

        } 
        
        finally {
        try {
         if (fr != null) {
                 fr.close();
                 System.out.println("Caracteres: " + contCaracteres);

                }

            } 
            catch (Exception e) {
                System.err.println("Cerrando fichero" + e.getMessage());
            }
        }
    }
}



class ExecutionTimer {
    private long startTime = 0;
    private long endTime = 0;
    private long timeElapsed;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
    }

    public long elapsedTime() {
        return timeElapsed = endTime - startTime;
    }
}

public class tarea3 {
    public static void main(String[] args) {

        ExecutionTimer tH = new ExecutionTimer();
        ExecutionTimer tsH = new ExecutionTimer();
        int numArg = args.length;

 

        System.out.println("*********************");
        System.out.println("Ejecutamos los hilos");
        tH.start();

        for (int i = 0; i < numArg; i++) {
            Reader hilo = new Reader(args[i]);

            hilo.start();

            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }

        }
        tH.stop();



        System.out.println("*********************");
        System.out.println("Ejecucion");
        for (int i = 0; i < numArg; i++) {
            reader(args[i]);
        }
        tsH.stop();

        System.out.println("Tiempo transcurrido con hilos: " + (tH.elapsedTime() / 1000000)
                + ",sin hilos: " + tsH.elapsedTime() / 1000000);
    }



    public static void reader(String fichero) {
        int contCaracteres = 0;

        FileReader fr = null;

        try {
            fr = new FileReader(fichero);
            int caracter = fr.read();

            while (caracter != -1) {
                contCaracteres++;
                caracter = fr.read();
            }
        } catch (Exception e) {
            System.err.println("Error de lectura" + e.getMessage());
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                    System.out.println("contCaracteres de texto introducidos" + contCaracteres);

                }
            } catch (Exception e) {
                System.err.println("Cerramos el fichero" + e.getMessage());
            }
        }
    }
}
