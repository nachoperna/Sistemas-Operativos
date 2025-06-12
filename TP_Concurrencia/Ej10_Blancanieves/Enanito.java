package Ej10_Blancanieves;

public class Enanito extends Thread{
    private Casa casa;
    private int numero;

    public Enanito(Casa c, int num){
        casa = c;
        numero = num;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public synchronized void esperar(){
        try {
            System.out.println("Enanito " + numero + " espera sentado a que le sirvan");
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void avisar(){
        System.out.println("Enanito " + getNumero() + " es avisado para comer");
        this.notify(); 
    }

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
            System.out.println("Enanito " + getNumero() + " comienza a comer");
            Thread.sleep(2000);
            System.out.println("Enanito " + getNumero() + " termina de comer");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            casa.ingresar(this);
            casa.avisar();
            esperar();
            comer();
            casa.salir(this);
            try {
                Thread.sleep(4000); // enanito vuelve a comer despues de 4 segundos
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
