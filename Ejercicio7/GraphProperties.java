package Ejercicio7;

import java.util.HashMap;
import java.util.Map;

import Actividades.Edge;
import Actividades.GraphLink;
import Actividades.Vertex;

public class GraphProperties<E> {
	private GraphLink<E> graph;

    public GraphProperties(GraphLink<E> graph) {
        this.graph = graph;
    }

    public Map<E, Integer[]> gradoNodos() {
        Map<E, Integer[]> grados = new HashMap<>();
        for (Vertex<E> v : graph.listVertex) {
            grados.put(v.getData(), new Integer[]{0, 0}); // [inDegree, outDegree]
        }
        for (Vertex<E> v : graph.listVertex) {
            for (Edge<E> e : v.getListAdj()) {
                E origen = v.getData();
                E destino = e.getRefDest().getData();

                Integer[] gOrigen = grados.get(origen);
                gOrigen[1] = gOrigen[1] + 1;
                grados.put(origen, gOrigen);

                Integer[] gDestino = grados.get(destino);
                gDestino[0] = gDestino[0] + 1;
                grados.put(destino, gDestino);
            }
        }
        return grados;
    }

    public boolean esCamino() {
        Map<E, Integer[]> grados = gradoNodos();
        int nodosConGrado1 = 0;
        int nodosConGrado2 = 0;

        for (Integer[] grado : grados.values()) {
            int in = grado[0];
            int out = grado[1];
            int suma = in + out;

            if (suma == 1) {
                nodosConGrado1++;
            } else if (suma == 2) {
                nodosConGrado2++;
            } else {
                return false;
            }
        }

        return (nodosConGrado1 == 2 && nodosConGrado2 == (grados.size() - 2));
    }

    public boolean esCiclo() {
        Map<E, Integer[]> grados = gradoNodos();

        for (Integer[] grado : grados.values()) {
            int in = grado[0];
            int out = grado[1];
            if (in != 1 || out != 1) {
                return false;
            }
        }
        return true;
    }

    public boolean esRueda() {
        Map<E, Integer[]> grados = gradoNodos();
        int n = grados.size();

        E centro = null;
        int centroGrado = 0;

        for (Map.Entry<E, Integer[]> entry : grados.entrySet()) {
            int in = entry.getValue()[0];
            int out = entry.getValue()[1];
            int gradoMax = Math.max(in, out);
            if (gradoMax == n - 1) {
                centro = entry.getKey();
                centroGrado = gradoMax;
                break;
            }
        }

        if (centro == null) return false;

        for (Map.Entry<E, Integer[]> entry : grados.entrySet()) {
            if (!entry.getKey().equals(centro)) {
                int in = entry.getValue()[0];
                int out = entry.getValue()[1];
                if (in != 2 || out != 2) { 
                    return false;
                }
            }
        }
        return true;
    }

    public boolean esRuedaExtendida() {
        return false; 
    }

}
