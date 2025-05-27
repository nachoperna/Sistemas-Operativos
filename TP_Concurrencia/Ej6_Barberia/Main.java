package Ej6_Barberia;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Tiempo de corte de pelo del barbero (en milisegundos): ");
        Barbero barbero = new Barbero(sc.nextLong());
        Barberia barberia = new Barberia(barbero);
        barbero.setBarberia(barberia);
        System.out.printf("Tiempo de entrada cliente nuevo a barberia (en milisegundos): ");
        long x = sc.nextLong();
        sc.close();
        
        try {
            barbero.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        while (true) {
            Cliente cliente = new Cliente(barberia, x);
            cliente.start();
            try {
                cliente.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
