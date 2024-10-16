class SensorHumedad {

    public void run() {
        while (true) {
            
            double humedad = obtenerHumedad();
           
            System.out.println("Humedad: " + humedad);
            
            try {
                Thread.sleep(4000);                // al final encontre como ahcer que se espere uno se espera un segundo y este 4 :)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private double obtenerHumedad() {
        return Math.random() * 10000;        // Cone sto podria generar una Huemdad aleatoria
    }
}