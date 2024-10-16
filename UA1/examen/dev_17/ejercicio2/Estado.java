public class Estado extends Thread{
    public Estado() {

    }

    public void run() {

        for (int i = 0; i < 10; i++) {//Metemos un for para realizar los 10 ciclos

            int estado = (int)Math.floor(Math.random()*10+1);//Declaramos la variable dentro para que en cada ciclo cambie
            System.out.println("El estado de las plantas del huerto del 1 al 10 es un: "+estado+".\n");

            long start = System.currentTimeMillis();//Declaramos la variable start para poder mostrar el tiempo que se para el hilo
            try {//Metemos un try and catch para la prueba de errores
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("El hilo esta en pausa durante "+(System.currentTimeMillis()-start)+"milisegundos.\n");
        }

    }

}
