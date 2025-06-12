package Ej11_ClubVino;

public class Miembro extends Thread{
    private int numero;
    private Club club;


    public Miembro(int x, Club c){
        numero = x;
        club = c;
    }

    public synchronized void esperar(){
        try {
            System.out.println("El miembro " + numero + " no obtuvo los ingredientes necesarios y se pone a esperar");
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void avisar(){
        notify();
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public void run() {
        while (true) {
            club.esperarIngredientes(this);
            try {
                Thread.sleep(5000); // tiempo de espera para volver a hacer vino y dejar lugar a otros
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
