package Graph;

public class VertexPos {
    private int positionX;
    private int positionY;

    public VertexPos(int posX, int posY){
        this.positionX = posX;
        this.positionY = posY;
    }

    @Override
    public String toString() {
        return "VertexPos [positionX=" + positionX + ", positionY=" + positionY + "]";
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
