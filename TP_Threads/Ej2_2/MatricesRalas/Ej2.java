package Ej2_2.MatricesRalas;

import java.util.Scanner;

import edu.isistan.matrix.IMatrix;
import edu.isistan.matrix.Utils;

public class Ej2 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Tamaño de la Matriz Rala: ");
        int t = sc.nextInt();
        double spar = 0.1;
        IMatrix m1 = Utils.generateSparseSquareMatrix(t, spar);
        IMatrix m2 = Utils.generateSparseSquareMatrix(t, spar);
        printMatrix(m1);
        System.out.println();
        System.out.println();
        printMatrix(m2);
        MultMT mm = new MultMT(m1, m2, t, spar, 0,0);
        System.out.println("Verificacion: " + Utils.verifyMultiplication(m1, m2, mm, 10));
        System.out.printf("Desea imprimir matriz resultado? (1.SI / 2.NO): ");
        if (sc.nextInt() == 1){
            printMatrix(mm.getResultado());
        }
        sc.close();
    }
    
    public static void printMatrix(IMatrix m) {
        for (int i = 0; i < m.getRows(); i++) {
            for (int j = 0; j < m.getColumns(); j++) {
                System.out.printf("%.3f\t", m.get(i, j));
            }
            System.out.println();
        }
    }
}
