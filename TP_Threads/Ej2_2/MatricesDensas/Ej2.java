package Ej2_2.MatricesDensas;

import java.util.Scanner;

import edu.isistan.matrix.IMatrix;
import edu.isistan.matrix.Utils;
import edu.isistan.matrix.mult.SimpleMultiplication;

public class Ej2 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Numero de filas y columnas de la Matriz Densa: ");
        int a = sc.nextInt();
        sc.close();
        IMatrix matriz1 = Utils.generateDenseSquareMatrix(a);
        IMatrix matriz2 = Utils.generateDenseSquareMatrix(a);
        IMatrix resultado = matriz1.createMatrix(a, a);

        int filasxhilo = (int) a/10;

        Hilo hilos[] = new Hilo[10];
        
        int x = 0;
        int y = filasxhilo;
        for (int j = 0; j < hilos.length; j++) {
            hilos[j] = new Hilo(matriz1, matriz2, resultado, x, y);
            x += filasxhilo;
            y += filasxhilo;
        }

        for (int i = 0; i < hilos.length; i++) {
            hilos[i].start();
        }
        try {
            for (int i = 0; i < hilos.length; i++) {
                hilos[i].join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        IMatrix resultado_secuencial = (new SimpleMultiplication()).multiply(matriz1, matriz2);
        System.out.println("Matrices iguales: " + Utils.equalMatrices(resultado, resultado_secuencial));
        printMatrix(resultado_secuencial);
        System.out.println();
        System.out.println();
        System.out.println();
        printMatrix(resultado);
    }

    public static void printMatrix(IMatrix m) {
        for (int i = 0; i < m.getRows(); i++) {
            for (int j = 0; j < m.getColumns(); j++) {
                System.out.printf("%.2f\t", m.get(i, j));
            }
            System.out.println();
        }
    }
    
}
