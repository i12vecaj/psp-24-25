
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main()
{
    pid_t idPPadre, idPHijo1, idPHijo2, idPHijo3;//Creacion de variables



    idPPadre = fork();//Obtenemos los hijos

    //Codigo proceso hijo1
    if (idPPadre == -1 ) //Ha ocurrido un error
    {
        printf("No se ha podido crear el proceso hijo1");
        exit(-1);
    }

    if (idPPadre == 0 )//Comprobamos que estamos en proceso hijo
    {
        printf("PID proceso hijo1:%d\t PID proceso padre:%d.\n",
                getpid(), getppid() );
    }
    else    //Nos encontramos en Proceso padre
    {
        idPHijo1 = wait(NULL); //espera la finalización del proceso hijo

    }
    //////////////////////////////

    //Codigo proceso hijo2
    if (idPPadre == -1 ) //Ha ocurrido un error
    {
        printf("No se ha podido crear el proceso hijo2");
        exit(-1);
    }

    if (idPPadre == 0 )//Comprobamos que estamos en proceso hijo
    {
        printf("PID proceso hijo2:%d\t PID proceso padre:%d.\n",
                getpid(), getppid() );
    }
    else    //Nos encontramos en Proceso padre
    {
        idPHijo2 = wait(NULL); //espera la finalización del proceso hijo

    }
    //////////////////////////////

    //Codigo proceso hijo3
    if (idPPadre == -1 ) //Ha ocurrido un error
    {
        printf("No se ha podido crear el proceso hijo3");
        exit(-1);
    }

    if (idPPadre == 0 )//Comprobamos que estamos en proceso hijo
    {
        printf("PID proceso hijo3:%d\t PID proceso padre:%d.\n",
                getpid(), getppid() );
    }
    else    //Nos encontramos en Proceso padre
    {
        idPHijo3 = wait(NULL); //espera la finalización del proceso hijo

    }
    return 0;
}