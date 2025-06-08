package Ejercicio6;

import Ejercicio4.GraphListEdge;
import Ejercicio5.TipoGrafoDetector;

public class Test {
	public static void main(String[] args) {
        GraphListEdge<String, String> grafo = new GraphListEdge<>(false);

        grafo.insertVertex("A");
        grafo.insertVertex("B");
        grafo.insertVertex("C");
        grafo.insertVertex("D");

        grafo.insertEdge("A", "B", "e1");
        grafo.insertEdge("B", "C", "e2");
        grafo.insertEdge("C", "D", "e3");
        grafo.insertEdge("D", "A", "e4"); 

        AnalizadorGrafo<String, String> analizador = new AnalizadorGrafo<>();

        System.out.println("=== Representación Formal ===");
        analizador.imprimirFormal(grafo);

        System.out.println("\n=== Lista de Adyacencias ===");
        analizador.imprimirListaAdyacencias(grafo);

        System.out.println("\n=== Matriz de Adyacencia ===");
        analizador.imprimirMatrizAdyacencia(grafo);

        System.out.println("\n=== Tipo de Grafo ===");
        analizador.analizarTipoGrafo(grafo);
    }
}
