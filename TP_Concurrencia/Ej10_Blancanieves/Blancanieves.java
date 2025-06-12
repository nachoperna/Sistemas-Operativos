package Ej10_Blancanieves;

public class Blancanieves extends Thread{
    private boolean ocupada = false;
    private Casa casa;

    public void agregarCasa(Casa c){
        casa = c;
    }

    public boolean estaOcupada(){
        return ocupada;
    }

    public synchronized void servir(Enanito comensal){
        System.out.println("Blancanieves se pone a servir la comida");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        comensal.avisar();
    }      

    public void ocuparse(){
        ocupada = true;
    }

    public void desocuparse(){
        ocupada = false;
    }

    @Override
    public void run() {
        while (true) {
            Enanito e = casa.siguiente();
            servir(e);
        }
    }
}
