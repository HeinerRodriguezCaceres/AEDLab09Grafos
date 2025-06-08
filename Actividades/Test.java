package Actividades;

import java.util.ArrayList;
import java.util.Stack;

public class Test {
	  public static void main(String[] args) {
		  GraphLink<String> graph = new GraphLink<>();

	        graph.insertVertex("A");
	        graph.insertVertex("B");
	        graph.insertVertex("C");
	        graph.insertVertex("D");
	        graph.insertVertex("E");

	        graph.insertEdge("A", "B", 2);
	        graph.insertEdge("A", "C", 4);
	        graph.insertEdge("B", "C", 1);
	        graph.insertEdge("B", "D", 7);
	        graph.insertEdge("C", "D", 3);
	        graph.insertEdge("D", "E", 1);

	        System.out.println("Grafo:");
	        graph.printGraph();

	        System.out.println("\n¿Existe el vértice 'A'? " + graph.searchVertex("A"));
	        System.out.println("¿Existe el vértice 'F'? " + graph.searchVertex("F"));

	        System.out.println("\n¿Existe la arista A -> C? " + graph.searchEdge("A", "C"));
	        System.out.println("¿Existe la arista A -> E? " + graph.searchEdge("A", "E"));

	        System.out.println("\nRecorrido BFS desde 'A':");
	        graph.bfs("A");

	        System.out.println("\nRuta más corta (BFS) de A a E:");
	        ArrayList<String> pathBFS = graph.bfsPath("A", "E");
	        if (pathBFS != null)
	            System.out.println(pathBFS);
	        else
	            System.out.println("No existe ruta");

	        System.out.println("\nRuta más corta (Dijkstra) de A a E:");
	        Stack<String> pathDijkstra = graph.Dijkstra("A", "E");
	        if (pathDijkstra != null)
	            System.out.println(pathDijkstra);
	        else
	            System.out.println("No existe ruta");

	        System.out.println("\n¿El grafo es conexo? " + graph.isConexo());

	        graph.removeVertex("C");
	        System.out.println("\nGrafo luego de eliminar el vértice 'C':");
	        graph.printGraph();

	        System.out.println("\n¿El grafo sigue siendo conexo? " + graph.isConexo());
	    }
}
