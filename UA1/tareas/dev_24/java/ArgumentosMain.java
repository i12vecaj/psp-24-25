package recibirargumentos;

public class RecibirArgumentos {
    public static void main(String[] args) {
        Thread hilo = new Thread(new Argumentos(args));
        hilo.start();
    }
}

class Argumentos implements Runnable {
    private String[] args;
    
    Argumentos (String[]args) {
        this.args = args.clone();
    }
    
    @Override
    public void run() {
        if(args.length < 1) {
            System.exit(1);
        } else if (args[0] instanceof String) {
            System.exit(2);
        } else if (Integer.getInteger(args[0]) < 0) {
            System.exit(3);
        } else {
            System.exit(0);
        }
    }
}