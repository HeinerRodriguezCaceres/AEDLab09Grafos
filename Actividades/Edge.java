package Actividades;

public class Edge<E> {
	public Vertex<E> refDest;
    public int weight;

    public Edge(Vertex<E> refDest, int weight) {
        this.refDest = refDest;
        this.weight = weight;
    }

    public Vertex<E> getRefDest() {
        return this.refDest;
    }

    public int getWeight() {
        return this.weight;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Edge<?>) {
            Edge<E> e = (Edge<E>) o;
            return this.refDest.equals(e.refDest);
        }
        return false;
    }

    
    @Override
    public String toString() {
        return refDest.getData() + "[" + weight + "], ";
    }
}
