#include <stdio.h>
#include <unistd.h>  
#include <stdlib.h>  

int main() {
    int numero;
    pid_t pid;
    int resultado;

    // Pedir un numero al usuario con control de errores
    printf ("Hola\n");
    printf("Introduce un número entero: ");
    resultado = scanf("%d", &numero);

    // Si el usuario no introduce un número entero
    if (resultado != 1) {
        printf("Error: Debes introducir un número entero.\n");
        return 1;  // Salimos del programa con un mensaje de error
    }

    // Creacion de los procesos
    pid = fork();  // Crea un proceso hijo

    if (pid < 0) {
        // Control de errores en caso de que fork falle
        printf("Error: No se pudo crear el proceso.\n");
        return 1;
    }

    if (pid == 0) {
        // Proceso hijo
        printf("Hijo: El valor sumando 4 es %d\n", numero + 4);
    } else {
        // Proceso padre
        printf("Padre: El valor restando 5 es %d\n", numero - 5);
    }

    return 0;
}

