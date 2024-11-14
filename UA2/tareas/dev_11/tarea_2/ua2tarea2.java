public class ua2tarea2 {
    public static void main(String[] args) {

        //Lo meto dentro de un try-catch por si diera algun error avisar al usuario
        try {
            //Instancio un objeto de la clase Cuenta corriente con un saldo inicial de 1000
            CuentaCorriente cuenta = new CuentaCorriente(1000);
            System.out.println("Saldo inicial: " + cuenta.getSaldo() + "€");

            //Instancio 3 objetos de mi clase Hilo, para hacer 3 ingresos distintos
            HiloIngreso hilo1 = new HiloIngreso(cuenta, 300, "Álvaro");
            HiloIngreso hilo2 = new HiloIngreso(cuenta, 150, "Juan");
            HiloIngreso hilo3 = new HiloIngreso(cuenta, 200, "Fran");

            //Inicio los hilos
            hilo1.start();
            hilo2.start();
            hilo3.start();
            //Y realizo un join() para esperar a que cada hilo acabe para iniciar el siguiente
            hilo1.join();
            hilo2.join();
            hilo3.join();

            System.out.println("Saldo final: " + cuenta.getSaldo() + "€");
        } catch (InterruptedException e) {
            System.err.println("Error al esperar a que los hilos finalicen.");
            e.printStackTrace();
        }
    }

    //Clase cuenta corriente con un atributo saldo, y un constructor que define el saldo inicial
    static class CuentaCorriente {
        private int saldo;

        public CuentaCorriente(int saldoInicial) {
            this.saldo = saldoInicial;
        }

        //Los getters y setters se hacen synchronized para que todos los hilos puedan acceder al saldo sin que se produzca un error de concurrencia
        public synchronized int getSaldo() {
            try {
                Thread.sleep((int) (Math.random() * (2000 - 250 + 1)) + 250);
            } catch (InterruptedException e) {
                System.err.println("Error en getSaldo.");
                e.printStackTrace();
            }
            return saldo;
        }

        public synchronized void setSaldo(int saldo) {
            try {
                Thread.sleep((int) (Math.random() * (2000 - 250 + 1)) + 250);
            } catch (InterruptedException e) {
                System.err.println("Error en setSaldo.");
                e.printStackTrace();
            }
            this.saldo = saldo;
        }

        //Metodo addSaldo donde esta la logica que añade la cantidad a ingresar en el saldo de la cuenta
        public synchronized void addSaldo(int cantidad, String usuarioQueIngresa) {
            int saldoActual = getSaldo();
            System.out.println("Usuario " + usuarioQueIngresa + " está realizando un ingreso.");
            System.out.println("Saldo actual antes del ingreso: " + saldoActual + "€");
            saldoActual += cantidad;
            setSaldo(saldoActual);
            System.out.println("Se ha ingresado " + cantidad + "€.");
            System.out.println("Saldo después del ingreso: " + getSaldo() + "€.");
            System.out.println("----------------------------------");
        }
    }

    //Clase Hilo donde se realizara el ingreso
    static class HiloIngreso extends Thread { //Aqui estan los atributos que tiene el hilo
        private final CuentaCorriente cuenta;
        private final int cantidad;
        private final String usuario;

        //Y este es el constructor que recoge el usuario y la cantidad ingresar puesto que el metodo run de este hilo se basa en el metodo addSaldo de la propia clase CuentaCorriente
        public HiloIngreso(CuentaCorriente cuenta, int cantidad, String usuario) {
            this.cuenta = cuenta;
            this.cantidad = cantidad;
            this.usuario = usuario;
        }

        @Override
        public void run() {
            cuenta.addSaldo(cantidad, usuario);
        }
    }
}
