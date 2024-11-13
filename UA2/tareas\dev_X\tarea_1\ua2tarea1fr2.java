
/*Creo la clase contador junto a sus metodos correspondientes,
esta servirá para luego poder llamarla y que la utilicen todos los hilos
sin necesidad de que cada uno tenga una variable independiente*/

class contador{

    /*Creo la variable que almacenará el valor del contador*/

    private int numero = 0;
    contador (int numer){
        this.numero=numer;
    }

    /*Creo un método que sumará 1 a la variable de contador*/

    public synchronized void incrementar(){
        numero++;
    }

    /*Creo un método que devuelve el valor que tenga la variable que se crea al principio de la clase contador*/

    public synchronized int resultado(){
        return numero;
    }
}

/*Creo la aprte del código que obtendrá la variable y le sumará de uno en un 1000,
esta clase llamada "hiloSumador" posteriormente será llamada 5 veces como hilo1, hilo2, hilo3, etc ya que en todos los casos hace la misma funcion,
en caso de que cada hilo realizara una tarea distinta cada uno se crearía de forma independiente y sería llamado de forma independiente.*/

class hiloSumador extends Thread{

    /*Creo un objeto contador de la clase contador*/

    private contador ContadorDelHilo;

    /*Constructor de la clase hiloSumador para poder asignar los valores que debe recibir*/

    public hiloSumador(String nombre, contador c){
        ContadorDelHilo = c;
        setName(nombre);
    }

    /*Método run que se ejecutará al llamar a esta clase*/

    public void run(){

        /*Bucle for que llamará 1000 veces al metodo "incrementar" del objeto contador para aumentar su valor en uno 1000 veces*/

        for (int i=0; i<1000; i++){
            ContadorDelHilo.incrementar();
        }

        /*System.out para poder escribir por la terminal el valor de la variable una vez acabado el buble for*/

        System.out.println("El valor del contador es de :" + ContadorDelHilo.resultado() + " tras la ejecucion del el hilo" + getName());
    }

}

public class Main {
    public static void main(String[] args) {

        /*Creación de los objetos hilo1, hilo2, hilo3, etc para su posterior iniciación con el metodo "start"*/

        contador cont = new contador(0);
        hiloSumador hilo1 = new hiloSumador("Hilo 1",cont);
        hiloSumador hilo2 = new hiloSumador("Hilo 2",cont);
        hiloSumador hilo3 = new hiloSumador("Hilo 3",cont);
        hiloSumador hilo4 = new hiloSumador("Hilo 4",cont);
        hiloSumador hilo5 = new hiloSumador("Hilo 5",cont);

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();


    }
}

