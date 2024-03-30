package Graph;

import java.util.ArrayList;
import java.util.List;

public class Prim {
    private GraphMatrix graph;

    public Prim(GraphMatrix graph) {
        this.graph = graph;
    }

    public List<Edge> findMST() {
        List<Edge> mst = new ArrayList<>();
        boolean[] visited = new boolean[graph.getVertices().size()];

        visited[0] = true; // Start from an arbitrary vertex (here, vertex 0)

        int edgeCount = 0;
        while (edgeCount < graph.getVertices().size() - 1) {
            int minWeight = Integer.MAX_VALUE;
            int src = 0, dst = 0;

            for (int i = 0; i < graph.getVertices().size(); i++) {
                if (visited[i]) {
                    for (int j = 0; j < graph.getVertices().size(); j++) {
                        if (!visited[j] && graph.showWeight[i][j] > 0 && graph.showWeight[i][j] < minWeight) {
                            minWeight = graph.showWeight[i][j];
                            src = i;
                            dst = j;
                        }
                    }
                }
            }

            mst.add(new Edge("e" + edgeCount, src, dst, minWeight));
            visited[dst] = true;
            edgeCount++;
        }

        return mst;
    }
}
