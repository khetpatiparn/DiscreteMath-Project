import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class GraphMatrix{
    // build adjacency matrix
    static List<String> vertices = new ArrayList<>();
    static List<Edge> edges = new ArrayList<>();
    int[][] matrix;
    int[][] showWeight;

    public GraphMatrix(int countV, int countE){
        matrix = new int[countV][countV]; // build size matrix
        showWeight = new int[countV][countV];
        vertices = listVertices(countV); 
        setUp();
        // System.out.println(vertices);
    }
    public GraphMatrix(int countV){
        matrix = new int[countV][countV];
        showWeight = new int[countV][countV];
        vertices = listVertices(countV); 
        setUp();
        // check
    }

    // build vertex
    public List<String> listVertices(int countVertices){
        for (int i = 0; i < countVertices; i++){
            String ver = "v" + i;
            vertices.add(ver);
        }
        return vertices;
    }

    public boolean isConnected(){
        boolean[] visited = new boolean[vertices.size()]; // Track visited vertices
        dfs(0, visited);  // Start a Depth-First Search from vertex 0
    
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
            matrix[e.getSrc()][e.getDst()] += 1;
        }
        else{
            matrix[e.getSrc()][e.getDst()] += 1;
            matrix[e.getDst()][e.getSrc()] += 1;
        }
        Draw(matrix);
        addWeight(e);
    }

    public void addWeight(Edge e){
        System.out.println("---Update Weight---");
        showWeight[e.getSrc()][e.getDst()] += e.getW();
        showWeight[e.getDst()][e.getSrc()] += e.getW();

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

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int countV = 5;

        //int countE = 3;
        GraphMatrix myG = new GraphMatrix(countV);

        // Add Edges endpoint and Weight
        // while (!myG.isConnected()) {
        //     String name = sc.next();
        //     int source = sc.nextInt();
        //     int dest = sc.nextInt();
        //     int w = sc.nextInt();
        //     myG.addEdges(new Edge(name,source, dest,w));
        // } 
        
        //myG.addEdges("e0", 0 ,1 , 7);
        myG.addEdges("e0", 1 ,2 , 7);
        myG.addEdges("e1", 2 ,3 , 10);
        myG.addEdges("e2", 3 ,4 , 8);
        myG.addEdges("e3", 4 ,0 , 2);
        // myG.addEdges("e3", 4 ,1 , 2);

        // myG.checkDeg();
        myG.isConnected();
    }
}