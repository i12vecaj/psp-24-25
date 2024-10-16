#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>

// Función para que los hijos impriman su identificador, PID y el del padre
void print_info(int id) {
    printf("Soy el hijo %d, mi PID es %d y el PID de mi padre es %d\n", id, getpid(), getppid());
}

int main() {
    pid_t pid;
    int numero_de_hijos;
    //Creamos los procesos hijos y los ejecutamos
    for (numero_de_hijos = 1; numero_de_hijos <= 3; numero_de_hijos++) {
        pid = fork();
        if (pid < 0) {
            // Control de errores
            printf("Error en la creacion del proceso");
            exit(1);
        } else if (pid == 0) {
            // Proceso hijo
            print_info(numero_de_hijos);
            exit(0);
        }
    }

    // Esperar a que todos los hijos terminen
    for (numero_de_hijos = 1; numero_de_hijos <= 3; numero_de_hijos++) {
        wait(NULL);
    }

    // PID del proceso padre
    printf("Soy el padre, mi PID es %d\n", getpid());
}
