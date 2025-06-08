package Ejercicio4;

public class Test {
	public static void main(String[] args) {
        GraphListEdge<String, Integer> g = new GraphListEdge<>();

        g.insertVertex("A");
        g.insertVertex("B");
        g.insertEdge("A", "B", 1);
        g.insertEdge("A", "C", 2);

        System.out.println("BFS desde A:");
        g.bfs("A");

        System.out.println("¿Existe A-B? " + g.searchEdge("A", "B"));
        System.out.println("¿Existe A-D? " + g.searchEdge("A", "D"));
    }

}
