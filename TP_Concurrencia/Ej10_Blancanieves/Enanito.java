package Ej10_Blancanieves;

public class Enanito extends Thread{
    Blancanieves blancanieves;
    private int numero;

    public Enanito(Blancanieves b, int num){
        blancanieves = b;
        numero = num;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public synchronized void esperar(){
        try {
            System.out.println("Enanito " + numero + " espera sentado porque Blancanieves esta ocupada");
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void avisar(){ this.notify(); }

    public void volverDespues(){
        System.out.println("Enanito " + numero + " sale a pasear porque no hay lugar");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void comer(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            blancanieves.ingresar(this);
        }
    }
}
