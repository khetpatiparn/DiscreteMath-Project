import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    public GraphMatrix(){
        setUp();
    }

    // build vertex
    public List<String> listVertices(int countVertices){
        for (int i = 1; i <= countVertices; i++){
            String ver = "v" + i;
            vertices.add(ver);
        }
        return vertices;
    }

    // public isGraph(int countV, int countE){
        
    // }

    // public checkDeg(){
        
    // }

    public void addEdges(Edge e){
        System.out.println("---Add Edges: from V" + e.getSrc() + " to V" + e.getDst() + "---");
        matrix[e.getSrc()][e.getDst()] = 1;
        matrix[e.getDst()][e.getSrc()] = 1;
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
        Scanner sc = new Scanner(System.in);
        // Process 1 : Input count of vertex
        System.out.print("Input Vertex's count: ");
        int countV = sc.nextInt();
        // Process 2 : Build Matrix
        System.out.print("Input Edge's count: ");
        int countE = sc.nextInt();
        GraphMatrix myG = new GraphMatrix(countV, countE);

        // Add Edges endpoint and Weight
        // myG.addEdges(new Edge("e1", 1 ,2 , 50));
        // myG.addEdges(new Edge("e1", 3 ,4 , 100));
        // myG.setUp();
        sc.close();
    }
}