package Ej8_Corrector;

public class Main {
    public static void main(String[] args) {
        int cant = 30;
        Corrector c[] = new Corrector[cant];
        Registro r = new Registro();

        for (int i = 0; i < cant; i++) {
            int x;
            if (i < 10){
                x = 0;
            } else if (i >= 10 && i < 20){
                x = 1;
            } else {
                x = 2;
            }
            c[i] = new Corrector(x, r);
            c[i].start();
        }
        
        
        for (int i = 0; i < cant; i++) {
            try {
                c[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
}