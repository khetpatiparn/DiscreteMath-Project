import java.util.ArrayList;
import java.util.List;

public class Prim {
    private GraphMatrix graph;

    public Prim(GraphMatrix graph) {
        System.out.println("prim working");
        this.graph = graph;
    }

    public List<Edge> findMST() {
        List<Edge> mst = new ArrayList<>();
        boolean[] visited = new boolean[graph.getVertices().size()];
    
        visited[0] = true; // Start from an arbitrary vertex (here, vertex 1)
    
        int edgeCount = 1; // Start naming edges from 1
        while (edgeCount < graph.getVertices().size()) {
            int minWeight = Integer.MAX_VALUE;
            int src = -1, dst = -1; // Initialize src and dst to invalid values
    
            // Iterate through all vertices
            for (int i = 0; i < graph.getVertices().size(); i++) {
                // If the current vertex is visited
                if (visited[i]) {
                    // Iterate through all adjacent vertices
                    for (int j = 0; j < graph.getVertices().size(); j++) {
                        // If the adjacent vertex is not visited and the weight is smaller than minWeight
                        if (!visited[j] && graph.showWeight[i][j] > 0 && graph.showWeight[i][j] < minWeight) {
                            minWeight = graph.showWeight[i][j];
                            src = i;
                            dst = j;
                        }
                    }
                }
            }
    
            // Add the edge with minimum weight to the MST
            mst.add(new Edge("e" + edgeCount, src + 1, dst + 1, minWeight));
            visited[dst] = true;
            edgeCount++;
        }
    
        return mst;
    }    
}
