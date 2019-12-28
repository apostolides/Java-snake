import java.awt.Color;
import java.util.Vector;

public class Snake{

    private final static int UP = 1;
    private final static int DOWN = 2;
    private final static int LEFT = 3;
    private final static int RIGHT = 4;


    private Node head = new Node();
    private Vector<Node> body = new Vector<Node>();
    private Color color = Color.GREEN;
    private int len = 0;
    private int speed = head.getWidth();
    private int direction = UP;
    private int prevDirection = 0;

    public Snake(){
        head.setColor(color);
    }

    public int length(){
        return len;
    }

    public void grow(){
        Node tmp = new Node();
        tmp.setColor(this.color);
        if(len==0){
            //First body node, should spawn behind head
            tmp.setCoords(head.getPrevxPos(),head.getPrevyPos());
        }
        else{
            //Spawn behind body tail
            tmp.setCoords(body.get(len-1).getPrevxPos(),body.get(len-1).getPrevyPos());
        }
        body.add(tmp);
        len++;
    }

    //Αdvances Snake
    public void advance() {
        //Update head
        if(direction==UP){
            this.head.updatePosition(this.head.getxPos(),this.head.getyPos()-speed);
        }
        else if(direction==DOWN){
            this.head.updatePosition(this.head.getxPos(),this.head.getyPos()+speed);
        }
        else if(direction==LEFT){
            this.head.updatePosition(this.head.getxPos()-speed,this.head.getyPos());
        }
        else if(direction==RIGHT){
            this.head.updatePosition(this.head.getxPos()+speed,this.head.getyPos());
        }

        //Update body
        if(len>0) {
            this.body.get(0).updatePosition(this.head.getPrevxPos(), this.head.getPrevyPos());
            for (int i = 1; i < len; i++) {
                this.body.get(i).updatePosition(this.body.get(i - 1).getPrevxPos(), this.body.get(i - 1).getPrevyPos());
            }
        }
    }

    public void setDirection(int direction){
        this.direction = direction;
    }

    public void setPrevDirection(int direction){
        this.prevDirection = direction;
    }

    public int getPrevDirection(){
        return prevDirection;
    }

    public int getDirection(){
        return direction;
    }

    public Node getHead(){
        return head;
    }

    public Vector<Node> getBody(){
        return body;
    }

    public Color getColor(){
        return color;
    }

    public void setCoords(int x,int y){
        head.setCoords(x,y);
    }
}
