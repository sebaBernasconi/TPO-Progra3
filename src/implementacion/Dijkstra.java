package implementacion;

import interfaz.ConjuntoTDA;
import interfaz.GrafoTDA;

public class Dijkstra {
    public GrafoTDA CalcularDijkstra(GrafoTDA grafo, int partida) {
        int verticeActual;
        int verticeAux;
        int verticeMenorPeso;
        int menorPeso;

        //creamos el grafo que vamos a devolver y agregamos el vertice que va a
        //el punto de partida. Ademas creamos un conjunto donde agregamos todos los
        //vertices del grafo original y sacamos el punto de partida
        GrafoTDA grafoArania = new GrafoLA();
        grafoArania.InicializarGrafo();
        grafoArania.AgregarVertice(partida);

        ConjuntoTDA verticesGrafoOriginal = grafo.Vertices();
        verticesGrafoOriginal.SacarElemento(partida);

        //elegimos uno de los vertices del conjunto de vertices del grafo original
        // y lo sacamos del conjunto hasta que se vacie. Ademas lo agregamos al grafo que vamos a devolver y
        //preguntamos si existe una arista directa entre el punto de partida y el vertice
        //que sacamos del conjunt para usarla para comparar
        while (!verticesGrafoOriginal.ConjuntoVacio()) {
            verticeActual = verticesGrafoOriginal.Elegir();
            verticesGrafoOriginal.SacarElemento(verticeActual);
            grafoArania.AgregarVertice(verticeActual);
            if (grafo.ExisteArista(partida, verticeActual)) {
                grafoArania.AgregarArista(partida, verticeActual, grafo.PesoArista(partida, verticeActual));
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
                if ((grafoArania.ExisteArista(partida , verticeAux)) && (menorPeso == 0 || (menorPeso > grafoArania.PesoArista(partida, verticeAux)))) {
                    menorPeso = grafoArania.PesoArista(partida, verticeAux);
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
                        if (!grafoArania.ExisteArista(partida, verticeAux)) {
                            grafoArania.AgregarArista(partida, verticeAux, grafoArania.PesoArista(partida, verticeMenorPeso)+grafo.PesoArista(verticeMenorPeso, verticeAux));
                        }
                        else {
                            if (grafoArania.PesoArista(partida, verticeAux) > grafoArania.PesoArista(partida, verticeMenorPeso)+grafo.PesoArista(verticeMenorPeso, verticeAux)) {
                                grafoArania.AgregarArista(partida, verticeAux , grafoArania.PesoArista(partida, verticeMenorPeso)+grafo.PesoArista(verticeMenorPeso, verticeAux));
                            }
                        }
                    }
                }
            }
        }

        return grafoArania;
    }
}
