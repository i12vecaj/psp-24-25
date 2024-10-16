#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

// Función para imprimir el pib del hijo
void print_process_info(int child_number) {
    pid_t pid = getpid();
    pid_t ppid = getppid();
    printf("Soy el hijo %d, mi PID es %d y el PID de mi padre es %d\n", child_number, pid, ppid);
}

int main() {
     // Declara variables para almacenar el pib de los hijos
    pid_t pid1, pid2, pid3;

     // Crea el primer proceso hijo
    pid1 = fork();
  //cadena de if para ir llamando a cada hijo 
    if (pid1 < 0) {
        // Verifica si hay errores al crear el proceso hijo
        printf("Error al crear el hijo 1\n");
        return 1;
    } else if (pid1 == 0) {
        // llamada a la funcion que imprime la info
        print_process_info(1);
    } else {
        // Crea el segundo proceso hijo
      //todo se repite como en el hijo 1
        pid2 = fork();
        if (pid2 < 0) {
            printf("Error al crear el hijo 2\n");
            return 1;
        } else if (pid2 == 0) {
             
            print_process_info(2);
        } else {
            // Crea el tercer proceso hijo
            pid3 = fork();
            if (pid3 < 0) {
                printf("Error al crear el hijo 3\n");
                return 1;
            } else if (pid3 == 0) {
                print_process_info(3);
            } else {
                // Esperar a que todos los hijos terminen para que el padre salga el ultimo para tener orden
                wait(NULL);
                wait(NULL);
                wait(NULL);
                // Obtener el pib del padre
                pid_t ppid = getpid();
                // Imprimmir el pib del padre
                printf("Soy el padre, mi PID es %d\n", ppid);
            }
        }
    }

    return 0;
}
