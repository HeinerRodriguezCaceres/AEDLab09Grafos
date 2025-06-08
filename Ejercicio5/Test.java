package Ejercicio5;

import Ejercicio4.GraphListEdge;

public class Test {
	public static void main(String[] args) {
        GraphListEdge<String, String> grafo = new GraphListEdge<>();

        grafo.insertVertex("A");
        grafo.insertVertex("B");
        grafo.insertVertex("C");
        grafo.insertVertex("D");

        grafo.insertEdge("A", "B", "e1");
        grafo.insertEdge("B", "C", "e2");
        grafo.insertEdge("C", "D", "e3");
        grafo.insertEdge("D", "A", "e4");

        TipoGrafoDetector<String, String> detector = new TipoGrafoDetector<>(grafo);
        detector.identificarTipo(); 
    }

}
