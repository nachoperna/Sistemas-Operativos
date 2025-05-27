package Ej6_Barberia;
public class Cliente extends Thread{
    private Barberia barberia;
    private long tiempo_entrada;

    public Cliente (Barberia b, long x){
        this.barberia = b;
        this.tiempo_entrada = x;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(tiempo_entrada); // entra un cliente cada aprox medio segundo a la barberia
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (barberia.hayLugar()){
            barberia.entraCliente(this);
            if (!barberia.barberOcupado()){
                try {
                    System.out.println("Barbero desocupado y cliente "+ this.threadId() + " pide corte");
                    barberia.despertarBarbero();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("Barbero ocupado");
            }
        }
        else{
            System.out.println("NO HAY LUGAR");
        }
    }
}
