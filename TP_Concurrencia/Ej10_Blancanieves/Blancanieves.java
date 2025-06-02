package Ej10_Blancanieves;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Blancanieves extends Thread{
    private int sillas_totales = 4;
    private int sillas_ocupadas = 0;
    private boolean enanitoesperando = false;
    private ArrayList<Enanito> comensales = new ArrayList<>();
    private boolean ocupada = false;
    private Semaphore variables = new Semaphore(1);

    public synchronized boolean haySilla(){
        return (sillas_totales - sillas_ocupadas) > 0;
    }

    public void servir(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ocupada = true;
        System.out.println("Blancanieves se pone a servir la comida");
        Enanito e = comensales.getFirst();
        System.out.println("Enanito " + e.getNumero() + " es avisado para comer");
        e.avisar();
        System.out.println("Enanito " + e.getNumero() + " comienza a comer");
        e.comer();
        System.out.println("Enanito " + e.getNumero() + " termina de comer");
        try {
            variables.acquire();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        sillas_ocupadas--;
        comensales.removeFirst();
        if (comensales.size() == 0){
            System.out.println("No hay enanitos esperando para comer");
            enanitoesperando = false;
            ocupada = false;
        }
        variables.release();
    }

    public void ingresar(Enanito e){
        System.out.println("Enanito " + e.getNumero() + " entra a la casa");
        while (!haySilla()){
            e.volverDespues();
        }
        try {
            variables.acquire();
            comensales.add(e);
            enanitoesperando = true;
            sillas_ocupadas++;
            variables.release();
            synchronized(this) {
                System.out.println("Enanito " + e.getNumero() + " ocupa una silla");
                System.out.println("Sillas ocupadas: " + sillas_ocupadas);
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        if (!ocupada) {
            System.out.println("Blancanieves no esta ocupada");
            despertar();
        }
        else{
            System.out.println("Blancanieves ocupada");
            e.esperar();
        }
    }        

    public synchronized void dormir(){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void despertar(){ this.notify(); }

    @Override
    public void run() {
        while (true) {
            while (!enanitoesperando){
                System.out.println("No hay enanitos esperando y Blancanieves se va a pasear");
                dormir();
                System.out.println("Blancanieves es avisada que hay algun enanito esperando");
            }
            servir();
        }
    }
}
