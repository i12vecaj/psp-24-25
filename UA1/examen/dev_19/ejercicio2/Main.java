
public class Main {
    public static void main(String[] args) {
int contador = 10;// es el numero de vueltas
        Hilo1 temp = new Hilo1();
        Hilo2 hum = new Hilo2();
        Hilo3 plant = new Hilo3();

        for(int i = 0; i < contador; i++){//bucle para poder ejecutar los hilos 10 veces
            temp.run();
            hum.run();
            plant.run();
        }
        // lo que he realizado en cada hilo es crear un numero ramdom para que la funcion sleep pueda tomar valores aleatorios
        //luego los he ejecutado dentro de un bucle para que simula continuidad de la toma de medidas
    }
}
