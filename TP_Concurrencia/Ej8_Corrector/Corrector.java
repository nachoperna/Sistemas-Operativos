package Ej8_Corrector;

import java.util.ArrayList;

public class Corrector extends Thread{
    private int x;
    private Registro registro;

    public Corrector(int x, Registro registro){
        this.x = x;
        this.registro = registro;
    }

    public void trabajar(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (x == 0) {
            System.out.println("Se pone a trabajar el hilo cargador " + Thread.currentThread().threadId());
            registro.cargar(3);
        } else if (x == 1) {
            System.out.println("Se pone a trabajar el hilo elmiinador " + Thread.currentThread().threadId());
            int c = (int)Math.random()*5+1;
            ArrayList<String> aux = new ArrayList<>();
            for (int i = 0; i < c; i++) {
                String s = "pal"+Integer.toString((int)(Math.random()*30)+1);
                aux.add(s);
            }
            System.out.println("Hilo " + Thread.currentThread().threadId() + " quiere eliminar: " + aux);
            registro.eliminar(aux);
        } else{
            System.out.println("Se pone a trabajar el hilo verificador " + Thread.currentThread().threadId());
            int c = (int)Math.random()*5+1;
            ArrayList<String> aux = new ArrayList<>();
            for (int i = 0; i < c; i++) {
                String s = "pal"+Integer.toString((int)(Math.random()*30)+1);
                aux.add(s);
            }
            System.out.println("Hilo " + Thread.currentThread().threadId() + " quiere verificar: " + aux);
            registro.verificar(aux);
        }
        registro.sale(this);
    }

    public int getTipo(){return x;}

    @Override
    public void run() {
        while (registro.cargaEjecuandose(this)){
            try {
                registro.dormir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        registro.ingresa(this);
    }
}
