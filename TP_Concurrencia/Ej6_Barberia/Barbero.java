package Ej6_Barberia;
public class Barbero extends Thread{
    private Barberia barberia;
    private boolean ocupado = false;
    private long tiempo_corte;

    public Barbero(long x){
        this.tiempo_corte = x;
    }

    public void setBarberia (Barberia b){
        this.barberia = b;
    }

    public boolean estaOcupado(){
        return ocupado;
    }

    public synchronized void cortarPelo(Cliente c){
        this.ocupado = true;
        System.out.println("Barbero le corta pelo a cliente " + c.threadId());
        try {
            Thread.sleep(tiempo_corte); // corta el pelo en x esgundos
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Barbero termina de cortarle el pelo a cliente " + c.threadId());
        this.ocupado = false;
        barberia.saleCliente();
    }
    
    @Override
    public void run() {
        while (true) {
            Cliente c = barberia.proximoCliente();
            cortarPelo(c); // sale del wait porque alguno cliente lo desperto
        }
    }
}
