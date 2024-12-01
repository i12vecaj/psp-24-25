import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ua2tarea1fr2runnable {

    public void main(String[] args) {
        Contador contador = new Contador();

        for (int i = 0; i < 5; i++) {
            Hilo hilo = new Hilo(contador);
            new Thread(hilo).start();
        }
    }

    static class Contador {
        int valor;
        int valorIncrementoHilos;

        public Contador() {
            this.valor = 0;
            this.valorIncrementoHilos = 1000;
        }

        public synchronized void setValor() {
            this.valor += valorIncrementoHilos;
            System.out.println("El valor actual es: " + valor);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    class Hilo implements Runnable {
        private final Contador contador;

        public Hilo(Contador contador) {
            this.contador = contador;
        }

        @Override
        public void run() {
            contador.setValor();
        }
    }

    @Nested
    class ua2tarea1fr2runnableTest {

        @Test
        void testIncrementoHilos() {
            Contador contador = new Contador();
            int numeroHilos = 5;
            int incrementoEsperado = contador.valorIncrementoHilos * numeroHilos;

            for (int i = 0; i < numeroHilos; i++) {
                Runnable hilo = new Hilo(contador);
                new Thread(hilo).start();
            }

            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                fail("La espera fue interrumpida: " + e.getMessage());
            }

            assertEquals(incrementoEsperado, contador.valor, "El valor del contador no es el esperado después de ejecutar los hilos.");
        }
    }
}
