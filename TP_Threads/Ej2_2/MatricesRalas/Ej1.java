package Ej2_2.MatricesRalas;

import java.util.Scanner;

import edu.isistan.matrix.IMatrix;
import edu.isistan.matrix.Utils;
import edu.isistan.matrix.mult.SimpleMultiplication;

public class Ej1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Ingrese tamaño de matriz rala: ");
        int size = sc.nextInt();
        IMatrix m1 = Utils.generateSparseSquareMatrix(size, 0.9);
        IMatrix m2 = Utils.generateSparseSquareMatrix(size, 0.9);
        IMatrix resultado = (new SimpleMultiplication()).multiply(m1, m2);
        long tiempo = Utils.measureTime(m1, m2, (new SimpleMultiplication()));
        float seg = (float) tiempo / 1000;
        System.out.println("Tiempo total estimado de la multiplicacion: " + seg + " segundos.");
        System.out.println();
        System.out.printf("Desea imprimir matriz resultado? (1.SI / 2.NO): ");
        if (sc.nextInt() == 1){
            printMatrix(resultado);
        }
        sc.close();
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
