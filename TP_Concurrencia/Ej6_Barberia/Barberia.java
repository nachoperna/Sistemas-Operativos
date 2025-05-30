package Ej6_Barberia;
import java.util.ArrayList;

public class Barberia {
    private int sillas_totales = 5;
    private int sillas_ocupadas = 0;
    private Barbero barbero;
    private ArrayList<Cliente> clientes = new ArrayList<>();

    public Barberia (Barbero b){
        this.barbero = b;
    }

    public synchronized boolean hayLugar(){
        return (sillas_totales - sillas_ocupadas) > 0;
    }

    public synchronized void entraCliente(Cliente c){
        if (hayLugar()){
            if (barberOcupado()){
                System.out.println("Barbero ocupado");
                sillas_ocupadas++;
                clientes.add(c);
                System.out.println("Cliente " + c.threadId() + " entra a la barberia y espera");
                System.out.println("Hay " + sillas_ocupadas + " sillas ocupadas");
            }
            else{
                System.out.println("Barbero desocupado");
                System.out.println("Cliente " + c.threadId() + " entra a la barberia y es atendido");
                sillas_ocupadas++;
                clientes.add(c);
                despertarBarbero();
            }
        }
        else{
            System.out.println("No hay lugar para cliente " + c.threadId() + " y se retira.");
        }
    }

    public synchronized void saleCliente(){
        sillas_ocupadas--;
        Cliente c = clientes.removeFirst();
        System.out.println("Cliente " + c.threadId() + " sale de la barberia");
        System.out.println("Hay " + sillas_ocupadas + " sillas ocupadas");
    }

    public boolean barberOcupado(){
        return barbero.estaOcupado();
    }

    public synchronized Cliente proximoCliente(){
        while (clientes.isEmpty()){
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
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