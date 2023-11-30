package implementacion;

import java.util.ArrayList;
import java.util.List;

public class Nodo implements Comparable<Nodo>{

	public int[] x;
	public int u;
	public int c;

	public int redMin;

	public int redMax;

	public Nodo(int[] x, int u, int c) {
		this.x = x;
		this.u = u;
		this.c = c;
	}

	@Override
	public int compareTo(Nodo o) {
		return Integer.compare(this.c, o.c);
	}
}
