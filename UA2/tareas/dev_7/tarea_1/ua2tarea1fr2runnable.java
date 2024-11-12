public class ua2tarea1fr2runnable {
    public static void main(String[] args) {
        class Contador{ //Creamos el contador que compartiran los hilos
            int valor;
            int iteraciones;
            public Contador() { //Establecemos los valores iniciales del hilo
                valor = 0;
                iteraciones = 3000; //He tenido que aumentar el número de iteraciones ya que con 1000 no podía ver la diferencia ya que las operaciones terminaban antes de que otro hilo pueda molestar en la ejecucción
            }
            public void incrementar(){ //Incrementamos el valor del contador
                for (int i=0; i< iteraciones;i++){
                    valor++;
                }
            }

            public int getValor(){ //Devolvemos el valor del contador
                return valor;
            }
        }

        class Hilo implements Runnable{ //Creamos el Hil extendiendo de Thread
            private String nombreHilo;
            private Contador contador;

            public Hilo(String nombreHilo, Contador contador) { //Guardamos el objeto de contador y el nombre del hilo
                this.nombreHilo = nombreHilo;
                this.contador = contador;

            }

            @Override
            public void run() {
                synchronized (contador){ //Sincronizamos el objeto de contador para evitar la condición de carrera
                    contador.incrementar(); //Incrementamos el valor
                    System.out.println("El resultado de " + nombreHilo + " es " + contador.getValor() ); //Comprobamos el valor del contador
                }
            }
        }

        Contador contador = new Contador(); //Instanciamos el objeto de contador que compartirán los hilos

        for (int i = 0; i < 5; i++) { //Con este bucle creamos e iniciamos los 5 hilos
            Hilo runnable = new Hilo("Hilo " + i ,contador); //Nuestra objeto Hilo que implementa la interface Runnable
            Thread hilo = new Thread(runnable);    //Creamos el hilo pasandole como argumento nuestra clase Hilo
            hilo.start(); //Iniciamos el hilo
        }
    }
}
/*
    Conclusion
    En este caso hemos cambiado la forma de crear el hilo, implementamos la interface Runnable.
    El resultado es el mismo ya que hemos sincronizado el contador.
    La forma de crear el hilo no interfiere en el resultado de este problema.
 */