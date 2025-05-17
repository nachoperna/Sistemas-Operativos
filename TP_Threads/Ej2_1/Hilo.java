public class Hilo extends Thread{
    private long id;
    
    public Hilo(){
        
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10000; i++) {
            this.id = Thread.currentThread().threadId();
            System.out.println("Hola Threads!. Soy el thread " + this.id +  " ejecutando por" + i + "vez");
        }
        // super.run();c
    }
}