package Ejercicio4;

public class EdgeObj<V, E> {
	public VertexObj<V, E> Vertex1;
    public VertexObj<V, E> Vertex2;
    protected E info;
    protected int position;

    public EdgeObj(VertexObj<V, E> v1, VertexObj<V, E> v2, E info, int position) {
        this.Vertex1 = v1;
        this.Vertex2 = v2;
        this.info = info;
        this.position = position;
    }
    public VertexObj<V, E> getVertex1() {
        return Vertex1; 
    }

    public VertexObj<V, E> getVertex2() {
        return Vertex2;  
    }
}
