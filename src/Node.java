import java.awt.Color;

public class Node {
    private int height,width,xPos,yPos;
    private int prevxPos,prevyPos;
    private Color color;

    public Node(){
        height = 20;
        width = 20;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void updatePosition(int xPos,int yPos){
        this.prevxPos = this.xPos;
        this.prevyPos = this.yPos;
        this.xPos = xPos;
        this.yPos = yPos;
    }
    public int getPrevxPos(){
        return prevxPos;
    }

    public int getPrevyPos(){
        return prevyPos;
    }

    public int getxPos(){
        return xPos;
    }

    public int getyPos(){
        return yPos;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public Color getColor(){
        return color;
    }

    public void setCoords(int x,int y){
        xPos = x;
        yPos = y;
    }

}
