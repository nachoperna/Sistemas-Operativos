package Ej7_BDD;

public class Proceso extends Thread{
    private BaseDatos bdd;

    public Proceso(BaseDatos b){
        this.bdd = b;
    }

    public void escribir(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bdd.setRegistro();
    }

    public void leer(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bdd.getRegistro();    
    }

    @Override
    public void run() {
        float x = (float) Math.random()*100; 
        if (x <= 20) { // 20% de procesos aproximadamente quiere escribir el registro
            escribir();
        } else {
            leer();
        }
    }
}
