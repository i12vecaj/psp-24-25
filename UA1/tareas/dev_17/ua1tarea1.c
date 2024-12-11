#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

int main(){

    pid_t pid, hijo_pid; //Aqui declaramos las variables de tipo pid_t

    int num; //Declaramos la variable numero que es la que va a recoger la variable que introducimos.

    printf("Buenas introduzca un numero: ");

    scanf("%d", &num); //Pedimos al usuario el numero

    pid = fork();//Se debe crear el fork despues del scanf para que no de error al momento de la creacion de los procesos.  

    if (pid == -1) {//Aqui tendremos  el control de errores el cual nos mostrara un texto en caso de que algo falle.
        printf("Lo sentimos no hemos podido realizar sus calculos.\n");
        exit(-1);//Este exit hara que el programa termine.
    }

    if (pid == 0){
        printf("Soy el proceso hijo mi PID es: %d. Yo sumare 4 a su numero: %d. \n El resultado es: %d.\n", getpid(), num, num+4);//Con getpid() mostramos el PID del proceso hijo.
        printf("--------------------------------------\n");
    }
    else{
        hijo_pid = wait(NULL); //wait(NULL) sirve para hacer esperar al proceso padre hasta que termine su accion el proceso hijo.
        printf("Soy el proceso padre mi PID es: %d. Yo restare 5 a su numero: %d\n El resultado es: %d.\n", getpid(), num, num-5);//Con getpid() mostramos el PID del proceso padre.
    }

    exit(0);//Y aqui cierra el programa.
}