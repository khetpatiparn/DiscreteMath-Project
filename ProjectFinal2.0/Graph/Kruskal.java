package Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Kruskal {

    public static List<Edge> findMST(GraphMatrix graph) {
        List<Edge> mstEdges = new ArrayList<>();
        List<Edge> allEdges = graph.getEdges(); 

        // Sort edges based on weight
        Comparator<Edge> comparator = new Comparator<Edge>() {
            @Override
            public int compare(Edge edge1, Edge edge2) {
                return Integer.compare(edge1.getWeight(), edge2.getWeight());
            }
        };
        
        Collections.sort(allEdges, comparator);

        DisjointSet ds = new DisjointSet(graph.getVertices().size()); 

        for (Edge edge : allEdges) {
            int rootSrc = ds.find(edge.getSrc());
            int rootDst = ds.find(edge.getDst());

            if (rootSrc != rootDst) {
                mstEdges.add(edge);
                ds.union(rootSrc, rootDst);
            }
        }

        return mstEdges;
    }
}
