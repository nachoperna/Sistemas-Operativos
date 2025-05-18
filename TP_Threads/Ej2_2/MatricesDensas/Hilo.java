package Ej2_2.MatricesDensas;

import edu.isistan.matrix.IMatrix;
import edu.isistan.matrix.mult.IMultiplication;

public class Hilo extends Thread implements IMultiplication{
    private IMatrix matriz1;
    private IMatrix matriz2;
    private IMatrix resultado;
    private int limiteinf;
    private int limitesup;

    public Hilo(IMatrix a, IMatrix b, IMatrix c, int x, int y){
        this.matriz1 = a;
        this.matriz2 = b;
        this.resultado = c;
        this.limiteinf = x;
        this.limitesup = y;
    }

    @Override
    public void run() {
        this.resultado = multiply(matriz2, matriz1);
    }

    @Override
    public IMatrix multiply(IMatrix a, IMatrix b) {
        if (a.getColumns()!=b.getRows())
			throw new RuntimeException("La cantidad de columnas de la matriz a tiene que ser igual a la cantidad de filas de la matriz b");
		
		//Por cada fila de A
		for (int i=limiteinf;i<limitesup;i++){
			//Por cada columna de B
			for (int j=0;j<b.getColumns();j++){
				//Realiza la multiplicación para la posición i j
				for (int k=0;k<b.getRows();k++)
					resultado.set(i, j, resultado.get(i, j)+
							a.get(i, k)*b.get(k, j));
			}
		}
        return resultado;
    }

    public IMatrix getResultado() {
        return resultado;
    }
}