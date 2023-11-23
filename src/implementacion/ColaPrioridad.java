package implementacion;

import interfaz.ColaPrioridadTDA;

public class ColaPrioridad implements ColaPrioridadTDA {
    private MaxHeap heap = new MaxHeap();
    @Override
    public void InicializarCola() {
        heap.InicializarHeap();
    }

    @Override
    public void AcolarPrioridad(int p, int x) {
        heap.InsertarElemento(p,x);
    }

    @Override
    public void Desacolar() {
        heap.ExtractMax();
    }

    @Override
    public int Primero() {
        return heap.Valor();
    }

    @Override
    public boolean ColaVacia() {
        return heap.HeapVacio();
    }

    @Override
    public int Prioridad() {
        return heap.Prioridad();
    }
}
