package org.example;

public class Hilo implements Runnable{

    /*
    * Creamos dos variables una que recibe los argumentos y otra que retorna el codigo de acceso
    * */
    private String [] argumentos;
    public int exitCode;

    public Hilo(String [] argumentos) {
        this.argumentos = argumentos;
        this.exitCode = 0;
    }

    @Override
    public void run() {
        if(argumentos.length < 1) {
            establecerExitCode(1);
        }
        for(String arg : argumentos) {
            try {
                int esNumero = Integer.parseInt(arg);

                if(esNumero < 0 ) {
                    //Numero negativo
                    establecerExitCode(3);
                }

            } catch(Exception e) {
                //Estamos hablando de una cadena de texto
                establecerExitCode(2);
            }
        }
    }

    public void establecerExitCode(int exitCode) {
        this.exitCode = exitCode;
    }

    public int getExitCode() {
        return this.exitCode;
    }

    /*
    * Esta funcion retorna un objeto que esta formado por una variable result que tiene el valor de exitCode
    * */
    public Object[] probarTest() {

        Object[] result = {getExitCode()};

        return result;
    }
}
