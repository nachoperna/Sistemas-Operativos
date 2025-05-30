package Ej6_Barberia;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // System.out.printf("Tiempo de corte de pelo del barbero (en milisegundos): ");
        Barbero barbero = new Barbero(3000);
        Barberia barberia = new Barberia(barbero);
        barbero.setBarberia(barberia);
        // System.out.printf("Tiempo de entrada cliente nuevo a barberia (en milisegundos): ");
        // long x = sc.nextLong();
        // sc.close();
        
        barbero.start();

        Cliente p[] = new Cliente[30];
        for (int i = 0; i < p.length; i++) {
            p[i] = new Cliente(barberia);
            try {
                Thread.sleep(1000); // entra un cliente cada x segundos a la barberia
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
