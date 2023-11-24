import implementacion.*;
import interfaz.ConjuntoTDA;
import interfaz.GrafoTDA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {
    static int u = Integer.MAX_VALUE;

    public static void CalcularU(List<Integer>costosCentro, int[] costoFijoCentro, int centroEvaluado){
        int valorCompar = 0;
        for (Integer i:
             costosCentro) {
            valorCompar += i + costoFijoCentro[centroEvaluado]  ;
        }

        if(valorCompar < u){
            u = valorCompar;
        }
    }
    public static int CalcularC(List<Integer>costosCentro, int[] costoFijoCentro, int centroEvaluado){
        int c = 0;
        c = Collections.min(costosCentro) + costoFijoCentro[centroEvaluado];
        return c;
    }
    public static int CalcularRedMin(List<Integer>costosCentro){
        int redMin = 0;

        return redMin;
    }
    public static int CalcularRedMax(List<Integer>costosCentro){
        int redMax = 0;

        return redMax;
    }

    public static void MostrarGrafo(GrafoTDA U) {
        ConjuntoTDA V = U.Vertices();
        ConjuntoTDA D;
        int v;
        int d;
        int p;
        while(!V.ConjuntoVacio()) {						// ciclo externo
            v = V.Elegir();
            System.out.println("Vértice " + v);
            V.Sacar(v);
            D = U.Vertices();
            while(!D.ConjuntoVacio()) {					// ciclo interno
                d = D.Elegir();
                p = U.PesoArista(v, d);
                if (p != 0)
                    System.out.println("          " + v+"->(Peso: "+ p +")->"+d+" ");
                D.Sacar(d);
            }
        }
    }

    public static void MostrarDijkstra(GrafoTDA A,int partida) {
        int x1 = partida;
        ConjuntoTDA V2 = A.Vertices();
        while (!V2.ConjuntoVacio()) {
            int x2 = V2.Elegir();
            V2.SacarElemento(x2);
            if (A.ExisteArista(x1,x2))
                System.out.print(x1+"->(Costo: "+A.PesoArista(x1,x2)+")->"+x2+" ");
            else
                System.out.print(x1+"->(0)->"+x2+" ");
            System.out.println();
        }
    }
    public static void main(String[] args) {

        int[][] matrizCostos = new int[8][50];

        GrafoTDA g = new GrafoMA();
        g.InicializarGrafo();

        ConjuntoTDA c = new ConjuntoTA();
        c.InicializarConjunto();

        int volumenProduccionClientes = 10;

        int[] costoFijoCentros = {1900,1500,2000,2700,2500,3000,500};

        for (int i = 0; i< 50; i++){
            //agregando los 50 clientes al grafo
            g.AgregarVertice(i);
        }
        g.AgregarVertice(59);
        for (int i = 50; i < 58; i++){
            //agregando los posibles centros de distribucion al grafo
            g.AgregarVertice(i);
        }

        //a continuacion se cargan todas las aristas("rutas") al grafo
        try {
            File archivoRutas = new File("C:\\Users\\Sebas\\Documents\\GitHub\\TPO-Progra3\\src\\rutasSinComentarios.txt");
            Scanner sc = new Scanner(archivoRutas);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] dataSplit = data.split(",");
                int v1 = Integer.parseInt(dataSplit[0]);
                int v2 = Integer.parseInt(dataSplit[1]);
                int peso = Integer.parseInt(dataSplit[2]);
                g.AgregarArista(v1, v2, peso);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

//        MostrarGrafo(g);

//        Dijkstra dijkstra = new Dijkstra();
//        GrafoTDA grafoDijkstra = dijkstra.CalcularDijkstra(g, 50);
//        if (grafoDijkstra.ExisteArista(50, 9)) {
//            System.out.println(grafoDijkstra.PesoArista(50, 9));
//        }
//        else {
//            System.out.println("No existe arista");
//        }
//        MostrarDijkstra(grafoDijkstra, 50);

        DijkstraOnline dijkstra = new DijkstraOnline();
        dijkstra.dijkstra(g.getmAdy(), 50);

        // probar dijkstra con este grafo
//        GrafoTDA grafo = new GrafoMA();
//        grafo.InicializarGrafo();
//        grafo.AgregarVertice(0);
//        grafo.AgregarVertice(1);
//        grafo.AgregarVertice(2);
//        grafo.AgregarVertice(3);
//        grafo.AgregarVertice(4);
//        grafo.AgregarVertice(5);
//        grafo.AgregarVertice(6);
//        grafo.AgregarVertice(7);
//        grafo.AgregarArista(0,1,5);
//        grafo.AgregarArista(1,0,5);
//        grafo.AgregarArista(1,2,9);
//        grafo.AgregarArista(2,1,9);
//        grafo.AgregarArista(2,3,6);
//        grafo.AgregarArista(3,2,6);
//        grafo.AgregarArista(3,1,2);
//        grafo.AgregarArista(1,3,2);
//        grafo.AgregarArista(2,0,8);
//        grafo.AgregarArista(0,2,8);
//        grafo.AgregarArista(3,4,1);
//        grafo.AgregarArista(4,1,1);
//        grafo.AgregarArista(4,0,6);
//        grafo.AgregarArista(0,4,6);
//        grafo.AgregarArista(3,5,9);
//        grafo.AgregarArista(5,3,9);
//        grafo.AgregarArista(5,6,4);
//        grafo.AgregarArista(6,5,4);
//        grafo.AgregarArista(6,7,3);
//        grafo.AgregarArista(7,6,3);
//        grafo.AgregarArista(7,0,3);
//        grafo.AgregarArista(0,7,3);
//        grafo.AgregarArista(6,0,5);
//        grafo.AgregarArista(0,6,5);
//        GrafoTDA grafoDijkstra2 = dijkstra.CalcularDijkstra(grafo, 0);
//        MostrarDijkstra(grafoDijkstra2, 0);
    }

    public static int CalcularCostoAnual(List<Integer> costosCD1, int[] costoFijoCentro) {
        int costoAnual = Integer.MAX_VALUE;
        PriorityQueue<Nodo> colaP = new PriorityQueue<>();
        colaP.add(new Nodo(u, CalcularC(costosCD1, costoFijoCentro, 1)));
        while (!colaP.isEmpty()) {
            // calcular redMin redMax
        }
        return costoAnual;

    }
}