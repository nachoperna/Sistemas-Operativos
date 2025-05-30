package Ej6_Barberia;
public class Cliente extends Thread{
    private Barberia barberia;

    public Cliente (Barberia b){
        this.barberia = b;
    }

    @Override
    public void run() {
        barberia.entraCliente(this);
    }
}
