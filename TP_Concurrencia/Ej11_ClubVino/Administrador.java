package Ej11_ClubVino;

public class Administrador extends Thread{
    Club club;

    public void agregarClub(Club c){
        club = c;
    }

    public synchronized void esperar(){
        System.out.println("Administrador se pone a descansar");
        try {
            wait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void avisar(){
        System.out.println("Administrador es avisado que quizas hay cosas para reponer");
        notify();
    }

    @Override
    public void run() {
        while(true){
            esperar();
            club.reponerIngredientes();
        }
    }
}
