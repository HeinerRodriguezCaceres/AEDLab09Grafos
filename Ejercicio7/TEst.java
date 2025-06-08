package Ejercicio7;

import Actividades.GraphLink;

public class TEst {
	public static void main(String[] args) {
        GraphLink<String> camino = new GraphLink<>();
        camino.insertVertex("A");
        camino.insertVertex("B");
        camino.insertVertex("C");
        camino.insertEdge("A", "B", 1);
        camino.insertEdge("B", "C", 1);

        GraphProperties<String> propCamino = new GraphProperties<>(camino);
        System.out.println("Camino:");
        System.out.println("Grados: " + propCamino.gradoNodos());
        System.out.println("Es camino? " + propCamino.esCamino());
        System.out.println("Es ciclo? " + propCamino.esCiclo());
        System.out.println("Es rueda? " + propCamino.esRueda());
        System.out.println();

        GraphLink<String> ciclo = new GraphLink<>();
        ciclo.insertVertex("A");
        ciclo.insertVertex("B");
        ciclo.insertVertex("C");
        ciclo.insertEdge("A", "B", 1);
        ciclo.insertEdge("B", "C", 1);
        ciclo.insertEdge("C", "A", 1);

        GraphProperties<String> propCiclo = new GraphProperties<>(ciclo);
        System.out.println("Ciclo:");
        System.out.println("Grados: " + propCiclo.gradoNodos());
        System.out.println("Es camino? " + propCiclo.esCamino());
        System.out.println("Es ciclo? " + propCiclo.esCiclo());
        System.out.println("Es rueda? " + propCiclo.esRueda());
        System.out.println();

        GraphLink<String> rueda = new GraphLink<>();
        rueda.insertVertex("A");
        rueda.insertVertex("B");
        rueda.insertVertex("C"); 
        rueda.insertVertex("D");
        rueda.insertEdge("A", "B", 1);
        rueda.insertEdge("B", "D", 1);
        rueda.insertEdge("D", "A", 1);
        rueda.insertEdge("C", "A", 1);
        rueda.insertEdge("C", "B", 1);
        rueda.insertEdge("C", "D", 1);

        GraphProperties<String> propRueda = new GraphProperties<>(rueda);
        System.out.println("Rueda:");
        System.out.println("Grados: " + propRueda.gradoNodos());
        System.out.println("Es camino? " + propRueda.esCamino());
        System.out.println("Es ciclo? " + propRueda.esCiclo());
        System.out.println("Es rueda? " + propRueda.esRueda());
        System.out.println();
    }

}
