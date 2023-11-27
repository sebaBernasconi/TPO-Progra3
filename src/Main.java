import implementacion.*;
import interfaz.ConjuntoTDA;
import interfaz.GrafoTDA;

import java.io.File;
import java.util.*;


public class Main {
    static int u = Integer.MAX_VALUE;
    static int[][] matrizCostos = new int[8][50];
    static int[] costosFijosCentros = new int[8];
    static int[] costosCentrosMuelle = new int[8];

    public static void CalcularU(List<Integer>centrosConsiderados){
//        int valorCompar = 0;
//        for (Integer i: costosCentro) {
//            valorCompar += i + costoFijoCentro[centroEvaluado]  ;
//        }
//
//        if(valorCompar < u){
//            u = valorCompar;
//        }
    }
    public static int CalcularC(List<Integer>costosCentro, int[] costoFijoCentro, int centroEvaluado){
        int c = 0;
        c = Collections.min(costosCentro) + costoFijoCentro[centroEvaluado];
        return c;
    }
    public static int CalcularRedMin(int [][] costosClientes, int redMinmaAnterior,int centroEvaluado){
        int redMin = redMinmaAnterior;

        for (int i = 0; i < 49; i++ ) { //recorriendo columnas
            for (int j = 0; i < 8; i++ ){//recorriendo filas
                //primero recorre todas las filas para una sola columna
                //sacando asi el valor para el minimo
                if (costosClientes[i][j] < redMin){
                    redMin = costosClientes[i][j];
                }

            }
        }

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

        GrafoTDA grafo = new GrafoMA();
        grafo.InicializarGrafo();

        int volumenProduccionClientes = 10;

        for (int i = 0; i< 50; i++){
            //agregando los 50 clientes al grafo
            grafo.AgregarVertice(i);
        }

        for (int i = 50; i < 58; i++){
            //agregando los posibles centros de distribucion al grafo
            grafo.AgregarVertice(i);
        }

        //a continuacion se cargan todas las aristas("rutas") al grafo
        try {
            File archivoRutas = new File("rutasSinComentarios.txt");
            Scanner sc = new Scanner(archivoRutas);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] dataSplit = data.split(",");
                int v1 = Integer.parseInt(dataSplit[0]);
                int v2 = Integer.parseInt(dataSplit[1]);
                int peso = Integer.parseInt(dataSplit[2]);
                grafo.AgregarArista(v1, v2, peso);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        try {
            File archivoCentros = new File("centrosSinComentarios.txt");
            Scanner sc = new Scanner(archivoCentros);
            for (int i = 0; i < 8; i++) {
                String data = sc.nextLine();
                String[] dataSplit = data.split(",");
                int costoCentroMuelle = Integer.parseInt(dataSplit[2]);
                int costoFijoCentro = Integer.parseInt(dataSplit[3]);
                costosCentrosMuelle[i] = costoCentroMuelle;
                costosFijosCentros[i] = costoFijoCentro;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        Dijkstra  dijkstra= new Dijkstra();
        int[][] matrizAdyacencia = grafo.getmAdy();

        for (int i = 50; i < 58; i++){
            int[] costosCD = dijkstra.find_dijkstra(matrizAdyacencia, i);
            for (int j = 0; j < 50; j++) {
                matrizCostos[i-50][j] = costosCD[j];
            }
        }

    }

    public static int CalcularCostoAnual(List<Integer> costosCD1, int[] costoFijoCentro) {
        int costoAnual = Integer.MAX_VALUE;
        PriorityQueue<Nodo> colaP = new PriorityQueue<>();
        colaP.add(new Nodo(List.of(0,0,0), u, CalcularC(costosCD1, costoFijoCentro, 1)));
        while (!colaP.isEmpty()) {
            // calcular redMin redMax
        }
        return costoAnual;

    }
}