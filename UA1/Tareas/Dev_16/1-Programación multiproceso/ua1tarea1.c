//Código hecho por Alberto Mármol Bello para la asignatura de Programación de servicios y procesos.
#include <stdio.h>
#include <stdlib.h>
#include <windows.h>

void create_child_process(HANDLE hReadPipe);

int main() {
    int var;
    HANDLE hPipe[2];

    //FR2: Pedir al usuario una variable.
    printf("Introduce un valor entero: ");
    if (scanf("%d", &var) != 1) {
        fprintf(stderr, "Error: Entrada no válida.\n");
        exit(EXIT_FAILURE);
    }

    //Crear un pipe
    if (!CreatePipe(&hPipe[0], &hPipe[1], NULL, 0)) {
        fprintf(stderr, "Error al crear el pipe: %lu\n", GetLastError());
        exit(EXIT_FAILURE);
    }

    //FR1: Crear el proceso hijo.
    create_child_process(hPipe[0]);

    //Escribir el valor en el pipe.
    DWORD written;
    if (!WriteFile(hPipe[1], &var, sizeof(var), &written, NULL)) {
        fprintf(stderr, "Error al escribir en el pipe: %lu\n", GetLastError());
    }
    
    //Cerramos el lado de escritura.
    CloseHandle(hPipe[1]);

    //Esperar un momento para que el hijo termine.
    //Solo para dar tiempo al hijo a leer.
    Sleep(100); 

    //FR3: Restar 5 a la variable.
    var -= 5;
    printf("Proceso padre: variable - 5 = %d\n", var);

    return 0;
}

void create_child_process(HANDLE hReadPipe) {
    STARTUPINFO si;
    PROCESS_INFORMATION pi;

    //Prepara la información para el proceso hijo.
    ZeroMemory(&si, sizeof(si));
    si.cb = sizeof(si);
    
    //Conectar entrada estándar al pipe.
    si.hStdInput = hReadPipe; 
    
    //Usar handles estándar.
    si.dwFlags |= STARTF_USESTDHANDLES; 

    //FR1: Crear el proceso hijo.
    if (!CreateProcess(NULL, "cmd.exe /c pause", NULL, NULL, TRUE, 0, NULL, NULL, &si, &pi)) {
        fprintf(stderr, "Error al crear el proceso: %lu\n", GetLastError());
        exit(EXIT_FAILURE);
    }

    //Cerrar handles del proceso hijo.
    CloseHandle(pi.hThread);
    CloseHandle(pi.hProcess);
}