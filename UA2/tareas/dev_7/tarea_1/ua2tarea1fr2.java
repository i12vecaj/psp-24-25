public class ua2tarea1fr2 {
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

        class Hilo extends Thread{ //Creamos el Hil extendiendo de Thread
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
            Hilo hilo = new Hilo("Hilo " + i ,contador); //Creamos el hilo
            hilo.start(); //Iniciamos el hilo
        }
    }
}
/*
    Conclusion
    El problema del apartado anterior se soluciona con la implementación del bloque
    synchronized en la clase Contador.
    Esta solución evita la condición de carrera y garantiza que todos los hilos sean capaces de
    incrementar el valor del contador de manera independiente.
 */