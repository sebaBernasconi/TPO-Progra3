package implementacion;

public class MaxHeap {
    private Nodo arr[];
    private int size;
    class Nodo {
        int valor;
        int prioridad;
    }
    public void InicializarHeap(){
        arr = new Nodo[100];
        size = -1;
    }

    // funcion para devolver el indice del padre del nodo con indice i
    private int Padre(int i){
        return ((i-1) / 2);
    }

    // funcion para devolver el índice del hijo izquierdo del nodo con índice i
    private int HijoIzq(int i){
        return ((2*i) + 1);
    }

    // funcion para devolver el indice del hijo derecho del nodo con indice i
    private int HijoDer(int i){
        return ((2*i) + 2);
    }

    // funcion para intercambiar de posicion en el arreglo dos nodos
    //guarda uno de los nodos en una variable temporal y hace el intercambio
    private void Swap(int i, int j){
        Nodo temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // funcion para mantener la propiedad del max heap cuando se inserta un elemento
    // compara si la prioridad del padre es menor que la del hijo, y si es verdadero intercambia
    // los nodos para mantener la propiedad
    private void HeapifyUp(int i){
        while (arr[Padre(i)].prioridad < arr[i].prioridad){
            Swap(Padre(i), i);          // intercambiamos el valor del padre con el del hijo
            i = Padre(i);               // actualizamos el indice i al indice que tenia el padre
        }
    }

    // funcion para mantener la propiedad del max heap cuando se remueve el elemento raiz
    // para el nodo i se chequea cual de los dos hijos (izquierdo o derecho) es mayor, y
    // se hacen el swap con el mayor
    private void HeapifyDown(int i){
        int maxIndex = i;
        // chequeamos con el hijo izquierdo y derecho cuál de los dos es mayor en prioridad
        // y lo intercambiamos por ese nodo
        int izq = HijoIzq(i);

        // verificamos que el hijo no se pase del size del array (si se pasa es porque no tiene hijo izquierdo)
        if (izq <= size && arr[izq].prioridad > arr[maxIndex].prioridad){
            maxIndex = izq;
        }

        int der = HijoDer(i);

        // cheque si la prioridad del hijo derecho es mayor que el del hijo izquierdo anterior
        if (der <= size && arr[der].prioridad > arr[maxIndex].prioridad){
            maxIndex = der;     // si la prioridad del hijo derecho es mayor se remplaza por el índice de ese
        }

        if (i != maxIndex){     // se verifica que el indice no sea el mismo del principio
            Swap(i, maxIndex);
            HeapifyDown(maxIndex);  // se llama recursivamente hasta el final del arbol
        }
    }

    // se agrega el elemento al final del arreglo y se utiliza la funcion
    // heapifyUp para mantener la propiedad
    public void InsertarElemento(int prioridad, int valor){
        Nodo elemento = new Nodo();     //creamos el nodo a insertar
        elemento.prioridad = prioridad;
        elemento.valor = valor;

        size += 1;
        arr[size] = elemento;

        HeapifyUp(size);        // para mantener la propiedad del max heap
    }

    // se remplaza la raiz por el ultimo elemento agregado y se utiliza la funcion
    // heapifyDown para mantener la propiedad
    public Nodo ExtractMax(){   // devuelve y elimina el elemento con mayor prioridad
        Nodo resultado = arr[0];

        arr[0] = arr[size];     // remplazamos la raiz con el último elemento agregado
        size -= 1;

        HeapifyDown(0);      // para mantener la propiedad del max heap
        return resultado;
    }

    public int Prioridad(){
        return arr[0].prioridad;
    }

    public int Valor(){
        return arr[0].valor;
    }

    public boolean HeapVacio(){
        return (size < 0);
    }
}
