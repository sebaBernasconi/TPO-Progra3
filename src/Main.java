import implementacion.*;
import interfaz.ConjuntoTDA;
import interfaz.GrafoTDA;

import java.io.File;
import java.util.*;


public class Main {
    static int u = Integer.MAX_VALUE;
    static final int cantCentros = 4;
    static final int cantClientes = 5;
    static int[][] matrizCostos;
    static int[] costosFijosCentros;
    static int[] costosCentrosMuelle = new int[8];

    public static int CalcularU(List<Integer>centrosConstruidos){
        int costoU = 0;
        for (int i = 0; i < cantClientes; i++) {
            int min = Integer.MAX_VALUE;
            for (Integer centro: centrosConstruidos) {
                if (matrizCostos[centro][i] < min) {
                    min = matrizCostos[centro][i];
                }
            }
            costoU += min;
        }
        for (Integer centro: centrosConstruidos) {
            costoU += costosFijosCentros[centro];
        }
        u = costoU;
        return costoU;
    }
    public static int CalcularC(List<Integer>centrosConstruidos, List<Integer>centrosEventuales){
        int costoC = 0;
        for (int i = 0; i < cantClientes; i++) {
            int min = Integer.MAX_VALUE;
            for (Integer centro: centrosConstruidos) {
                if (matrizCostos[centro][i] < min) {
                    min = matrizCostos[centro][i];
                }
            }
            for (Integer centro: centrosEventuales) {
                if (matrizCostos[centro][i] < min) {
                    min = matrizCostos[centro][i];
                }
            }
            costoC += min;
        }
        for (Integer centro: centrosConstruidos) {
            costoC += costosFijosCentros[centro];
        }
        return costoC;
    }
    public static int CalcularRedMin(int centroEvaluado, List<Integer> centrosConstruidos){
        int redMin = 0;
        //falta contemplar un caso.
        //evalua centros construidos o posibles. los no construidos NO LOS EVALUA
        for (int i = 0; i < cantClientes; i ++){
            //para cada columna
            int min = Integer.MAX_VALUE;
            int segundoMin = Integer.MAX_VALUE;
            for(Integer centro: centrosConstruidos){
                //recorremos todos los centros
                if (matrizCostos[centro][i] < min) {
                   segundoMin = min;
                   min = matrizCostos[centro][i];
                } else if (matrizCostos[centro][i] < segundoMin && matrizCostos[centro][i] != min){
                    segundoMin = matrizCostos[centro][i];
                }
            }
            if (matrizCostos[centroEvaluado][i] == min){
               System.out.println("actual comparado con min -->" + matrizCostos[centroEvaluado][i] + "min -->" + min);
                redMin += segundoMin - min;
                System.out.println("RedMin parcial -->" + redMin);
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
//        try {
//            File archivoRutas = new File("rutasSinComentarios.txt");
//            Scanner sc = new Scanner(archivoRutas);
//            while (sc.hasNextLine()) {
//                String data = sc.nextLine();
//                String[] dataSplit = data.split(",");
//                int v1 = Integer.parseInt(dataSplit[0]);
//                int v2 = Integer.parseInt(dataSplit[1]);
//                int peso = Integer.parseInt(dataSplit[2]);
//                grafo.AgregarArista(v1, v2, peso);
//            }
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//
//        try {
//            File archivoCentros = new File("centrosSinComentarios.txt");
//            Scanner sc = new Scanner(archivoCentros);
//            for (int i = 0; i < 8; i++) {
//                String data = sc.nextLine();
//                String[] dataSplit = data.split(",");
//                int costoCentroMuelle = Integer.parseInt(dataSplit[1]);
//                int costoFijoCentro = Integer.parseInt(dataSplit[2]);
//                costosCentrosMuelle[i] = costoCentroMuelle;
//                costosFijosCentros[i] = costoFijoCentro;
//            }
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }

//        Dijkstra  dijkstra= new Dijkstra();
//        int[][] matrizAdyacencia = grafo.getmAdy();
//
//        for (int i = 50; i < 58; i++){
//            int[] costosCD = dijkstra.find_dijkstra(matrizAdyacencia, i);
//            for (int j = 0; j < 50; j++) {
//                matrizCostos[i-50][j] = costosCD[j];
//            }
//        }
        costosFijosCentros = new int[]{4, 6, 6, 8};
        matrizCostos = new int[][]{
                {3, 10, 8, 18, 14},
                {9, 4, 6, 5, 5},
                {12, 6, 10, 4, 8},
                {8, 6, 5, 12, 9}
        };

        System.out.println("U y C para el primer centro construido: " + CalcularU(List.of(0)) + " :: " + CalcularC(List.of(0), List.of(1,2,3)));
        System.out.println("Red min?-->" + CalcularRedMin(3, List.of(0,1)));
    }

//    public static int CalcularCostoAnual(List<Integer> costosCD1, int[] costoFijoCentro) {
//        int costoAnual = Integer.MAX_VALUE;
//        PriorityQueue<Nodo> colaP = new PriorityQueue<>();
//        colaP.add(new Nodo(List.of(0,0,0), u, CalcularC(costosCD1, costoFijoCentro, 1)));
//        while (!colaP.isEmpty()) {
//            // calcular redMin redMax
//        }
//        return costoAnual;
//
//    }
}