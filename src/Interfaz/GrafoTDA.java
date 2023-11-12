package Interfaz;

public interface GrafoTDA {
    void InicializarGrafo();
    void AgregarVertice(int v);
    void EliminarVertice(int v);
    ConjuntoTDA Vertices();
    void AgregarArista(int v1, int v2, int peso);
    void EliminarArista(int v1, int v2);
    boolean ExisteArista(int v1, int v2);
    int PesoArista(int v1, int v2);
    GrafoTDA Dijkstra(GrafoTDA grafo, int partida);

}
