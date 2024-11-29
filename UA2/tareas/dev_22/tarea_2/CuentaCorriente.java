import java.util.Random;

public class CuentaCorriente {

        private static float saldo;

        public CuentaCorriente(float saldo) {
            this.saldo = saldo;
        }

        public int tiempo() {
            Random random = new Random();
            return 250 + random.nextInt(2000);
        }

        public long saldoAleatorio() {
            Random random = new Random();
            return 1 + random.nextInt(10000);
        }

        //setter
        public float generarSaldo() {
            try {
                Thread.sleep(saldoAleatorio());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return saldo;
        }

        //getter
        public static float obtenerSaldo() {
            return saldo;
        }

        public static void setSaldo(float saldo) {
            CuentaCorriente.saldo = saldo;
        }

        //metodo sincronizado
        public static synchronized void historicoCuenta(float cantidad, String realizadorIngreso) {


            float resultado =  obtenerSaldo()+ cantidad;
            float previo = resultado-cantidad;
            System.out.println("Estado previo del saldo: " + previo);

            setSaldo(resultado);
            System.out.println("Ingreso de " + cantidad + " realizado por " + realizadorIngreso);
            System.out.println("El estado de la cuenta es de " + resultado);
        }

        /*
        * Cuando al método se le quita la sincronización el resultado, es disperso,
        * básicamente, los ingresos con el estado de la cuenta no son correctos,
        * mientras que al sincronizarlo, ingresos y estado de la cuenta van ligados.
        */


    }
