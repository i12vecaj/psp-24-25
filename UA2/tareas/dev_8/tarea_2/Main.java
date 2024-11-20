public class Main {
    public static void main(String[] args) {
        //saldo inicial 1000
        CuentaCorriente cuenta = new CuentaCorriente(1000.0);
        System.out.println("Saldo inicial: " + cuenta.getSaldo());

        // hacemos 3 hilos y los hiciamos
        IngresoPorExtends hilo1 = new IngresoPorExtends(cuenta);
        IngresoPorExtends hilo2 = new IngresoPorExtends(cuenta);
        IngresoPorExtends hilo3 = new IngresoPorExtends(cuenta);
        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {
            hilo1.setPriority(1);
            hilo2.setPriority(10);
            hilo3.setPriority(6);
        } catch (Exception e) {
            System.out.println("Error en asignacion prioridades");
        }

        try {
            // esperamos a que terminen todos los hilos
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("/////////////////////////////////////////////////////////////");
        System.out.println("Saldo final: " + cuenta.getSaldo());
    }
}

// si quitamos el modificador synchoronized el programa se ejecuta de forma random usando la variable de forma no ordenada, pero como uso el join lo hace de forma secuencial
// mientras que si mantenemos el modificador synchronized los hilos hacen uso de la variable de una forma mas ordenada
