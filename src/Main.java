import Implementacion.ConjuntoTA;
import Implementacion.GrafoLA;
import Interfaz.ConjuntoTDA;
import Interfaz.GrafoTDA;

public class Main {
    public  static void MostrarGrafo(GrafoTDA A) {
        ConjuntoTDA V1 = A.Vertices();
        while (!V1.ConjuntoVacio()) {
            int x1 = V1.Elegir();
            V1.SacarElemento(x1);
            ConjuntoTDA V2 = A.Vertices();
            while (!V2.ConjuntoVacio()) {
                int x2 = V2.Elegir();
                V2.SacarElemento(x2);
                if (A.ExisteArista(x1,x2))
                    System.out.print(x1+"->("+A.PesoArista(x1,x2)+")->"+x2+" ");
                else
                    System.out.print(x1+"->(0)->"+x2+" ");
            }
            System.out.println("");
        }
    }
    public static void MostrarDijkstra(GrafoTDA A,int partida) {
        int x1 = partida;
        ConjuntoTDA V2 = A.Vertices();
        while (!V2.ConjuntoVacio()) {
            int x2 = V2.Elegir();
            V2.SacarElemento(x2);
            if (A.ExisteArista(x1,x2))
                System.out.print(x1+"->("+A.PesoArista(x1,x2)+")->"+x2+" ");
            else
                System.out.print(x1+"->(0)->"+x2+" ");
        }
        System.out.println("");
    }
    public static void main(String[] args) {

        GrafoTDA g = new GrafoLA();
        g.InicializarGrafo();

        ConjuntoTDA c = new ConjuntoTA();
        c.InicializarConjunto();

        int volumenProduccionClientes = 10;

        int costoFijoCentros [] = {1900,1500,2000,2700,2500,3000,500};


        for (int i = 0; i< 50; i++){
            //agregando los 50 clientes al grafo
            g.AgregarVertice(i);
        }

        for (int i = 50; i < 58; i++){
            //agregando los posibles centros de distribucion al grafo
            g.AgregarVertice(i);
        }

        //agregando el muelle al grafo
        g.AgregarVertice(58);

        //a continuacion se cargan todas las aristas("rutas") al grafo
        g.AgregarArista(1, 2, 10);
        g.AgregarArista(2, 1, 10);
        g.AgregarArista(2, 3, 10);
        g.AgregarArista(3, 2, 10);
        g.AgregarArista(1, 28, 8);
        g.AgregarArista(28, 1, 8);
        g.AgregarArista(28, 50, 11);
        g.AgregarArista(50, 28, 11);
        g.AgregarArista(1, 3, 12);
        g.AgregarArista(3, 1, 12);
        g.AgregarArista(3, 50, 10);
        g.AgregarArista(50, 3, 10);
        g.AgregarArista(3, 4, 8);
        g.AgregarArista(4, 3, 8);
        g.AgregarArista(51, 4, 9);
        g.AgregarArista(4, 51, 9);
        g.AgregarArista(3, 5, 4);
        g.AgregarArista(5, 3, 4);
        g.AgregarArista(5, 51, 3);
        g.AgregarArista(51, 5, 3);
        g.AgregarArista(6, 5, 9);
        g.AgregarArista(5, 6, 9);
        g.AgregarArista(6, 7, 8);
        g.AgregarArista(7, 6, 8);
        g.AgregarArista(7, 8, 4);
        g.AgregarArista(8, 7, 4);
        g.AgregarArista(8, 51, 10);
        g.AgregarArista(51, 8, 10);
        g.AgregarArista(8, 10, 12);
        g.AgregarArista(10, 8, 12);
        g.AgregarArista(9, 10, 9);
        g.AgregarArista(10, 9, 9);
        g.AgregarArista(10, 11, 6);
        g.AgregarArista(11, 10, 6);
        g.AgregarArista(11, 12, 5);
        g.AgregarArista(12, 11, 5);
        g.AgregarArista(12, 13, 6);
        g.AgregarArista(13, 12, 6);
        g.AgregarArista(11, 14, 8);
        g.AgregarArista(14, 11, 8);
        g.AgregarArista(14, 15, 8);
        g.AgregarArista(15, 14, 8);
        g.AgregarArista(15, 16, 9);
        g.AgregarArista(16, 15, 9);
        g.AgregarArista(15, 20, 6);
        g.AgregarArista(20, 15, 6);
        g.AgregarArista(13, 20, 4);
        g.AgregarArista(20, 13, 4);
        g.AgregarArista(20, 18, 2);
        g.AgregarArista(18, 20, 2);
        g.AgregarArista(17, 18, 6);
        g.AgregarArista(18, 17, 6);
        g.AgregarArista(18, 19, 7);
        g.AgregarArista(19, 18, 7);
        g.AgregarArista(19, 23, 6);
        g.AgregarArista(23, 19, 6);
        g.AgregarArista(18, 55, 9);
        g.AgregarArista(55, 18, 9);
        g.AgregarArista(20, 55, 7);
        g.AgregarArista(55, 20, 7);
        g.AgregarArista(20, 21, 6);
        g.AgregarArista(21, 20, 6);
        g.AgregarArista(21, 56, 9);
        g.AgregarArista(56, 21, 9);
        g.AgregarArista(22, 56, 7);
        g.AgregarArista(56, 22, 7);
        g.AgregarArista(22, 23, 5);
        g.AgregarArista(23, 22, 5);
        g.AgregarArista(23, 55, 4);
        g.AgregarArista(55, 23, 4);
        g.AgregarArista(23, 24, 4);
        g.AgregarArista(24, 23, 4);
        g.AgregarArista(24, 25, 5);
        g.AgregarArista(25, 24, 5);
        g.AgregarArista(24, 26, 6);
        g.AgregarArista(26, 24, 6);
        g.AgregarArista(24, 27, 7);
        g.AgregarArista(27, 24, 7);
        g.AgregarArista(27, 57, 7);
        g.AgregarArista(57, 27, 7);
        g.AgregarArista(26, 57, 6);
        g.AgregarArista(57, 26, 6);
        g.AgregarArista(29, 31, 5);
        g.AgregarArista(31, 29, 5);
        g.AgregarArista(31, 32, 7);
        g.AgregarArista(32, 31, 7);
        g.AgregarArista(31, 53, 4);
        g.AgregarArista(53, 31, 4);
        g.AgregarArista(50, 30, 5);
        g.AgregarArista(30, 50, 5);
        g.AgregarArista(30, 53, 7);
        g.AgregarArista(53, 30, 7);
        g.AgregarArista(32, 33, 8);
        g.AgregarArista(33, 32, 8);
        g.AgregarArista(33, 52, 4);
        g.AgregarArista(52, 33, 4);
        g.AgregarArista(31, 52, 8);
        g.AgregarArista(52, 31, 8);
        g.AgregarArista(52, 34, 8);
        g.AgregarArista(34, 52, 8);
        g.AgregarArista(34, 35, 6);
        g.AgregarArista(35, 34, 6);
        g.AgregarArista(35, 36, 7);
        g.AgregarArista(36, 35, 7);
        g.AgregarArista(34, 39, 4);
        g.AgregarArista(39, 34, 4);
        g.AgregarArista(39, 38, 3);
        g.AgregarArista(38, 39, 3);
        g.AgregarArista(38, 37, 9);
        g.AgregarArista(37, 38, 9);
        g.AgregarArista(39, 40, 8);
        g.AgregarArista(40, 39, 8);
        g.AgregarArista(40, 41, 7);
        g.AgregarArista(41, 40, 7);
        g.AgregarArista(52, 41, 6);
        g.AgregarArista(41, 52, 6);
        g.AgregarArista(41, 53, 9);
        g.AgregarArista(53, 41, 9);
        g.AgregarArista(41, 13, 7);
        g.AgregarArista(13, 41, 7);
        g.AgregarArista(39, 43, 5);
        g.AgregarArista(43, 39, 5);
        g.AgregarArista(39, 54, 4);
        g.AgregarArista(54, 39, 4);
        g.AgregarArista(40, 54, 5);
        g.AgregarArista(54, 40, 5);
        g.AgregarArista(41, 49, 6);
        g.AgregarArista(49, 41, 6);
        g.AgregarArista(43, 42, 5);
        g.AgregarArista(42, 43, 5);
        g.AgregarArista(43, 54, 4);
        g.AgregarArista(54, 43, 4);
        g.AgregarArista(43, 44, 9);
        g.AgregarArista(44, 43, 9);
        g.AgregarArista(44, 45, 7);
        g.AgregarArista(45, 44, 7);
        g.AgregarArista(45, 54, 6);
        g.AgregarArista(54, 45, 6);
        g.AgregarArista(45, 46, 9);
        g.AgregarArista(46, 45, 9);
        g.AgregarArista(45, 47, 7);
        g.AgregarArista(47, 45, 7);
        g.AgregarArista(47, 48, 8);
        g.AgregarArista(48, 47, 8);
        g.AgregarArista(48, 49, 9);
        g.AgregarArista(49, 48, 9);
        g.AgregarArista(49, 56, 3);
        g.AgregarArista(56, 49, 3);
        g.AgregarArista(48, 0, 6);
        g.AgregarArista(0, 48, 6);
        g.AgregarArista(0, 56, 6);
        g.AgregarArista(56, 0, 6);
        g.AgregarArista(0, 57, 4);
        g.AgregarArista(57, 0, 4);
        g.AgregarArista(29, 50, 3);
        g.AgregarArista(50, 29, 3);

        

        MostrarGrafo(g);
    }
}