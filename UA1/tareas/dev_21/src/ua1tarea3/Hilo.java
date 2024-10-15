package ua1tarea3;

public class Hilo implements Runnable {
    private String[] args;

    public Hilo (String[] args){
        this.args = args;
    }

    @Override
    public void run(){
        try{
            int numero = Integer.parseInt(args[0]);

            if (numero < 0)
                System.exit(0);
            else
                System.exit(1);
        }catch (Exception e){
            System.exit(0);
        }
    }
}