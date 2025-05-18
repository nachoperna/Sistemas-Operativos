package Ej2_2.MatricesDensas;
import edu.isistan.matrix.IMatrix;
import edu.isistan.matrix.Utils;
import edu.isistan.matrix.mult.IMultiplication;
import edu.isistan.matrix.mult.SimpleMultiplication;

public class Ej1 {
    public static void main(String[] args) {
        IMultiplication mult = new SimpleMultiplication();
        boolean encuentra = false;
        int i = 500;
        float prom = 0;
        while (!encuentra) {
            System.out.println("i="+i);
            IMatrix matriz1 = Utils.generateDenseSquareMatrix(i);
            System.out.println("Se genero matriz1");
            IMatrix matriz2 = Utils.generateDenseSquareMatrix(i);
            System.out.println("Se genero matriz2");
            long suma = 0;
            for (int j = 0; j < 10; j++) {
                suma += Utils.measureTime(matriz1, matriz2, mult);
                System.out.println("Se midio "+(j+1)+" veces");
            }
            float promedio = (float) suma/10;
            float t = (float) promedio/1000;
            t = (float) (Math.round(t * 100) / 100.0);
            System.out.println("t="+t);
            if (t >= 30 && t <= 60){
                encuentra = true;
                prom = t;
            }
            else{
                i *= 1.2;
            }
        }
        System.out.println("i: " + i + " - Promedio(t): " + prom + "s");
    }
}
