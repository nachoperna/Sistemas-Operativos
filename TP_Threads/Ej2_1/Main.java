public class Main {
    public static void main(String[] args) {
        Hilo hilos[] = new Hilo[10];
        
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Hilo();
            hilos[i].start();
        }

        for (int i = 0; i < hilos.length; i++) {
            try {
                hilos[i].join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}