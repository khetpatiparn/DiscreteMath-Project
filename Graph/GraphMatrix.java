package Graph;

import java.util.ArrayList;
import java.util.List;

public class GraphMatrix{
    // build adjacency matrix
    private List<String> vertices = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();
    int[][] matrix;
    static int[][] showWeight;
    public List<String> getVertices() {
        return vertices;
    }
    public void setVertices(List<String> vertices) {
        this.vertices = vertices;
    }
    public List<Edge> getEdges() {
        return edges;
    }
    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
    public int[][] getMatrix() {
        return matrix;
    }
    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
    public int[][] getShowWeight() {
        return showWeight;
    }
    
    public GraphMatrix(int countV, int countE){
        matrix = new int[countV][countV]; // build size matrix
        showWeight = new int[countV][countV];
        vertices = listVertices(countV); 
        setUp();
    }
    public GraphMatrix(int countV){
        matrix = new int[countV][countV]; //int[5][5] => 0 1 2 3 4
        showWeight = new int[countV][countV];
        vertices = listVertices(countV); 
        setUp();
        // check
    }

    // build vertex
    public List<String> listVertices(int countVertices){
        for (int i = 1; i <= countVertices; i++){
            String ver = "v" + i;
            vertices.add(ver);
        }
        return vertices;
    }

    public boolean isConnected(){
        boolean[] visited = new boolean[vertices.size()]; // Track visited vertices
        dfs(1, visited);  // Start a Depth-First Search from vertex 0
    
        // Check if all vertices have been visited
        for (boolean isVisited : visited) {
            if (!isVisited) {
                System.out.println("Graph is NOT connected");
                return false; // Graph is disconnected
            }
        }
        System.out.println("Graph is connected");
        return true;
    }
    
    // Helper for Depth-First Search
    private void dfs(int vertex, boolean[] visited) {
        visited[vertex] = true;
    
        // Explore neighbors
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[vertex][i] > 0 && !visited[i]) {
                dfs(i, visited); 
            }
        }
    }

    public void addEdges(String name, int src, int dst, int w){
        Edge e = new Edge(name, src, dst, w);
        edges.add(e);
        System.out.println("---Add Edges: from V" + e.getSrc() + " to V" + e.getDst() + "---");
        if (e.getSrc() == e.getDst()){
            matrix[e.getSrc() - 1][e.getDst() - 1] += 1;
        }
        else{
            matrix[e.getSrc() - 1][e.getDst() - 1] += 1;
            matrix[e.getDst() - 1][e.getSrc() - 1] += 1;
        }
        Draw(matrix);
        addWeight(e);
    }

    public void addEdges(Edge e){
        edges.add(e);
        System.out.println("---Add Edges: from V" + e.getSrc() + " to V" + e.getDst() + "---");
        if (e.getSrc() == e.getDst()){
            matrix[e.getSrc() - 1][e.getDst() - 1] += 1;
        }
        else{
            matrix[e.getSrc() - 1][e.getDst() - 1] += 1;
            matrix[e.getDst() - 1][e.getSrc() - 1] += 1;
        }
        Draw(matrix);
        addWeight(e);
    }

    public void addWeight(Edge e){
        System.out.println("---Update Weight---");
        showWeight[e.getSrc() - 1][e.getDst() - 1] += e.getWeight();
        showWeight[e.getDst() - 1][e.getSrc() - 1] += e.getWeight();

        Draw(showWeight);
    }
    
    public void Draw(int [][] mt) {
        System.out.print("   ");
        for (String item : vertices){
            System.out.print(item + " ");
        }
        System.out.println();

        for (int rows = 0; rows < mt.length; rows++){
            System.out.print(vertices.get(rows) + "  ");
            for(int cols = 0; cols < mt.length; cols++){
                System.out.print(mt[rows][cols] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void setUp(){
        System.out.println("---Set up Matrix---");
        System.out.print("   ");
        for (String item : vertices){
            System.out.print(item + " ");
        }
        System.out.println();

        for (int rows = 0; rows < matrix.length; rows++){
            System.out.print(vertices.get(rows) + "  ");
            for(int cols = 0; cols < matrix.length; cols++){
                System.out.print(0 + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}