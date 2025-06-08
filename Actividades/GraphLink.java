package Actividades;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GraphLink<E> {
	public ListLinked<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new ListLinked<>();
    }

    public boolean searchVertex(E data) {
        for (Vertex<E> v : listVertex) {
            if (v.getData().equals(data)) return true;
        }
        return false;
    }
    

    public boolean searchEdge(E v, E z) {
        Vertex<E> origin = getVertex(v);
        if (origin != null) {
            for (Edge<E> e : origin.getListAdj()) {
                if (e.getRefDest().getData().equals(z)) return true;
            }
        }
        return false;
    }

    public void insertVertex(E data) {
        if (!searchVertex(data)) {
            listVertex.addLast(new Vertex<>(data));
        }
    }

    public void insertEdge(E v, E z, int weight) {
        Vertex<E> origin = getVertex(v);
        Vertex<E> destination = getVertex(z);
        if (origin != null && destination != null) {
            origin.getListAdj().addLast(new Edge<>(destination, weight));
            destination.getListAdj().addLast(new Edge<>(origin, weight)); // Grafo no dirigido
        }
    }

    public void removeEdge(E v, E z) {
        Vertex<E> origin = getVertex(v);
        Vertex<E> destination = getVertex(z);
        if (origin != null && destination != null) {
            origin.getListAdj().remove(new Edge<>(destination, 0));
            destination.getListAdj().remove(new Edge<>(origin, 0));
        }
    }

    public void removeVertex(E data) {
        Vertex<E> toRemove = getVertex(data);
        if (toRemove != null) {
            for (Vertex<E> v : listVertex) {
                v.getListAdj().remove(new Edge<>(toRemove, 0));
            }
            listVertex.remove(toRemove);
        }
    }

    public Vertex<E> searchVertexObject(E data) {
        for (Vertex<E> vertex : this.listVertex) {
            if (vertex.data.equals(data)) {
                return vertex;
            }
        }
        return null;
    }
    
    
    
    
    
    public void bfs(E v) {
        Vertex<E> start = getVertex(v);
        if (start == null) {
            System.out.println("El vértice " + v + " no existe.");
            return;
        }

        Set<Vertex<E>> visited = new HashSet<>();
        Queue<Vertex<E>> queue = new LinkedList<>();

        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            System.out.print(current.data + " ");

            for (Edge<E> edge : current.listAdj) {
                Vertex<E> neighbor = edge.refDest;
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        System.out.println();
    }

    public ArrayList<E> bfsPath(E v, E z) {
        Vertex<E> start = getVertex(v);
        Vertex<E> end = getVertex(z);

        if (start == null || end == null) return null;

        Map<Vertex<E>, Vertex<E>> prev = new HashMap<>();
        Set<Vertex<E>> visited = new HashSet<>();
        Queue<Vertex<E>> queue = new LinkedList<>();

        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();

            if (current.equals(end)) break;

            for (Edge<E> edge : current.listAdj) {
                Vertex<E> neighbor = edge.refDest;
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                    prev.put(neighbor, current);
                }
            }
        }

        ArrayList<E> path = new ArrayList<>();
        for (Vertex<E> at = end; at != null; at = prev.get(at)) {
            path.add(0, at.data);  
        }

        if (!path.isEmpty() && path.get(0).equals(v)) {
            return path;
        } else {
            return null;
        }
    }

    
    public Stack<E> Dijkstra(E startData, E endData) {
        HashMap<Vertex<E>, Double> distances = new HashMap<>();
        HashMap<Vertex<E>, Vertex<E>> previous = new HashMap<>();
        PriorityQueue<Vertex<E>> pq = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        HashSet<Vertex<E>> visited = new HashSet<>();

        Vertex<E> start = searchVertexObject(startData);
        Vertex<E> end = searchVertexObject(endData);

        if (start == null || end == null) return null;

        for (Vertex<E> v : listVertex) {
            distances.put(v, Double.MAX_VALUE);
            previous.put(v, null);
        }

        distances.put(start, 0.0);
        pq.add(start);

        while (!pq.isEmpty()) {
            Vertex<E> current = pq.poll();
            if (!visited.add(current)) continue;

            for (Edge<E> edge : current.listAdj) {
                Vertex<E> neighbor = edge.refDest;
                double newDist = distances.get(current) + edge.weight;
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                    pq.add(neighbor);
                }
            }
        }

        Stack<E> path = new Stack<>();
        for (Vertex<E> at = end; at != null; at = previous.get(at)) {
            path.push(at.data);
        }

        Stack<E> finalPath = new Stack<>();
        while (!path.isEmpty()) {
            finalPath.push(path.pop());
        }

        return finalPath;
    }
    public boolean isConexo() {
        if (listVertex.isEmpty()) return true;

        HashSet<Vertex<E>> visited = new HashSet<>();
        Vertex<E> start = listVertex.get(0);
        dfsVisit(start, visited);

        return visited.size() == listVertex.size();
    }

    private void dfsVisit(Vertex<E> current, HashSet<Vertex<E>> visited) {
        visited.add(current);
        for (Edge<E> edge : current.listAdj) {
            Vertex<E> neighbor = edge.refDest;
            if (!visited.contains(neighbor)) {
                dfsVisit(neighbor, visited);
            }
        }
    }
    
    public ArrayList<E> shortPath(E startData, E endData) {
        HashMap<Vertex<E>, Double> distances = new HashMap<>();
        HashMap<Vertex<E>, Vertex<E>> previous = new HashMap<>();
        PriorityQueue<Vertex<E>> pq = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        HashSet<Vertex<E>> visited = new HashSet<>();

        Vertex<E> start = searchVertexObject(startData);
        Vertex<E> end = searchVertexObject(endData);

        if (start == null || end == null) return null;

        for (Vertex<E> v : listVertex) {
            distances.put(v, Double.MAX_VALUE);
            previous.put(v, null);
        }

        distances.put(start, 0.0);
        pq.add(start);

        while (!pq.isEmpty()) {
            Vertex<E> current = pq.poll();
            if (!visited.add(current)) continue;

            for (Edge<E> edge : current.listAdj) {
                Vertex<E> neighbor = edge.refDest;
                double newDist = distances.get(current) + edge.weight;
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                    pq.add(neighbor);
                }
            }
        }

        ArrayList<E> path = new ArrayList<>();
        for (Vertex<E> at = end; at != null; at = previous.get(at)) {
            path.add(0, at.data);
        }

        return path;
    }
    
    
    
    public Vertex<E> getVertex(E data) {
        for (Vertex<E> v : listVertex) {
            if (v.getData().equals(data)) return v;
        }
        return null;
    }

    @Override
    public String toString() {
        return listVertex.toString();
    }
    public void printGraph() {
        for (Vertex<E> vertex : listVertex) {
            System.out.print(vertex.data + " -> ");
            for (Edge<E> edge : vertex.listAdj) {
                System.out.print(edge.refDest.data + "(" + edge.weight + ") -> ");
            }
            System.out.println("null");
        }
    }
}
