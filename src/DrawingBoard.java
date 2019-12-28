import javax.swing.*;
import java.awt.*;

public class DrawingBoard extends JComponent{

    private Snake snake;
    private Node food;
    private GameFrame gameFrame;

    public DrawingBoard(Snake snake,Node food,GameFrame gameFrame){
        super();
        this.snake = snake;
        this.food = food;
        this.gameFrame = gameFrame;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        //Set food color
        g.setColor(this.food.getColor());
        //Draw food
        g.fillRect(food.getxPos(),food.getyPos(),food.getWidth(),food.getHeight());
        //Set snake color
        g.setColor(this.snake.getColor());
        //Draw snake head
        g.fillRect(this.snake.getHead().getxPos(),this.snake.getHead().getyPos(),this.snake.getHead().getWidth(),this.snake.getHead().getHeight());
        //Draw snake body
        for(int i=0;i<this.snake.length();i++){
            g.fillRect(this.snake.getBody().get(i).getxPos(),this.snake.getBody().get(i).getyPos(),this.snake.getHead().getWidth(),this.snake.getHead().getHeight());
        }
        g.setColor(Color.WHITE);
        g.drawString("Score: " + gameFrame.getScore(),3,15);

        if(!gameFrame.running){
            g.setColor(Color.RED);
            g.fillRect(GameFrame.defaultWidth/2-150,GameFrame.defaultHeight/2-100,300,100);
            g.setColor(Color.BLACK);
            g.drawString("Game Over :(",GameFrame.defaultWidth/2-45,GameFrame.defaultHeight/2-60);
            g.drawString("Score: " + gameFrame.getScore(),GameFrame.defaultWidth/2-30,GameFrame.defaultHeight/2-30);
        }

        Toolkit.getDefaultToolkit().sync();
    }

}
