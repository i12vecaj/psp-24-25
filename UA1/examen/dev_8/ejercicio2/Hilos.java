public class Hilos extends Thread{

    public void run() {
        System.out.println("Comienza la comprobacion");
        //Creo el objeto Humedad y recojo la informacion
        int humedad;
        S_Humedad por_humedad = new S_Humedad();
        humedad = por_humedad.getporcentaje();
        //Creo el objeto Humedad y recojo la informacion
        int temperatura;
        S_Temperatura por_temperatura = new S_Temperatura();
        temperatura = por_temperatura.getporcentaje();
        //Creo el objeto Humedad y recojo la informacion
        int vida;
        S_Vida por_vida = new S_Vida();
        vida = por_vida.getporcentaje();

        //Muestro la informacion por pantalla
        System.out.println("La humedad = " + humedad);
        System.out.println("La temperatura = " + temperatura);
        System.out.println("El estado de la planta es = " + vida);

        System.out.println("Estos son los datos que muestran los sensores");
    }
}
