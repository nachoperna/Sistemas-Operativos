package Ej7_BDD;

public class Main {
    public static void main(String[] args) {
        BaseDatos bdd = new BaseDatos(1);
        Proceso p[] = new Proceso[500];
        for (int i = 0; i < p.length; i++) {
            p[i] = new Proceso(bdd);
            p[i].start();
        }
        for (int i = 0; i < p.length; i++) {
            try {
                p[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
