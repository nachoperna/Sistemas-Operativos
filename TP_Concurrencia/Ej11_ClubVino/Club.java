package Ej11_ClubVino;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Club {
    // Almacen
    private int estaciones_mezcla = 2;
    private int jarras = 6;
    private int unidad_fermentacion = 7;
    private int envase_jugo_fruta = 15;
    private int paquete_levadura = 20;
    private Semaphore sem = new Semaphore(1);
    private ArrayList<Miembro> miembros = new ArrayList<>();
    private Administrador administrador;

    public Club(Administrador admin) {
        administrador = admin;
    }

    public void agregarMiembro(Miembro m){
        miembros.add(m);
    }

    public synchronized boolean requerimientos(){
        return ((estaciones_mezcla >= 1)
                && (jarras >= 2)
                && (unidad_fermentacion >= 1)
                && (envase_jugo_fruta >= 1)
                && (paquete_levadura >= 1));
    }

    public void hacerVino(Miembro m){
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Agarro lo necesario
        System.out.println("El miembro " + m.getNumero() + " entra al almacen para agarrar ingredientes");
        estaciones_mezcla--;
        jarras -= 2;
        unidad_fermentacion--;
        envase_jugo_fruta--;
        paquete_levadura--;
        sem.release();
        administrador.avisar();
        System.out.println("El miembro " + m.getNumero() + " sale del almacen y comienza a preparar vino");
        // Se espera 4 semanas (segundos) para producir el vino
        try {
            Thread.sleep(4000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("El miembro " + m.getNumero() + " termina de preparar el vino");

        probarVino(m);
    }

    public synchronized void reponerIngredientes(){
        System.out.println("Administrador entra a reponer ingredientes");
        if (estaciones_mezcla < 1){
            estaciones_mezcla = 2;
        }
        if (jarras < 6){
            jarras = 6;
        }
        if (unidad_fermentacion < 7){
            unidad_fermentacion = 7;
        }
        if (envase_jugo_fruta < 15){
            envase_jugo_fruta = 15;
        }
        if (paquete_levadura < 20){
            paquete_levadura = 20;
        }
        System.out.println("Administrador termina de reponer ingredientes");
        notifyAll(); // administrador le avisa a todos los que estan esperando por requerimientos que ya hizo reposicion
    }

    public synchronized void probarVino(Miembro c){
        System.out.println("El miembro " + c.getNumero() + " le da de probar a todos los miembros");
        for (Miembro m : miembros) {
            int opinion = (int)Math.random()*2+1;
            if (opinion == 1){
                System.out.println("Al miembro " +  m.getNumero() + " le gusto el vino");
            }
            else{
                System.out.println("Al miembro " +  m.getNumero() + " no le gusto el vino");
            }
        }
        System.out.println("Todos los miembros volvieron a su rutina");
    }

    public synchronized void esperarIngredientes(Miembro m){
        while (!requerimientos()) {
            try {
                System.out.println("El miembro " + m.getNumero() + " no obtuvo los ingredientes necesarios y se pone a esperar");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        hacerVino(m);
    }
}
