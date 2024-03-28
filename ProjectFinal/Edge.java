
    public class Edge {
        private String name;
        private int src, dst;
        private int w;
        
        public Edge(int src, int dst, int w){
            this.src = src;
            this.dst = dst;
            this.w = w;
        }
        public Edge(int src, int dst){
            this.src = src;
            this.dst = dst;
        }
        
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
    
        public int getWeight() {
            return w;
        }
    
        public void setWeight(int w) {
            this.w = w;
        }
        @Override
        public String toString() {
            return "Edge [name=" + name + ", src=" + src + ", dst=" + dst + ", w=" + w + "]";
        }
    
    }

