package Ejercicio5;

import Ejercicio4.EdgeObj;
import Ejercicio4.GraphListEdge;
import Ejercicio4.VertexObj;

public class TipoGrafoDetector<V, E> {
	 private GraphListEdge<V, E> grafo;

	    public TipoGrafoDetector(GraphListEdge<V, E> grafo) {
	        this.grafo = grafo;
	    }

	    public int grado(VertexObj<V, E> v) {
	        int count = 0;
	        for (EdgeObj<V, E> e : grafo.secEdge) {
	            if (e.endVertex1.equals(v) || e.endVertex2.equals(v)) {
	                count++;
	            }
	        }
	        return count;
	    }

	    public boolean esCamino() {
	        int grado1 = 0;
	        int grado2 = 0;

	        for (VertexObj<V, E> v : grafo.secVertex) {
	            int g = grado(v);
	            if (g == 1) grado1++;
	            else if (g == 2) grado2++;
	            else return false;
	        }

	        return grado1 == 2 && grado2 == (grafo.secVertex.size() - 2);
	    }

	    public boolean esCiclo() {
	        for (VertexObj<V, E> v : grafo.secVertex) {
	            if (grado(v) != 2) return false;
	        }
	        return true;
	    }

	    public boolean esRueda() {
	        int centro = 0;
	        int periferia = 0;
	        int n = grafo.secVertex.size();

	        for (VertexObj<V, E> v : grafo.secVertex) {
	            int g = grado(v);
	            if (g == n - 1) centro++;
	            else if (g == 3) periferia++;
	            else return false;
	        }

	        return centro == 1 && periferia == (n - 1);
	    }

	    public boolean esCompleto() {
	        int n = grafo.secVertex.size();
	        int esperado = n * (n - 1) / 2;

	        if (grafo.secEdge.size() != esperado) return false;

	        for (VertexObj<V, E> v : grafo.secVertex) {
	            if (grado(v) != n - 1) return false;
	        }

	        return true;
	    }

	    public void identificarTipo() {
	        int n = grafo.secVertex.size();

	        if (esCompleto()) {
	            System.out.println("Es un grafo completo K" + n);
	        } else if (esRueda()) {
	            System.out.println("Es un grafo rueda W" + n);
	        } else if (esCiclo()) {
	            System.out.println("Es un ciclo C" + n);
	        } else if (esCamino()) {
	            System.out.println("Es un camino P" + n);
	        } else {
	            System.out.println("Grafo no clasificado o estructura no válida.");
	        }
	    }
}

