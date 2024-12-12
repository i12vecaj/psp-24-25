/**
Feedback JD: 12/12/2024

La estructura de entrega no es correcta: solo se pedía un único fichero. Lo avisé la semana pasada, que la estructura y requisitos debían ser correctos en el examen.

No obstante, te hago las apreciaciones pertinentes, respecto a tu solución:

Tu implementación de los hilos productor y consumidor es funcional y eficiente, con el uso adecuado de la sincronización y la lógica de los métodos wait() y notifyAll(). Sin embargo, el manejo de excepciones podría mejorarse, y el uso de un solo objeto Random para ambos hilos optimizaría el código.

Lo que mejorarías: el manejo de excepciones más claro y optimización en la creación de objetos Random.

**/

//con esta clase lo que hacemos es que el codigo compile  y tambien que los hilos funcionen
public class Main  extends Thread{
    public static void main(String[] args) {
        Clasebuffer buffer = new Clasebuffer(10);

        Claseproducctor productor = new Claseproducctor(buffer);
        Claseconsumidor consumidor = new Claseconsumidor(buffer);

        Thread hiloProductor = new Thread( productor);
        Thread hiloConsumidor = new Thread( consumidor);

        hiloProductor.start();
        hiloConsumidor.start();


            try {
                hiloConsumidor.join();
                hiloProductor.join();

            }catch (Exception e){
                System.out.println("este problema no esta dejando continuar el codigo"+e);
            }

    }
}
