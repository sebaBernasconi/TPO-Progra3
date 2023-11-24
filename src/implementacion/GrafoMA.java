package implementacion;

import interfaz.ConjuntoTDA;
import interfaz.GrafoTDA;

public class GrafoMA implements GrafoTDA {

	int[][] mAdy;
	int[] etiqs;
	int cant;
	int tam = 59;

	@Override
	public void InicializarGrafo() {
		mAdy = new int[tam][tam];
		etiqs = new int[tam];
		cant = 0;
	}

	@Override
	public void AgregarVertice(int v) {
		for(int i = 0; i <= cant; i++) {
			mAdy[i][cant] = 0;
			mAdy[cant][i] = 0;
		}
		etiqs[cant] = v;
		cant++;
	}

	@Override
	public void EliminarVertice(int v) {
		int inx = nodo2Indice(v);
		for(int j = 0; j < cant; j++)
			mAdy[inx][j] = mAdy[cant - 1][j];
		for(int i = 0; i < cant; i++)
			mAdy[i][inx] = mAdy[i][cant - 1];
		etiqs[inx] = etiqs[cant - 1];
		cant--;
	}

	@Override
	public ConjuntoTDA Vertices() {
		ConjuntoTDA c = new ConjuntoTA();
		c.InicializarConjunto();
		for(int i = 0; i < cant; i++)
			c.AgregarElemento(etiqs[i]);
		return c;
	}

	@Override
	public void AgregarArista(int o, int d, int p) {
		int i = nodo2Indice(o);
		int j = nodo2Indice(d);
		mAdy[i][j] = p;
	}

	@Override
	public void EliminarArista(int o, int d) {
		int i = nodo2Indice(o);
		int j = nodo2Indice(d);
		mAdy[i][j] = 0;
	}

	@Override
	public boolean ExisteArista(int o, int d) {
		int i = nodo2Indice(o);
		int j = nodo2Indice(d);
		return (mAdy[i][j] != 0);
	}

	@Override
	public int PesoArista(int o, int d) {
		int i = nodo2Indice(o);
		int j = nodo2Indice(d);
		return mAdy[i][j];
	}
	
	private int nodo2Indice(int v) {
		int i = cant - 1;
		while(i >= 0 && etiqs[i] != v)
			i--;
		return i;
	}

	public int[][] getmAdy() {
		return mAdy;
	}
}
