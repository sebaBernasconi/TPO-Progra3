package interfaz;

public interface ConjuntoTDA {
    void InicializarConjunto();
    boolean ConjuntoVacio();
    void AgregarElemento(int x);
    int Elegir();
    void Sacar(int x);
    void SacarElemento(int x);
    boolean Pertenece(int x);
}
