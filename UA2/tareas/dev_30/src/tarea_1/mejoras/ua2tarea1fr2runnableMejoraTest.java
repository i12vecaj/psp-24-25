import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ua2tarea1fr2runnableMejoraTest {

    @Test
    public void testIncrementoContador() throws InterruptedException {
        // Arrange
        ua2tarea1fr2runnableMejora.Contador contador = new ua2tarea1fr2runnableMejora.Contador(0);
        Thread hilo1 = new Thread(new ua2tarea1fr2runnableMejora.incrementadorContador(contador));
        Thread hilo2 = new Thread(new ua2tarea1fr2runnableMejora.incrementadorContador(contador));
        Thread hilo3 = new Thread(new ua2tarea1fr2runnableMejora.incrementadorContador(contador));
        Thread hilo4 = new Thread(new ua2tarea1fr2runnableMejora.incrementadorContador(contador));
        Thread hilo5 = new Thread(new ua2tarea1fr2runnableMejora.incrementadorContador(contador));

        // Act
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        hilo1.join();
        hilo2.join();
        hilo3.join();
        hilo4.join();
        hilo5.join();

        // Assert
        assertEquals(5000, contador.getValorContador(), "El valor final del contador debe ser 5000.");
    }
}
