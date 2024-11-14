public class ua2tarea2 {
    public static void main(String[] args) {
        class CuentaCorriente { //Creamos la clase CuentaCorriente
            int saldo;
            public CuentaCorriente() {
                this.saldo = 1000;
            } //Asignamos un valor inicial al saldo

            /*
             * Esta función recibe un entero y no devuelve nada.
             * Ese valor lo suma a la variable saldo, se genera un número random
             * entre 0.25 y 2 segundo para dormir al hilo durante ese valor.
             */
            public void setSaldo(int saldo) {
                this.saldo += saldo;
                try {
                    int duerme = (int) (Math.random() * (2000-250 + 1)+250);
                    Thread.sleep(duerme);
                }catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }


            /*
             * Esta función devuelve el saldo actual de la cuenta.
             * Se genera un número random entre 0.25 y 2 segundo para dormir al hilo durante ese valor.
             *
             */
            public int getSaldo() {
                try {
                    int duerme = (int) (Math.random() * (2000-250 + 1)+250);
                    Thread.sleep(duerme);
                }catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                return saldo;
            }

            /*
             * Esta función recibe un entero y una cadena, y realiza una transacción
             * en la cuenta.
             * Se utiliza el término synchronized para sincronizar el método.
             */
            public synchronized void  depositar(int cantidad, String nombre){
                System.out.println("El saldo actual antes de la transacción : " + saldo);
                setSaldo(cantidad);
                System.out.println(nombre + " ha realizado una transacción.");
                System.out.println("Saldo actual : " + getSaldo());
                System.out.println("---------------------------------------------------");
            }

        }

        class Hilo extends Thread { //Creamos la clase Hilo
            String nombre ;
            int cantidad;
            CuentaCorriente cuenta;


            /*
            *   Constructor
            * Recibe un objeto  CuentaCorriente y un entero,
            * los asigna a los atributos y muestra el nombre
            * del hilo junto a la cantidad para depositar, que ha sido
            * generado automaticamente.
            *
             */
            public Hilo( CuentaCorriente cuenta, int id){
                nombre = "Hilo " + id;
                cantidad = (int) (Math.random() * 1000 + 1);
                System.out.println(nombre + " " + cantidad);
                this.cuenta = cuenta;
            }


            @Override
            public void run() {
                synchronized (cuenta){ //Sincronizamos el recurso cuenta para evitar la condición de carrera
                    cuenta.depositar(cantidad, nombre); //Depositamos la cantidad junto al nombre del hilo
                }
            }
        }


        CuentaCorriente cuenta = new CuentaCorriente(); //Creamos el objeto de cuenta corirriente
        for (int i= 0; i<5; i++){ //Dentro de este bucle creamos los 5 hilos y los iniciamos
            Hilo hilo = new Hilo(cuenta, i);
            hilo.start();
        }
    }
}

/*
 *Conclusión
 * El uso de synchronized es fundamental en este programa para asegurar que
 * las operaciones de actualización de saldo sean consistentes y reflejen correctamente el
 * resultado de todas las transacciones realizadas por los hilos.
 */