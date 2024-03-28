import java.util.ArrayList;
import java.util.List;

public class GraphMatrix{
    // build adjacency matrix
    List<String> vertices = new ArrayList<>();
    int[][] matrix;

    public GraphMatrix(int countV, int countE){
        matrix = new int[countV][countV]; // build size matrix
        vertices = listVertices(countV); 
        setUp();
        // System.out.println(vertices);
    }
    public GraphMatrix(int countV){
        matrix = new int[countV][countV];
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


    // public void checkDeg(){
    //     for(int i = 0; i < matrix.length; i++){
    //         int degree = 0;
    //         for(int j = 0; j < matrix.length; j++){
    //             degree += matrix[i][j]; // Sum the values in each row
    //         }
    //         System.out.println("Degree of Vertex " + vertices.get(i) + ": " + degree);
    //     }
    // }

    public void isConnected(){
        boolean[] visited = new boolean[vertices.size()]; // Track visited vertices
        dfs(0, visited);  // Start a Depth-First Search from vertex 0
    
        // Check if all vertices have been visited
        for (boolean isVisited : visited) {
            if (!isVisited) {
                System.out.println("Graph is NOT connected");
                return; // Graph is disconnected
            }
        }
        System.out.println("Graph is connected");
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

    public void addEdges(Edge e){
        System.out.println("---Add Edges: from V" + e.getSrc() + " to V" + e.getDst() + "---");
        matrix[e.getSrc()][e.getDst()] += 1;
        matrix[e.getDst()][e.getSrc()] += 1;
        Draw(matrix);
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
        int countV = 5;
        int countE = 3;
        GraphMatrix myG = new GraphMatrix(countV);

        // Add Edges endpoint and Weight
        myG.addEdges(new Edge("e1", 2 ,3 , 50));
        myG.addEdges(new Edge("e2", 2 ,3 , 80));
        myG.addEdges(new Edge("e3", 3 ,3 , 50));
        myG.addEdges(new Edge(0, 2));
        myG.addEdges(new Edge(0, 1));
        myG.addEdges(new Edge(1, 2));
        myG.addEdges(new Edge(1, 4));

        // myG.checkDeg();
        myG.isConnected();
    }
}