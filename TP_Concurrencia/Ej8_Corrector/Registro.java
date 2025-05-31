package Ej8_Corrector;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Registro{
    private ArrayList<String> palabras = new ArrayList<>();
    private int suma_palabras = 0;
    private int correctores_esperando = 0;
    private Semaphore sem_cargadores = new Semaphore(1);
    private Semaphore sem_variables = new Semaphore(1);
    private boolean carga_ejecutandose = false;

    public Registro(){}

    public synchronized boolean cargaEjecuandose(Corrector c){
        if (c.getTipo() > 0){
            return false;
        }
        return carga_ejecutandose;
    }

    public synchronized void dormir(){
        try {
            System.out.println("Se durmio hilo " + Thread.currentThread().threadId());
            wait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lock(){
        try {
            sem_cargadores.acquire();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void unlock(){
        sem_cargadores.release();
    }

    public synchronized void despertar(){
        notify();
    }

    public void ingresa(Corrector c){
        try {
            sem_variables.acquire();
        } catch (Exception e) {
            // TODO: handle exception
        }
        correctores_esperando++;
        boolean primero = (correctores_esperando == 1);
        System.out.println("Correctores esperando: "+correctores_esperando);
        sem_variables.release();

        if (primero){
            c.trabajar(); // si esta solo en el registro o es eliminador o verificador que labure directo
        }
        else if (c.getTipo() > 0){
            System.out.println("mas de un corrector esperando pero es eliminador o verificador");
            c.trabajar();
        }
        else if (c.getTipo() == 0){
            System.out.println("mas de un corrector esperando y es cargador");
            c.trabajar();
        }
    }

    public void sale(Corrector c){
        try {
            sem_variables.acquire();
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("Sale hilo "+Thread.currentThread().threadId());
        correctores_esperando--;
        System.out.println("Correctores esperando: "+correctores_esperando);
        sem_variables.release();
        if (c.getTipo() == 0){
            despertar(); // como solo los cargadores se duermen cuando uno termina despierta a otro
        }
    }

    public synchronized void cargar(int c){
        carga_ejecutandose = true;
        ArrayList<String> aux = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            suma_palabras++;
            String s = "pal"+Integer.toString(suma_palabras);
            aux.add(s);
        }
        try {
            sem_variables.acquire();
            palabras.addAll(aux);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sem_variables.release();
        System.out.println("Hilo " + Thread.currentThread().threadId() + " carga: " + aux);
        carga_ejecutandose = false;
        despertar();
    }

    public void eliminar(ArrayList<String> a_eliminar){
        try {
            sem_variables.acquire();
            for (String s : a_eliminar) {
                if (palabras.contains(s)){
                    palabras.remove(s);
                }
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Palabras luego de que " + Thread.currentThread().threadId() + " eliminara: " + palabras);
        sem_variables.release();
        despertar();
    }

    public void verificar(ArrayList<String> a_verificar){
        ArrayList<String> aux = new ArrayList<>();
        try {
            sem_variables.acquire();
            for (String pal : a_verificar) {
                if (palabras.contains(pal)){
                    aux.add(pal);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sem_variables.release();
        a_verificar.removeAll(aux);
        System.out.println("Palabras que no estan en el diccionario verificado por el hilo " + Thread.currentThread().threadId() + ": " + a_verificar);
    }

    public void mostrarRegistro(){
        System.out.println(palabras);
    }
    
    public synchronized void agregaCorrector(){
        correctores_esperando++;
    }

    public synchronized void eliminaCorrector(){
        correctores_esperando--;
    }

}
