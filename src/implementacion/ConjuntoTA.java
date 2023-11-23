package implementacion;

import interfaz.ConjuntoTDA;

public class ConjuntoTA implements ConjuntoTDA {

    int[] a;
    int cant;

    public void InicializarConjunto() {
        a = new int[1000];
        cant = 0;

    }

    public boolean ConjuntoVacio() {
        return (cant == 0);
    }

    public void AgregarElemento(int x) {
        if(!this.Pertenece(x)) {
            a[cant] = x;
            cant ++;
        }
    }

    public int Elegir() {

        return a[cant - 1];
    }

    public void SacarElemento(int x) {
        int i = 0;
        while(i < cant && a[i] != x) {
            i++;
        }
        if(i < cant) {
            a[i] = a[cant - 1];
            cant--;
        }

    }

    public boolean Pertenece(int x) {
        int i = 0;
        while(i < cant && a[i] != x) {
            i++;
        }
        return (i < cant);
    }

    public void MostrarConjunto(ConjuntoTDA c){
        while(!c.ConjuntoVacio()) {
            int elemento = 0;
            elemento = c.Elegir();
            System.out.println("El elemento actual es --> " + elemento);
            c.SacarElemento(elemento);
        }
    }

    public void Sacar(int x) {
        int i = 0;
        while(i < cant && a[i] != x)
            i++;
        if (i < cant) {
            a[i] = a[cant - 1];
            cant--;
        }

    }
}
