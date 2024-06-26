package Graph;

import java.util.List;
import java.util.ArrayList;

public class Dijkstra {
    public int nVer;
    public int[][] graph;
    public int source;
    public int[]distance;
    public List<Integer> distanceList = new ArrayList<>(); // List to store distance
    public List<List<Integer>> paths = new ArrayList<>(); // List to store paths

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
        distance = new int[nVer];
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

        // Set the paths to the class variable
        this.paths = paths;

        // Print all paths
        for (int i = 0; i < nVer; i++) {
            if (i != source) {
                System.out.print("Path from v" + (source + 1) + " to v" + (i + 1) + " : "); // Adjust vertex numbering
                for (int node : paths.get(i)) {
                    System.out.print("v" + (node + 1) + " "); // Adjust vertex numbering
                }
                System.out.println(",Distance: " + distance[i]);
                distanceList.add(distance[i]);
            }else{
                distanceList.add(999);
                paths.add(distanceList);
            }
        }

    }

    // Method to retrieve paths for display
    public List<String> getPathStrings() {
        List<String> pathStrings = new ArrayList<>();
        for (int i = 0; i < nVer; i++) {
            if (i != source) {
                StringBuilder pathString = new StringBuilder();
                pathString.append("Path from v").append(source + 1).append(" to v").append(i + 1).append(": ");
                for (int node : paths.get(i)) {
                    pathString.append("v").append(node + 1).append(" ");
                }
                pathString.append(", Distance : ").append(distance[i]);
                pathStrings.add(pathString.toString());
            } else {
                pathStrings.add("Source vertex: v" + (source + 1) + ", Distance: 0");
            }
        }
        return pathStrings;
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

    public List<Integer> getDistanceList() {
        return distanceList;
    }

    public void setDistanceList(List<Integer> distanceList) {
        this.distanceList = distanceList;
    }
}