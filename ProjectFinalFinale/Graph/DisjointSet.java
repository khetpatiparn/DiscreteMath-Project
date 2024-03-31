package Graph;

class DisjointSet {
    private int[] parent;
    private int[] rank; // For optimization

    public DisjointSet(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i; // Initially, each vertex is its own parent
            rank[i] = 0;
        }
    }

    public int find(int u) {
        if (parent[u] != u) {
            parent[u] = find(parent[u]); // Path compression
        }
        return parent[u];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        // Union by rank (optimization)
        if(rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else if (rank[rootY] > rank[rootX]) {
            parent[rootX] = rootY;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}