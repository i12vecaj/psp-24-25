  #include <stdlib.h>
  #include <unistd.h>
  #include <stdio.h>

  // Tarea 1 de la unidad 1 - Álvaro Fernández Amaro
  int main() {
          
      int variableNumerica;
      printf("Introduce un numero: ");
      scanf("%d", &variableNumerica); //Pide un número al usuario.

      pid_t pid, pid_hijo;
      pid =  fork(); // A partir del proceso padre (main) se crea un nuevo proceso hijo.

      if (pid == -1) { // Haciendo esto estamos realizando un control de errores, haciendo que si el proceso da "-1" muestre un mensaje de error y salga del programa.
          printf("No se ha podido crear el proceso hijo");
          exit(-1); // Sale del programa con un código de error.
      }
      
      // En caso de que los procesos se ejecuten correctamente, se ejecutarán los siguientes if.
      if (pid == 0) { // Si el pid es 0, es el proceso hijo.
          printf("\nSoy el proceso hijo con un PID %d, y mi función es sumar 4 a la variable.\n", getpid()); // getpid() es una función que devuelve el ID del proceso.
          variableNumerica = variableNumerica + 4;
          printf("La variable ahora vale: %d\n", variableNumerica);
          printf("\n------------------------------\n");
          exit(0); // Sale del proceso hijo.
      }
      else {  // Si el pid es distinto de 0, es el proceso padre.
        pid_hijo = wait(NULL);
        printf("\nSoy el proceso padre y mi PID es %d, y mi función es restar 5 a la variable.\n", getppid()); // getppid() es una función que devuelve el ID del padre del proceso.
        variableNumerica = variableNumerica - 5;
        printf("La variable ahora vale: %d\n", variableNumerica);
        exit(1); // Sale del proceso padre.
      }
  }
