package Implementacion;

import Interfaz.ConjuntoTDA;
import Interfaz.GrafoTDA;

public class GrafoLA implements GrafoTDA {

    class NodoGrafo{
        int nodo;
        NodoArista arista;
        NodoGrafo sigNodo;
    }

    class NodoArista{
        int etiqueta;
        NodoGrafo nodoDestino;
        NodoArista sigArista;
    }

    NodoGrafo origen;

    public void InicializarGrafo() {
        origen = null;
    }

    public void AgregarVertice(int v) {
        // El vertice se inserta al inicio de la lista de nodos
        NodoGrafo aux = new NodoGrafo() ;
        aux . nodo = v;
        aux . arista = null ;
        aux . sigNodo = origen;
        origen = aux ;
    }

    public void EliminarVertice(int v) {
        // Se recorre la lista de v´ertices para remover el nodo v
        //y las aristas con este v´ertice.
        // Distingue el caso que sea el primer nodo
        if( origen. nodo == v) {
            origen = origen.sigNodo;
        }
        NodoGrafo aux = origen;
        while (aux!= null){
            // remueve de aux todas las aristas hacia v
            this.EliminarAristaNodo(aux,v);
            if( aux.sigNodo!= null && aux.sigNodo.nodo == v) {
                // Si el siguiente nodo de aux es v , lo elimina
                aux.sigNodo = aux.sigNodo.sigNodo;
            }
            aux = aux.sigNodo;
        }

    }
    private void EliminarAristaNodo(NodoGrafo nodo, int v) {
        NodoArista aux = nodo.arista;
        if( aux != null) {
            // Si la arista a eliminar es la primera en
            // la lista de nodos adyacentes
            if( aux.nodoDestino.nodo == v){
                nodo.arista = aux.sigArista;
            }
            else {
                while ( aux.sigArista!= null && aux.sigArista.nodoDestino.nodo != v ){
                    aux = aux.sigArista;
                }
                if( aux.sigArista!= null ) {
                    // Quita la referencia a la arista hacia v
                    aux.sigArista = aux.sigArista.sigArista;
                }
            }
        }


    }

    public ConjuntoTDA Vertices() {
        ConjuntoTDA c = new ConjuntoTA() ;
        c.InicializarConjunto() ;

        NodoGrafo aux = origen;
        while ( aux != null){
            c.AgregarElemento( aux.nodo ) ;
            aux = aux.sigNodo;
        }
        return c;
    }


    public void AgregarArista(int v1, int v2, int peso) {
        NodoGrafo n1 = Vert2Nodo(v1);
        NodoGrafo n2 = Vert2Nodo(v2);
        // La nueva arista se inserta al inicio de la lista
        // de nodos adyacentes del nodo origen
        NodoArista aux = new NodoArista ();
        aux.etiqueta = peso ;
        aux.nodoDestino = n2;
        aux.sigArista = n1.arista;
        n1.arista = aux ;
    }

    private NodoGrafo Vert2Nodo(int v) {
        NodoGrafo aux = origen;
        while ( aux != null && aux . nodo != v){
            aux = aux . sigNodo;
        }
        return aux ;
    }


    public void EliminarArista(int v1, int v2) {
        NodoGrafo n1 = Vert2Nodo( v1);
        EliminarAristaNodo (n1 , v2 );

    }

    public boolean ExisteArista(int v1, int v2) {
        NodoGrafo n1 = Vert2Nodo( v1);
        NodoArista aux = n1.arista;
        while ( aux != null && aux.nodoDestino.nodo != v2 ){
            aux = aux.sigArista;
        }
        // Solo si se encontro la arista buscada , aux no es null
        return aux != null;
    }

    public int PesoArista(int v1, int v2) {
        NodoGrafo n1 = Vert2Nodo( v1);
        NodoArista aux = n1 . arista;
        while( aux . nodoDestino. nodo != v2){
            aux = aux . sigArista;
        }
        // Se encontr´o la arista entre los dos nodos
        return aux . etiqueta;
    }

    public  GrafoTDA Dijkstra(GrafoTDA grafo, int partida) {
        int verticeActual;
        int verticeAux;
        int verticeMenorPeso;
        int menorPeso;

        //creamos el grafo que vamos a devolver y agregamos el vertice que va a
        //el punto de partida. Ademas creamos un conjunto donde agregamos todos los
        //vertices del grafo original y sacamos el punto de partida
        GrafoTDA grafoAraña = new GrafoLA();
        grafoAraña.InicializarGrafo();
        grafoAraña.AgregarVertice(partida);

        ConjuntoTDA verticesGrafoOriginal = grafo.Vertices();
        verticesGrafoOriginal.SacarElemento(partida);

        //elegimos uno de los vertices del conjunto de vertices del grafo original
        // y lo sacamos del conjunto hasta que se vacie. Ademas lo agregamos al grafo que vamos a devolver y
        //preguntamos si existe una arista directa entre el punto de partida y el vertice
        //que sacamos del conjunt para usarla para comparar
        while (!verticesGrafoOriginal.ConjuntoVacio()) {
            verticeActual = verticesGrafoOriginal.Elegir();
            verticesGrafoOriginal.SacarElemento(verticeActual);
            grafoAraña.AgregarVertice(verticeActual);
            if (grafo.ExisteArista(partida, verticeActual)) {
                grafoAraña.AgregarArista(partida, verticeActual, grafo.PesoArista(partida, verticeActual));
            }
        }
        //volvemos a llenar el conjunto de vertices del grafo original para
        //para empezar a buscar los caminos mas cortos desde el punto de partida.
        //Ademas creamos un conjunto con los que ya visitamos que en este caso serian
        //los vertices para los que ya se encontro el camino mas corto desde el punto de partida
        ConjuntoTDA VerticeGrafoOriginal = grafo.Vertices();
        VerticeGrafoOriginal.SacarElemento(partida);
        ConjuntoTDA verticesVisitados = new ConjuntoTA();
        verticesVisitados.InicializarConjunto();


        while (!VerticeGrafoOriginal.ConjuntoVacio()) {

            menorPeso = 0;
            verticeMenorPeso = 0;
            while (!VerticeGrafoOriginal.ConjuntoVacio()) {

                //en este while lo que hacemos es elegir un vertice y
                //agregarlo al conj de visitados. Ademas preguntamos si
                //existe una arista directa desde el punto de partida y el vertice que eligio
                //y ademas si el camino mas corto vale 0 o si el camino mas corto es mayor
                //al del peso de la arista entre el punto de partida y el vertice actual.
                //en caso de que las condiciones se cumplan se modifica el peso de la arista
                //entre el punto de partida y el vertice que se eligio y se asigna el vertice a
                //la variable que contiene al vertice con el menor peso

                verticeAux = VerticeGrafoOriginal.Elegir();
                VerticeGrafoOriginal.SacarElemento(verticeAux);
                verticesVisitados.AgregarElemento(verticeAux);
                if ((grafoAraña.ExisteArista(partida , verticeAux)) && (menorPeso == 0 || (menorPeso > grafoAraña.PesoArista(partida, verticeAux)))) {
                    menorPeso = grafoAraña.PesoArista(partida, verticeAux);
                    verticeMenorPeso = verticeAux;
                }
            }

            if (verticeMenorPeso != 0) {
                verticesVisitados.SacarElemento(verticeMenorPeso);

                while (!verticesVisitados.ConjuntoVacio()) {
                    verticeAux = verticesVisitados.Elegir();
                    verticesVisitados.SacarElemento(verticeAux);
                    VerticeGrafoOriginal.AgregarElemento(verticeAux);

                    //elegimos el vertice del grafo y preguntamos si hay una arista entre
                    //el vertcie con menor peso y el vertice actual en el grafo original. si esto se cumple
                    //pregunta si no hay una arista entre el punto de partida y el vertice actual en el grafo que devolvemos
                    // si esto tambien se cumple agrega una arista al grafo que vamos a devolver
                    //entre el punto de partida y el vertice actual y le pone el peso que hay desde
                    //el punto de partida al vertice de menor peso + el peso que hay desde el vertice de menor peso
                    //hasta el vertice actual.


                    //si hay una arista entre el punto de partida y el vertice actual en el grafo que devolvemos(osea que ya esta visitada)
                    //, pasa por el else que compara si el peso que actualmente es el menor > a otro de los posibles caminos y si lo es lo pisa
                    //con el que ahora seria el mas corto.

                    if (grafo.ExisteArista(verticeMenorPeso, verticeAux)) {
                        if (!grafoAraña.ExisteArista(partida, verticeAux)) {
                            grafoAraña.AgregarArista(partida, verticeAux, grafoAraña.PesoArista(partida, verticeMenorPeso)+grafo.PesoArista(verticeMenorPeso, verticeAux));
                        }
                        else {
                            if (grafoAraña.PesoArista(partida, verticeAux) > grafoAraña.PesoArista(partida, verticeMenorPeso)+grafo.PesoArista(verticeMenorPeso, verticeAux)) {
                                grafoAraña.AgregarArista(partida, verticeAux , grafoAraña.PesoArista(partida, verticeMenorPeso)+grafo.PesoArista(verticeMenorPeso, verticeAux));
                            }
                        }
                    }
                }
            }
        }

        return grafoAraña;
    }


}
