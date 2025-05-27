package Ej6_Barberia;
import java.util.ArrayList;

public class Barberia {
    private int sillas_totales = 10;
    private int sillas_ocupadas = 0;
    private Barbero barbero;
    private ArrayList<Cliente> clientes = new ArrayList<>();

    public Barberia (Barbero b){
        this.barbero = b;
    }

    public boolean hayLugar(){
        return (sillas_totales - sillas_ocupadas) != 0;
    }

    public synchronized void entraCliente(Cliente c){
        this.sillas_ocupadas++;
        clientes.add(c);
        System.out.println("Cliente " + c.threadId() + " entra a la barberia");
        System.out.println("Hay " + sillas_ocupadas + " sillas ocupadas");
    }

    public synchronized void saleCliente(){
        this.sillas_ocupadas--;
        System.out.println("Cliente " + clientes.removeFirst().threadId() + " sale de la barberia");
        System.out.println("Hay " + sillas_ocupadas + " sillas ocupadas");
    }

    public boolean barberOcupado(){
        return barbero.estaOcupado();
    }

    public Cliente proximoCliente(){
        if (clientes.isEmpty()){
            return null;
        }
        return clientes.getFirst();
    }

    public synchronized void dormirse(){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void despertarBarbero(){
        notify();
    }
}