public class Edge {
    private String name;
    private int src, dst;
    private int w;

    public Edge(String name, int src, int dst, int w){
        this.name = name;
        this.src = src;
        this.dst = dst;
        this.w = w;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public int getDst() {
        return dst;
    }

    public void setDst(int dst) {
        this.dst = dst;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

}
