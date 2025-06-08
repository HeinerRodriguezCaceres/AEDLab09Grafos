package Ejercicio9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Actividades.GraphLink;

public class graphAnalizer<V, G> {
	 private GraphLink<V> G;

	    public GraphAnalyzer(Graph<V> G) {
	        this.G = G;
	    }

	    public boolean esIsomorfo(GraphLink<V> otro) {
	        if (G.size() != otro.size()) return false;
	        if (G.edgeCount() != otro.edgeCount()) return false;

	        List<V> verticesG = new ArrayList<>(G.vertices());
	        List<V> verticesOtro = new ArrayList<>(otro.vertices());

	        return verificarPermutaciones(verticesG, verticesOtro, otro);
	    }

	    private boolean verificarPermutaciones(List<V> original, List<V> objetivo, Graph<V> otro) {
	        return generarPermutaciones(original, 0, objetivo, otro);
	    }

	    private boolean generarPermutaciones(List<V> lista, int i, List<V> objetivo, Graph<V> otro) {
	        if (i == lista.size()) {
	            Map<V, V> mapa = new HashMap<>();
	            for (int j = 0; j < lista.size(); j++) {
	                mapa.put(lista.get(j), objetivo.get(j));
	            }
	            return compararAristas(mapa, otro);
	        }

	        for (int j = i; j < lista.size(); j++) {
	            Collections.swap(lista, i, j);
	            if (generarPermutaciones(lista, i + 1, objetivo, otro)) return true;
	            Collections.swap(lista, i, j);
	        }
	        return false;
	    }

	    private boolean compararAristas(Map<V, V> mapa, Graph<V> otro) {
	        for (V u : G.vertices()) {
	            for (V v : G.adjacent(u)) {
	                V u2 = mapa.get(u);
	                V v2 = mapa.get(v);
	                if (!otro.containsEdge(u2, v2)) return false;
	            }
	        }
	        return true;
	    }

	    public boolean esPlano(Map<V, int[]> posiciones) {
	        List<Pair<V, V>> aristas = G.edges();

	        for (int i = 0; i < aristas.size(); i++) {
	            for (int j = i + 1; j < aristas.size(); j++) {
	                Pair<V, V> a1 = aristas.get(i);
	                Pair<V, V> a2 = aristas.get(j);

	                Set<V> conjunto = new HashSet<>(Arrays.asList(a1.first, a1.second, a2.first, a2.second));
	                if (conjunto.size() < 4) continue;

	                if (segmentosCruzan(posiciones.get(a1.first), posiciones.get(a1.second),
	                                    posiciones.get(a2.first), posiciones.get(a2.second))) {
	                    return false;
	                }
	            }
	        }
	        return true;
	    }

	    private boolean segmentosCruzan(int[] p1, int[] p2, int[] p3, int[] p4) {
	        return orientacion(p1, p2, p3) * orientacion(p1, p2, p4) < 0 &&
	               orientacion(p3, p4, p1) * orientacion(p3, p4, p2) < 0;
	    }

	    private int orientacion(int[] a, int[] b, int[] c) {
	        return (b[1] - a[1]) * (c[0] - b[0]) - (b[0] - a[0]) * (c[1] - b[1]);
	    }

	    public boolean esConexo() {
	        Set<V> visitados = new HashSet<>();
	        V inicial = G.vertices().iterator().next();
	        dfs(inicial, visitados);
	        return visitados.size() == G.size();
	    }

	    private void dfs(V v, Set<V> visitados) {
	        visitados.add(v);
	        for (V vecino : G.adjacent(v)) {
	            if (!visitados.contains(vecino)) {
	                dfs(vecino, visitados);
	            }
	        }
	    }

	    public boolean esAutoComplementario() {
	        GraphLink<V> complemento = new GraphLink<>();
	        for (V v : G.vertices()) {
	            complemento.getVertex(v);
	        }

	        for (V u : G.vertices()) {
	            for (V v : G.vertices()) {
	                if (!u.equals(v) && !G.containsEdge(u, v)) {
	                    complemento.addEdge(u, v);
	                }
	            }
	        }

	        return esIsomorfo(complemento);
	    }
	

}
