package Ejercicio4;

import java.util.ArrayList;
import java.util.List;

public class GraphListEdge<V, E> {
	public ArrayList<VertexObj<V, E>> secVertex;
    public ArrayList<EdgeObj<V, E>> secEdge;
    private List<VertexObj<V, E>> vertices = new ArrayList<>();
    private List<EdgeObj<V, E>> edges = new ArrayList<>();

    public GraphListEdge() {
        this.secVertex = new ArrayList<>();
        this.secEdge = new ArrayList<>();
    }

    public void insertVertex(V info) {
        if (searchVertex(info) == null) {
            VertexObj<V, E> v = new VertexObj<>(info, secVertex.size());
            secVertex.add(v);
        }
    }

    public void insertEdge(V vi, V vj, E info) {
        VertexObj<V, E> vtx1 = searchVertex(vi);
        VertexObj<V, E> vtx2 = searchVertex(vj);

        if (vtx1 == null) {
            vtx1 = new VertexObj<>(vi, secVertex.size());
            secVertex.add(vtx1);
        }

        if (vtx2 == null) {
            vtx2 = new VertexObj<>(vj, secVertex.size());
            secVertex.add(vtx2);
        }

        if (!searchEdge(vi, vj)) {
            EdgeObj<V, E> edge = new EdgeObj<>(vtx1, vtx2, info, secEdge.size());
            secEdge.add(edge);
        }
    }

    public VertexObj<V, E> searchVertex(V info) {
        for (VertexObj<V, E> v : secVertex) {
            if (v.info.equals(info)) {
                return v;
            }
        }
        return null;
    }

    public boolean searchEdge(V vi, V vj) {
        for (EdgeObj<V, E> e : secEdge) {
            if (e.endVertex1.info.equals(vi) && e.endVertex2.info.equals(vj)) {
                return true;
            }
        }
        return false;
    }

    public void bfs(V start) {
        VertexObj<V, E> startVertex = searchVertex(start);
        if (startVertex == null) return;

        ArrayList<VertexObj<V, E>> visited = new ArrayList<>();
        ArrayList<VertexObj<V, E>> queue = new ArrayList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            VertexObj<V, E> current = queue.remove(0);
            System.out.println("Visitando: " + current.info);

            for (EdgeObj<V, E> edge : secEdge) {
                VertexObj<V, E> neighbor = null;
                if (edge.endVertex1.equals(current)) {
                    neighbor = edge.endVertex2;
                } else if (edge.endVertex2.equals(current)) {
                    neighbor = edge.endVertex1;
                }

                if (neighbor != null && !visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }
    public List<VertexObj<V, E>> getVertices() {
        return vertices;
    }

    public List<EdgeObj<V, E>> getEdges() {
        return edges; 
    }

}
