import java.util.List;

public class YourMainClass { // Or whatever your main class is named
    public static void main(String[] args) {
        // ... set up your GraphMatrix object (myG) ...
        int countV = 8;

        GraphMatrix myG = new GraphMatrix(countV);

        myG.addEdges("e0", 0 ,2 , 355);
        myG.addEdges("e1", 1 ,2 , 74);
        myG.addEdges("e2", 1 ,4 , 348);
        myG.addEdges("e3", 0 ,5 , 695);
        myG.addEdges("e4", 2 ,3 , 262);
        myG.addEdges("e5", 2 ,4 , 269);
        myG.addEdges("e6", 4 ,7 , 306);
        myG.addEdges("e7", 6 ,7 , 230);
        myG.addEdges("e8", 4 ,6 , 83);
        myG.addEdges("e9", 4 ,5 , 151);
        myG.addEdges("e10", 3 ,4 , 242);
        myG.isConnected();


        List<Edge> mstEdges = Kruskal.findMST(myG);

        System.out.println("MST Edges Kruskal's algorithms:");
        for (Edge edge : mstEdges) {
            System.out.println(edge.getName() + ": Src-" + edge.getSrc() + ", Dst-" + edge.getDst() + ", Weight-" + edge.getW());
        }
        System.out.println(mstEdges);
        System.out.println();

        Prim prims = new Prim(myG);  

        // Find the MST using Prim's algorithm
        List<Edge> mstEdgesprim= prims.findMST(); 
        System.out.println("MST Edges Prim's algorithms:");
        for (Edge edge : mstEdgesprim) {
            System.out.println(edge.getName() + ": Src-" + edge.getSrc() + ", Dst-" + edge.getDst() + ", Weight-" + edge.getW());
        }
    }
}
