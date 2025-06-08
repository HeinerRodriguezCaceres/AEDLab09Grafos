package Ejercicio6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Ejercicio4.EdgeObj;
import Ejercicio4.GraphListEdge;
import Ejercicio4.VertexObj;



public class AnalizadorGrafo<V, E> {
	private GraphListEdge<V, E> grafo;

    public AnalizadorGrafo(GraphListEdge<V, E> grafo) {
        this.grafo = grafo;
    }

    public void mostrarFormaFormal() {
        System.out.println("Vértices: " + grafo.getVertices());
        System.out.println("Aristas:");
        for (EdgeObj<V, E> arista : grafo.getEdges()) {
            System.out.println(arista.getVertex1().getData() + " -- " + arista.getVertex2().getData());
        }
    }

    public void mostrarListaAdyacencias() {
        System.out.println("Lista de Adyacencias:");
        for (VertexObj<V, E> vertice : grafo.getVertices()) {
            System.out.print(vertice.getData() + ": ");
            List<VertexObj<V, E>> adyacentes = new ArrayList<>();
            for (EdgeObj<V, E> arista : grafo.getEdges()) {
                if (arista.getVertex1().equals(vertice)) {
                    adyacentes.add(arista.getVertex2());
                } else if (arista.getVertex2().equals(vertice)) {
                    adyacentes.add(arista.getVertex1());
                }
            }
            for (VertexObj<V, E> ady : adyacentes) {
                System.out.print(ady.getData() + " ");
            }
            System.out.println();
        }
    }

    public void mostrarMatrizAdyacencia() {
        List<VertexObj<V, E>> vertices = grafo.getVertices();
        int n = vertices.size();
        int[][] matriz = new int[n][n];

        for (EdgeObj<V, E> arista : grafo.getEdges()) {
            int i = vertices.indexOf(arista.getVertex1());
            int j = vertices.indexOf(arista.getVertex2());
            matriz[i][j] = 1;
            matriz[j][i] = 1;
        }

        System.out.println("Matriz de Adyacencia:");
        System.out.print("   ");
        for (VertexObj<V, E> v : vertices) {
            System.out.print(v.getData() + " ");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print(vertices.get(i).getData() + ": ");
            for (int j = 0; j < n; j++) {
                System.out.print(matriz[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public void mostrarGrados() {
        System.out.println("Grados de los nodos:");
        for (VertexObj<V, E> vertice : grafo.getVertices()) {
            int grado = 0;
            for (EdgeObj<V, E> arista : grafo.getEdges()) {
                if (arista.getVertex1().equals(vertice) || arista.getVertex2().equals(vertice)) {
                    grado++;
                }
            }
            System.out.println(vertice.getData() + ": G" + grado);
        }
    }

    public void detectarTipo() {
        List<VertexObj<V, E>> vertices = grafo.getVertices();
        int n = vertices.size();
        int[] grados = new int[n];
        for (int i = 0; i < n; i++) {
            VertexObj<V, E> v = vertices.get(i);
            for (EdgeObj<V, E> arista : grafo.getEdges()) {
                if (arista.getVertex1().equals(v) || arista.getVertex2().equals(v)) {
                    grados[i]++;
                }
            }
        }

        int max = Arrays.stream(grados).max().getAsInt();
        int min = Arrays.stream(grados).min().getAsInt();

        boolean esCompleto = true;
        for (int g : grados) {
            if (g != n - 1) {
                esCompleto = false;
                break;
            }
        }

        if (esCompleto) {
            System.out.println("Es un grafo completo: K" + n);
            return;
        }

        int gradoDos = 0, gradoUno = 0, gradoCentral = 0;
        for (int g : grados) {
            if (g == 2) gradoDos++;
            if (g == 1) gradoUno++;
            if (g == n - 1) gradoCentral++;
        }

        if (gradoUno == 2 && gradoDos == n - 2) {
            System.out.println("Es un camino: P" + n);
        } else if (gradoDos == n) {
            System.out.println("Es un ciclo: C" + n);
        } else if (gradoCentral == 1 && gradoDos == n - 1) {
            System.out.println("Es una rueda: W" + n);
        } else {
            System.out.println("No se pudo determinar el tipo específico del grafo.");
        }
    }
}
