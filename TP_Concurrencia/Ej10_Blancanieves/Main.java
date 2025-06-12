package Ej10_Blancanieves;

public class Main {
    public static void main(String[] args) {
        Blancanieves blanca = new Blancanieves();
        Casa casa = new Casa(blanca);
        blanca.agregarCasa(casa);
        Enanito enano[] = new Enanito[7];
        
        blanca.start();

        int x = 1;

        for (int i = 0; i < enano.length; i++) {
            enano[i] = new Enanito(casa, x);
            enano[i].start();
            x++;
        }

        for (int i = 0; i < enano.length; i++) {
            try {
                enano[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            blanca.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
