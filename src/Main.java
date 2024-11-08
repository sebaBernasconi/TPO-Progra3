import implementacion.*;
import interfaz.ConjuntoTDA;
import interfaz.GrafoTDA;

import java.io.File;
import java.util.*;


public class Main {

    // Ejercicio real TPO

    static final int cantCentros = 8;
    static final int cantClientes = 50;
    static int[] volumenProduccionClientes = new int[cantClientes];
    static int[][] matrizCostos = new int[cantCentros][cantClientes];
    static int[] costosFijosCentros = new int[cantCentros];
    static int[] costosCentrosAlPuerto = new int[cantCentros];

    // Ejercicio en clase

//    static final int cantCentros = 4;
//    static final int cantClientes = 5;
//    static int[][] matrizCostos;
//    static int[] costosFijosCentros;

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
        return costoU;
    }
    public static int CalcularC(List<Integer>centrosConstruidos, List<Integer>centrosConsiderados){
        int costoC = 0;
        for (int i = 0; i < cantClientes; i++) {
            int min = Integer.MAX_VALUE;
            for (Integer centro: centrosConstruidos) {
                if (matrizCostos[centro][i] < min) {
                    min = matrizCostos[centro][i];
                }
            }
            for (Integer centro: centrosConsiderados) {
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
    public static int CalcularRedMin(int centroEvaluado, List<Integer> centrosConsiderados){
        int redMin = 0;
        //falta contemplar un caso.
        //evalua centros construidos o posibles. los no construidos NO LOS EVALUA
        for (int i = 0; i < cantClientes; i ++){
            //para cada columna
            int valorCentroEvaluado = matrizCostos[centroEvaluado][i];
            int segundoMin = Integer.MAX_VALUE;
            for(Integer centro: centrosConsiderados){
                //recorremos todos los centros
                if (matrizCostos[centro][i] < valorCentroEvaluado) {
                    valorCentroEvaluado = -1;
                    break;
                }
                else if (matrizCostos[centro][i] < segundoMin){
                    segundoMin = matrizCostos[centro][i];
                }
            }
            if (matrizCostos[centroEvaluado][i] == valorCentroEvaluado){
                redMin += segundoMin - valorCentroEvaluado;
            }

        }

        return redMin;
    }
    public static int CalcularRedMax(int centroEvaluado, List<Integer> centrosConsiderados, List<Integer>centrosConstruidos){
        int redMax = 0;
        //la primera pasada agarro el maximo y despues comparo con los centros construidos
        if (centrosConstruidos.size() == 0) {
            for (int i = 0; i < cantClientes; i++){
                int valorCentroEvaluado = matrizCostos[centroEvaluado][i];
                int max = Integer.MIN_VALUE;
                for (Integer centro: centrosConsiderados){
                    if (matrizCostos[centro][i] > max){ //&& matrizCostos[J][i] != fila de centro construido
                        max = matrizCostos[centro][i];
                    }//else if(si ya hay un centro construido)
                }
                if (valorCentroEvaluado < max){ //&& no hay ningun centro construido
                    redMax += (max - valorCentroEvaluado);
                }//else if (si ya hay un centro consturido)
            }
        }
        else {
            for (int i = 0; i < cantClientes; i++) {
                int valorCentroEvaluado = matrizCostos[centroEvaluado][i];
                int min = Integer.MAX_VALUE;
                for (Integer centro: centrosConstruidos) {
                    if (matrizCostos[centro][i] < min) {
                        min = matrizCostos[centro][i];
                    }
                }
                if (valorCentroEvaluado < min) {
                    redMax += (min - valorCentroEvaluado);
                }
            }
        }

        return redMax;
    }
    public static void QueCentrosConstruyo(int[] centros) {
        System.out.println("Se construyen los centros: ");
        for (int i = 0; i < cantCentros; i++) {
            if (centros[i] == 1) {
                System.out.println("Centro: " + i);
            }
        }
        System.out.print("Arreglo x final: ");
        for (int i = 0; i < cantCentros; i++) {
            if ( i == 7) {
                System.out.print(centros[i]);
            }
            else {
                System.out.print(centros[i] + ", ");
            }
        }
        System.out.println();
    }
    public static void CentroPorCliente(int[] centros) {
        List<Integer> centrosConstruidosFinales = new ArrayList<>();
        for (int i = 0; i < cantCentros; i++) {
            if (centros[i] == 1) {
                centrosConstruidosFinales.add(i);
            }
        }

        for (int i = 0; i < cantClientes; i++) {
            int posicionCentroCostoMinimo = -1;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < cantCentros; j++) {
                if (!centrosConstruidosFinales.contains(j)) {
                    continue;
                }
                if (matrizCostos[j][i] < min) {
                    min = matrizCostos[j][i];
                    posicionCentroCostoMinimo = j;
                }
            }
            System.out.println("El cliente " + i + " entrega su mercaderia al centro: " + posicionCentroCostoMinimo
                    + ", con costo " + matrizCostos[posicionCentroCostoMinimo][i]);
        }
    }

    public static void main(String[] args) {

        GrafoTDA grafo = new GrafoMA();
        grafo.InicializarGrafo();

        for (int i = 0; i < cantClientes; i++){
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
        // A continuacion se cargan los costos fijos de construccion de los centros, los costos del camino de cada
        // centro al muelle, y el volumen de produccion de cada cliente.
        try {
            File archivoCentros = new File("centrosSinComentarios.txt");
            Scanner sc = new Scanner(archivoCentros);
            for (int i = 0; i < cantCentros; i++) {
                String data = sc.nextLine();
                String[] dataSplit = data.split(",");
                int costoCentroMuelle = Integer.parseInt(dataSplit[1]);
                int costoFijoCentro = Integer.parseInt(dataSplit[2]);
                costosCentrosAlPuerto[i] = costoCentroMuelle;
                costosFijosCentros[i] = costoFijoCentro;
            }
            for (int i = 0; i < cantClientes; i++) {
                String data = sc.nextLine();
                String[] dataSplit = data.split(",");
                int volumenProduccion = Integer.parseInt(dataSplit[1]);
                volumenProduccionClientes[i] = volumenProduccion;
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
                matrizCostos[i-50][j] = (costosCD[j] + costosCentrosAlPuerto[i-50]) * volumenProduccionClientes[j];
            }
        }

//        costosFijosCentros = new int[]{4, 6, 6, 8};
//        matrizCostos = new int[][]{
//                {3,     10,      8,     18,     14},
//                {9,     4,      6,      5,      5},
//                {12,    6,      10,     4,      8},
//                {8,     6,      5,      12,     9}
//        };

        // Resultado ejercicio:
        // Devuelve el arreglo con 1 o -1 segun que centro se construye y cual no
        int[] centrosConstruidos = CalcularCentrosConstruir();
        QueCentrosConstruyo(centrosConstruidos);    // Metodo para imprimir por pantalla que centros se construyen
        CentroPorCliente(centrosConstruidos);       // Metodo para mostrar que cliente le entrega a que centro
    }

    public static int[] CalcularCentrosConstruir() {
        /*
            creamos el nodo raiz y le calculamos U y C.
            Evaluar si C > U lo descarto y lo saco de la cola
            Sino calculo red min
            Si redmin < costo fijo -> calculo red max
            Si red min > costo fijo -> construyo

            Si red max > costo fijo -> evaluo las dos opciones y meto los nuevos nodos en la cola de prioridad
            Si red max < costo fijo -> no construyo
            repetir mientras u != c
        */
        int[] x = new int[cantCentros];
        PriorityQueue<Nodo> colaP = new PriorityQueue<>();
        List<Integer> centrosConstruidos = new ArrayList<>();
        List<Integer> centrosConsiderados = new ArrayList<>();
        // loop para agregar los centros a la lista de centros considerados
        for (int i = 0; i < cantCentros; i++) {
            centrosConsiderados.add(i);
        }
        int centroEvaluado = 0;
        int c = -1;
        int u = Integer.MAX_VALUE;
        int uPoda = Integer.MAX_VALUE;
        Nodo raiz = new Nodo(x, u, CalcularC(centrosConstruidos, centrosConsiderados));
        colaP.add(raiz);
        while (u != c) {
            Nodo nodoEvaluado = colaP.poll();
            x = nodoEvaluado.x;
            for (int i = 0; i < cantCentros; i++){
                if (x[i] == 0) {
                    centroEvaluado = i;
                    break;
                }
            }
            // Si c < u sigo evaluando las opciones
            if (nodoEvaluado.c < nodoEvaluado.u) {
                // limpiamos los centros construidos y considerados para el nodo que vamos a evaluar
                centrosConstruidos.clear();
                centrosConsiderados.clear();
                // le asignamos los centros construidos y considerados segun el vector x devuelto por el nodo anterior
                for (int i = 0; i < cantCentros; i++) {
                    if (x[i] == 1) {
                        centrosConstruidos.add(i);
                        centrosConsiderados.add(i);
                    }
                    else if (x[i] == 0){
                        if (i == centroEvaluado) {
                            continue;
                        }
                        centrosConsiderados.add(i);
                    }
                }
                nodoEvaluado.redMin = CalcularRedMin(centroEvaluado, centrosConsiderados);
                // si red min > costo fijo centro construyo directamente
                if (nodoEvaluado.redMin > costosFijosCentros[centroEvaluado]) {
                    x[centroEvaluado] = 1;      // asigno 1 a x para saber que construi
                    // limpiamos los centros construidos y considerados para el nodo que vamos a evaluar
                    centrosConstruidos.clear();
                    centrosConsiderados.clear();
                    // le asignamos los centros construidos y considerados segun el vector x devuelto por el nodo anterior
                    for (int i = 0; i < cantCentros; i++) {
                        if (x[i] == 1) {
                            centrosConstruidos.add(i);
                        }
                        else if (x[i] == 0){
                            if (i == centroEvaluado) {
                                continue;
                            }
                            centrosConsiderados.add(i);
                        }
                    }

                    int uTemp = CalcularU(centrosConstruidos);  // calculo u y c para asignarselo al nuevo nodo
                    c = CalcularC(centrosConstruidos, centrosConsiderados);
                    if (uTemp < uPoda) {
                        uPoda = uTemp;
                        u = uTemp;
                        Nodo siguiente = new Nodo(x, uPoda, c);
                        colaP.add(siguiente);
                    }
                    else {
                        u = uTemp;
                        Nodo siguiente = new Nodo(x, uTemp, c);
                        colaP.add(siguiente);
                    }
                }
                else {
                    // limpiamos los centros construidos y considerados para el nodo que vamos a evaluar
                    centrosConstruidos.clear();
                    centrosConsiderados.clear();
                    // le asignamos los centros construidos y considerados segun el vector x devuelto por el nodo anterior
                    for (int i = 0; i < cantCentros; i++) {
                        if (x[i] == 1) {
                            centrosConstruidos.add(i);
                            centrosConsiderados.add(i);
                        }
                        else if (x[i] == 0){
                            if (i == centroEvaluado) {
                                continue;
                            }
                            centrosConsiderados.add(i);
                        }
                    }
                    nodoEvaluado.redMax = CalcularRedMax(centroEvaluado, centrosConsiderados, centrosConstruidos);
                    // si red max < costo fijo centro no construyo
                    if (nodoEvaluado.redMax < costosFijosCentros[centroEvaluado]) {
                        x[centroEvaluado] = -1;
                        // limpiamos los centros construidos y considerados para el nodo que vamos a evaluar
                        centrosConstruidos.clear();
                        centrosConsiderados.clear();
                        // le asignamos los centros construidos y considerados segun el vector x devuelto por el nodo anterior
                        for (int i = 0; i < cantCentros; i++) {
                            if (x[i] == 1) {
                                centrosConstruidos.add(i);
                            }
                            else if (x[i] == 0){
                                if (i == centroEvaluado) {
                                    continue;
                                }
                                centrosConsiderados.add(i);
                            }
                        }

                        int uTemp = CalcularU(centrosConstruidos);      // calculo u y c para asignarselo al nuevo nodo
                        c = CalcularC(centrosConstruidos, centrosConsiderados);
                        if (uTemp < uPoda) {
                            uPoda = uTemp;
                            u = uTemp;
                            Nodo siguiente = new Nodo(x, uPoda, c);
                            colaP.add(siguiente);
                        }
                        else {
                            u = uTemp;
                            Nodo siguiente = new Nodo(x, uTemp, c);
                            colaP.add(siguiente);
                        }
                    }
                    else {
                        int[] xConstruyo = new int[cantCentros];
                        for (int i = 0; i < cantCentros; i++) {
                            xConstruyo[i] = x[i];
                        }
                        // asignamos la primera opcion de construir el centro
                        xConstruyo[centroEvaluado] = 1;

                        // limpiamos los centros construidos y considerados para el nodo que vamos a evaluar
                        centrosConstruidos.clear();
                        centrosConsiderados.clear();
                        // le asignamos los centros construidos y considerados segun el vector x devuelto por el nodo anterior
                        for (int i = 0; i < cantCentros; i++) {
                            if (xConstruyo[i] == 1) {
                                centrosConstruidos.add(i);
                            }
                            else if (xConstruyo[i] == 0){
                                if (i == centroEvaluado) {
                                    continue;
                                }
                                centrosConsiderados.add(i);
                            }
                        }

                        int uConstruido = CalcularU(centrosConstruidos);      // calculo u y c para asignarselo al nuevo nodo
                        c = CalcularC(centrosConstruidos, centrosConsiderados);
                        if (uConstruido < u) {
                            uPoda = uConstruido;
                            u = uConstruido;
                            Nodo construyo = new Nodo(xConstruyo, uPoda, c);
                            colaP.add(construyo);
                        }
                        else {
                            u = uConstruido;
                            Nodo construyo = new Nodo(xConstruyo, uConstruido, c);
                            colaP.add(construyo);
                        }



                        int[] xNoConstruyo = new int[cantCentros];
                        for (int i = 0; i < cantCentros; i++) {
                            xNoConstruyo[i] = x[i];
                        }
                        // asignamos la primera opcion de construir el centro
                        xNoConstruyo[centroEvaluado] = -1;

                        // limpiamos los centros construidos y considerados para el nodo que vamos a evaluar
                        centrosConstruidos.clear();
                        centrosConsiderados.clear();
                        // le asignamos los centros construidos y considerados segun el vector x devuelto por el nodo anterior
                        for (int i = 0; i < cantCentros; i++) {
                            if (xNoConstruyo[i] == 1) {
                                centrosConstruidos.add(i);
                            }
                            else if (xNoConstruyo[i] == 0){
                                if (i == centroEvaluado) {
                                    continue;
                                }
                                centrosConsiderados.add(i);
                            }
                        }

                        int uNoConstruido = CalcularU(centrosConstruidos);      // calculo u y c para asignarselo al nuevo nodo
                        c = CalcularC(centrosConstruidos, centrosConsiderados);
                        if (uNoConstruido < u) {
                            uPoda = uNoConstruido;
                            u = uNoConstruido;
                            Nodo construyo = new Nodo(xNoConstruyo, u, c);
                            colaP.add(construyo);
                        }
                        else {
                            u = uNoConstruido;
                            Nodo noConstruyo = new Nodo(xNoConstruyo, uNoConstruido, c);
                            colaP.add(noConstruyo);
                        }
                    }
                }
            }
            // Si c > u hago la poda, y no construyo
            else {
                x[centroEvaluado] = -1;
                // limpiamos los centros construidos y considerados para el nodo que vamos a evaluar
                centrosConstruidos.clear();
                centrosConsiderados.clear();
                // le asignamos los centros construidos y considerados segun el vector x devuelto por el nodo anterior
                for (int i = 0; i < cantCentros; i++) {
                    if (x[i] == 1) {
                        centrosConstruidos.add(i);
                    }
                    else if (x[i] == 0){
                        if (i == centroEvaluado) {
                            continue;
                        }
                        centrosConsiderados.add(i);
                    }
                }

                int uTemp = CalcularU(centrosConstruidos);      // calculo u y c para asignarselo al nuevo nodo
                c = CalcularC(centrosConstruidos, centrosConsiderados);
                if (uTemp < u) {
                    uPoda = uTemp;
                    u = uTemp;
                    Nodo siguiente = new Nodo(x, u, c);
                    colaP.add(siguiente);
                }
                else {
                    u = uTemp;
                    Nodo siguiente = new Nodo(x, u, c);
                    colaP.add(siguiente);
                }
            }
        }

        System.out.println("COSTO TOTAL ANUAL: " + c);
        return x;
    }
}