#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h> 

int main() {

    pid_t process_id, child_pid;  // Declaramos las variables de tipo pid_t para los procesos

    int input_value;  // Variable que almacenará el número ingresado por el usuario


    // Solicitar al usuario que ingrese un número
    printf("Por favor, ingrese un número entero: ");
    scanf("%d", &input_value);  // Capturar el número ingresado



    // Crear un proceso hijo usando fork()
    process_id = fork(); 



    // Control de errores al crear el proceso
    if (process_id < 0) {
        fprintf(stderr, "Error: No se pudo crear el proceso.\n");
        exit(1);  // Terminar el programa en caso de error
    }



    // Si es el proceso hijo
    if (process_id == 0) {
        printf("Soy el hijo (PID: %d). Voy a sumarle 4 a su número: %d.\n", getpid(), input_value);
        printf("Resultado del hijo: %d\n", input_value + 4);
        printf("----------------------------\n");
    } 


    // Si es el proceso padre
    else {
        // Esperar a que el proceso hijo termine
        child_pid = wait(NULL);  
        printf("Soy el padre (PID: %d). Ahora restaré 5 a su número: %d.\n", getpid(), input_value);
        printf("Resultado del padre: %d\n", input_value - 5);
    }

    return 0;  // Terminar el programa correctamente
}
