import java.util.Random;

class hiloTemperatura extends Thread {
    @Override
    public void run() {
        Random random = new Random();
        try {
            int delay = random.nextInt(3000 - 1000) + 1000;
            Thread.sleep(delay);
            int randomNumber = random.nextInt(100);
            System.out.println("Temperatura: " + randomNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
