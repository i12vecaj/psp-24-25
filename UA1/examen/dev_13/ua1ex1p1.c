#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

// Función para mostrar la información del hijo
void mostrarInformacionHijos(int hijo_numero) {
    pid_t pHijo = getpid();   // PID del hijo
    pid_t pPadre = getppid(); // PID del padre

    printf("El hijo %d tiene un PID de %d y el de su PADRE es %d.\n", hijo_numero, pHijo, pPadre);
}

int main() {
    pid_t pPadre = getpid(); // PID del proceso padre
    int numHijos = 3;       // Numero de hijos que vamos a crear
    pid_t pHijo;

    // Crear los hijos en un bucle
    for (int i = 1; i <= numHijos; i++) {
        pHijo = fork();
        
        if (pHijo < 0) {  // Control de errores
            perror("Error al intentar crear el hijo");
            exit(EXIT_FAILURE);
        }

        if (pHijo == 0) {  // Código ejecutado por el hijo
            mostrarInformacionHijos(i);
            exit(EXIT_SUCCESS); // El hijo termina su ejecución
        }
    }

    // Esperar a que terminen todos los hijos
    for (int i = 0; i < numHijos; i++) {
        wait(NULL);
    }

    // PID del padre antes de finalizar
    printf("PID del proceso PADRE: %d\n", pPadre);

    return 0;
}
