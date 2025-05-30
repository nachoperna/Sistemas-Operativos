package Ej7_BDD;

import java.util.concurrent.Semaphore;

public class BaseDatos {
    private int registro;
    private int lectoresActivos = 0; // Contador de lectores activos
    private int escritoresEsperando = 0; // Contador de escritores esperando
    private Semaphore mutexLectores = new Semaphore(1); // Protege el contador de lectores
    private Semaphore sem_escritor = new Semaphore(1); // Exclusión mutua para escritores
    private Semaphore sem_lector = new Semaphore(1); // Bloquea nuevos lectores si hay escritores esperando

    public BaseDatos(int registroInicial) {
        registro = registroInicial;
    }

    public void getRegistro() {
        try {
            sem_lector.acquire(); // Bloquea si hay escritores esperando
            mutexLectores.acquire();
            lectoresActivos++;
            if (lectoresActivos == 1) {
                sem_escritor.acquire(); // Bloquea escritores si es el primer lector
            }
            mutexLectores.release();
            sem_lector.release();

            // Leer el registro
            System.out.println("Proceso " + Thread.currentThread().threadId() + " lee: " + registro);

            // Decrementar el contador de lectores
            mutexLectores.acquire();
            lectoresActivos--;
            if (lectoresActivos == 0) {
                sem_escritor.release(); // Permite a los escritores si no hay lectores activos
            }
            mutexLectores.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setRegistro() {
        try {
            mutexLectores.acquire();
            escritoresEsperando++; // Incrementa el contador de escritores esperando
            if (escritoresEsperando == 1) {
                sem_lector.acquire(); // Bloquea nuevos lectores
            }
            mutexLectores.release();

            sem_escritor.acquire(); // Exclusión mutua para escritores

            // Escribir en el registro
            System.out.println("OBTIENE LOCK");
            this.registro++;
            System.out.println("Proceso " + Thread.currentThread().threadId() + " escribe: " + registro);
            System.out.println("LIBERA LOCK");

            sem_escritor.release(); // Libera el acceso a otros escritores

            mutexLectores.acquire();
            escritoresEsperando--; // Decrementa el contador de escritores esperando
            if (escritoresEsperando == 0) {
                sem_lector.release(); // Permite nuevos lectores si no hay escritores esperando
            }
            mutexLectores.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
