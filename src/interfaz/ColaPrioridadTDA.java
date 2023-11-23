package interfaz;

public interface ColaPrioridadTDA {

    void InicializarCola();
    void AcolarPrioridad(int p, int x);
    void Desacolar();
    int Primero();
    boolean ColaVacia();
    int Prioridad();
}
