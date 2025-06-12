package Ej11_ClubVino;

public class Main {
    public static void main(String[] args) {
        Miembro miembros[] = new Miembro[8];
        Administrador admin = new Administrador();
        Club club = new Club(admin);
        admin.agregarClub(club);
        admin.start();
        int x = 1;

        for (int i = 0; i < miembros.length; i++) {
            miembros[i] = new Miembro(x, club);
            club.agregarMiembro(miembros[i]);
            miembros[i].start();
            x++;
        }

        for (int i = 0; i < miembros.length; i++) {
            try {
                miembros[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
