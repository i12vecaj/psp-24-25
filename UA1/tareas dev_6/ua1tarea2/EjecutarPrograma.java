public class EjecutarPrograma {
  public static void main(String[] args) {
    
      // FR3: Crear y ejecutar el hilo
      LeerCadena leerCadena = new LeerCadena();
      leerCadena.start(); // Iniciar el hilo para leer la cadena

      try {
          leerCadena.join(); // Esperar a que el hilo termine
      } catch (InterruptedException e) {
          System.out.println("El hilo fue interrumpido: " + e.getMessage());
      }
  }
}

