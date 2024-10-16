//Main

public class Main {
    public static void main(String[] args) throws InterruptedException {

         for (int i = 1; i <= 10; i++) {
             estadoDePlantasSensor estado = new estadoDePlantasSensor();
            tempSensor temperatura = new tempSensor();
            humedadSensor humedad = new humedadSensor();

            estado.estado();
            temperatura.temperatura();
            humedad.humedad();
        }


    }
}

//Archivo del hilo de la temperatura

public class tempSensor extends Thread {

    public void temperatura() throws InterruptedException {
      /*Debido al problema con la funcion random he deicidido usar el currentTimeMillis para obtener un valor pseodu aleatorio y utilizarlo a la hora de indicar el tiempo que ha pasado y tambien el valor aleatorio que registra el sensor*/
        long ms=System.currentTimeMillis();
        int nrandom = (int) (long) ((ms%1000)+54);
        /*A la hora de la espera como no he sido capaz de obtener un valor aleatorio lo he predefinido, pero usaría la funcion random o una similar indicandoe que debe ser de 1 a 3 y asignar ese valor aleatorio a la funcion sleep*/
        Thread.sleep(1000);
        long msAhora=System.currentTimeMillis();
        long diferenciaMs=(msAhora-ms)/1000;
        System.out.println(nrandom+"ºC "+diferenciaMs+" segundos de diferencia desde la ultima medición");

    }
}

//Archivo del hilo de la humedad

public class humedadSensor extends Thread {

    public void humedad() throws InterruptedException {
            /*Debido al problema con la funcion random he deicidido usar el currentTimeMillis para obtener un valor pseodu aleatorio y utilizarlo a la hora de indicar el tiempo que ha pasado y tambien el valor aleatorio que registra el sensor*/
        long ms=System.currentTimeMillis();
        int nrandom = (int) (long) ((ms%1000)+98);
        /*A la hora de la espera como no he sido capaz de obtener un valor aleatorio lo he predefinido, pero usaría la funcion random o una similar indicandoe que debe ser de 1 a 3 y asignar ese valor aleatorio a la funcion sleep*/
        Thread.sleep(1000);
        long msAhora=System.currentTimeMillis();
        long diferenciaMs=(msAhora-ms)/1000;
        System.out.println(nrandom+"% de humedad "+diferenciaMs+" segundos de diferencia desde la ultima medición");

    }
}

//Archivo del hilo del estado de la planta

public class estadoDePlantasSensor extends Thread {

    public void estado() throws InterruptedException {
            /*Debido al problema con la funcion random he deicidido usar el currentTimeMillis para obtener un valor pseodu aleatorio y utilizarlo a la hora de indicar el tiempo que ha pasado y tambien el valor aleatorio que registra el sensor*/
        long ms=System.currentTimeMillis();
        int nrandom = (int) (long) (ms%1000);
        /*A la hora de la espera como no he sido capaz de obtener un valor aleatorio lo he predefinido, pero usaría la funcion random o una similar indicandoe que debe ser de 1 a 3 y asignar ese valor aleatorio a la funcion sleep*/
        Thread.sleep(1000);
        long msAhora=System.currentTimeMillis();
        long diferenciaMs=(msAhora-ms)/1000;
        System.out.println(nrandom+" puntos de estado de la planta "+diferenciaMs+" segundos de diferencia desde la ultima medición");

    }
}
