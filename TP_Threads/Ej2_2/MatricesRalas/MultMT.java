package Ej2_2.MatricesRalas;

import edu.isistan.matrix.IMatrix;
import edu.isistan.matrix.Utils;
import edu.isistan.matrix.mult.IMultiplication;

public class MultMT extends Thread implements IMultiplication{
    private IMatrix m1;
    private IMatrix m2;
    private IMatrix resultado;
    private int tamanio;
    private double spar; 
    private int limiteinf;
    private int limitesup;

    public MultMT(IMatrix m1, IMatrix m2, int t, double spar, int x, int y){
        this.m1 = m1;
        this.m2 = m2;
        resultado = Utils.generateSparseSquareMatrix(t, spar);
        tamanio = t;
        this.spar = spar;
        limiteinf = x;
        limitesup = y;
    }

    @Override
    public IMatrix multiply(IMatrix a, IMatrix b) {
        MultMT hilos[];
        int filasxhilo = 0;
        if (tamanio < 10){ // si la matriz es menor de 10x10 se genera un hilo por fila
            hilos = new MultMT[tamanio];
            filasxhilo = 1;
        }
        else{ // si la matriz es mayor a 10x10 se generan 10 hilos
            filasxhilo = (int) tamanio / 10;
            hilos = new MultMT[10];
        }
        
        limitesup = filasxhilo;

        for (MultMT hilo : hilos) {
            hilo = new MultMT(a, b, tamanio, spar, limiteinf, limitesup);
            hilo.start();
            limiteinf = limitesup;
            limitesup += filasxhilo;
        }
        try {
            for (int i = 0; i < hilos.length; i++) {
                hilos[i] = new MultMT(a, b, tamanio, spar, limiteinf, limitesup);                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }
    
    @Override
    public void run() {
        if (m1.getColumns()!=m2.getRows())
			throw new RuntimeException("La cantidad de columnas de la matriz a tiene que ser igual a la cantidad de filas de la matriz b");

		//Por cada fila de A
		for (int i=limiteinf;i<limitesup;i++){
			//Por cada columna de B
			for (int j=0;j<m2.getColumns();j++){
				//Realiza la multiplicación para la posición i j
				for (int k=0;k<m2.getRows();k++)
					resultado.set(i, j, resultado.get(i, j)+
							m1.get(i, k)*m2.get(k, j));
			}
		}
    }

    public IMatrix getResultado() {
        return resultado;
    }
}

