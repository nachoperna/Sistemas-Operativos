package Ej10_Blancanieves;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Casa {
    private int sillas_totales = 4;
    private int sillas_ocupadas = 0;
    private ArrayList<Enanito> comensales = new ArrayList<>();
    private Semaphore sem_variables = new Semaphore(1);
    private Blancanieves blanca;

    public Casa(Blancanieves b){
        blanca = b;
    }

    public synchronized boolean haySilla(){
        return (sillas_totales - sillas_ocupadas) > 0;
    }

    public synchronized Enanito siguiente(){
        while (comensales.isEmpty()){
            System.out.println("Blancanieves desocupada y se va a pasear");
            try {
                blanca.desocuparse();
                this.wait(); // blancanieves se duerme sobre el monitor de la casa
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        blanca.ocuparse();
        return comensales.removeFirst();
    }

    public void ingresar(Enanito e){
        System.out.println("Enanito " + e.getNumero() + " entra a la casa");
        while (!haySilla()){
            e.volverDespues();
        }
        try {
            sem_variables.acquire();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        sillas_ocupadas++;
        comensales.add(e);
        sem_variables.release();
        System.out.println("Enanito " + e.getNumero() + " ocupa una silla");
        System.out.println("Sillas ocupadas: " + sillas_ocupadas);
    }
    
    public synchronized void avisar(){ 
        if (!blanca.estaOcupada()){
            System.out.println("Blancanieves es avisada que hay enanitos esperando para comer");
            this.notify(); // se le avisa a blancanieves que se durmio sobre el monitor de la casa
        }
        else{
            System.out.println("Blancanieves ocupada");
        }
    }

    public void salir(Enanito e){
        System.out.println("Enanito " + e.getNumero() + " sale de la casa");
        sillas_ocupadas--;
        System.out.println("Sillas ocupadas: " + sillas_ocupadas);            
    }
}
