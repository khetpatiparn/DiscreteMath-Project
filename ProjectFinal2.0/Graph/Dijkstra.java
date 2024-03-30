package Graph;
import java.util.List;
import java.util.ArrayList;


public class Dijkstra {
    public int nVer;
    public int[][] graph;
    public int source;

    public Dijkstra(int V, int graph[][], int src) {
        this.nVer = V;
        this.graph = graph;
        this.source = src;
    }

    public void dijkstra() {
        List<List<Edge>> adjacencyList = new ArrayList<>();

        // Initialize adjacency list
        for (int i = 0; i < nVer; i++) {
            List<Edge> edges = new ArrayList<>();
            adjacencyList.add(edges);
        }

        // Convert graph matrix to adjacency list
        for (int i = 0; i < nVer; i++) {
            for (int j = 0; j < nVer; j++) {
                if (graph[i][j] != 0) {
                    Edge edge = new Edge(i, j, graph[i][j]);
                    adjacencyList.get(i).add(edge);
                }
            }
        }

        boolean[] visited = new boolean[nVer];
        int[] distance = new int[nVer];
        List<List<Integer>> paths = new ArrayList<>();

        // Initialize distances and paths
        for (int i = 0; i < nVer; i++) {
            visited[i] = false;
            distance[i] = Integer.MAX_VALUE;
            paths.add(new ArrayList<>());
        }

        // Distance from source to itself is 0
        distance[source] = 0;
        paths.get(source).add(source);

        // Dijkstra's algorithm
        for (int count = 0; count < nVer - 1; count++) {
            int u = minDistance(distance, visited);
            visited[u] = true;

            for (Edge edge : adjacencyList.get(u)) {
                int v = edge.getDst();
                if (!visited[v] && distance[u] != Integer.MAX_VALUE && distance[u] + edge.getWeight() < distance[v]) {
                    distance[v] = distance[u] + edge.getWeight();
                    paths.get(v).clear();
                    paths.get(v).addAll(paths.get(u));
                    paths.get(v).add(v);
                }
            }
        }

        // Print all paths
        for (int i = 0; i < nVer; i++) {
            if (i != source) {
                System.out.print("Path from v" + source + " to v" + i + ": ");
                for (int node : paths.get(i)) {
                    System.out.print("v"+node + " ");
                }
                System.out.println(", Distance: " + distance[i]);
            }
        }
    }

    private int minDistance(int[] distance, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < nVer; v++) {
            if (!visited[v] && distance[v] <= min) {
                min = distance[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        int V = 9;
        int[][] graph = {
            {0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        int source = 0;

        Dijkstra dijkstra = new Dijkstra(V, graph, source);
        dijkstra.dijkstra();
    }
}
