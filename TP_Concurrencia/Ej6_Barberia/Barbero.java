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

    public synchronized void cortarPelo(long id){
        this.ocupado = true;
        System.out.println("Barbero le corta pelo a cliente " + id);
        try {
            Thread.sleep(tiempo_corte); // corta el pelo en 3 segundos es una maquina
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Barbero termina de cortarle el pelo a cliente " + id);
        // tendriamos problemas de concurrencia en este punto si el scheduler cambia a un hilo cliente que recibe informacion de que el barbero esta ocupado cuando en realidad termino de cortar el pelo 
        // (por eso usamos synchronized)
        this.ocupado = false;
    }

    @Override
    public void run() {
        while(true){
            Cliente c = barberia.proximoCliente();
            if (c == null){
                try {
                    System.out.println("Barbero se pone a dormir");
                    barberia.dormirse(); // si no tiene cliente para cortar se duerme una siesta
                    System.out.println("Barbero se despierta");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                cortarPelo(c.threadId()); // sale del wait porque alguno cliente lo desperto
                barberia.saleCliente();
            }
        }
    }
}
