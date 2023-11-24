package implementacion;

public class Nodo implements Comparable<Nodo>{
	public int u;
	public int c;

	public int redMin;

	public int redMax;

	public Nodo(int u, int c) {
		this.u = u;
		this.c = c;
	}

	@Override
	public int compareTo(Nodo o) {
		return Integer.compare(this.c, o.c);
	}
}
