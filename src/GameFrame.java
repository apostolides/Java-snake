import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameFrame implements KeyListener {
    private final static int UP = 1;
    private final static int DOWN = 2;
    private final static int LEFT = 3;
    private final static int RIGHT = 4;

    public static int defaultHeight = 1000;
    public static int defaultWidth = 1000;
    private JFrame mainFrame = new JFrame("Snake game 0.1");
    private static Snake snake = new Snake();
    private static Node food = new Node();
    private static int score = 0;
    private DrawingBoard G = new DrawingBoard(snake, food,this);

    public boolean running = true;

    public GameFrame() {

        snake.setCoords(defaultWidth/2,defaultHeight/2);
        food.setColor(Color.RED);
        spawnFood(food); //Randomizes food position TODO: avoiding collision with snake

        mainFrame.setBounds(0, 0, defaultWidth, defaultHeight);
        mainFrame.getContentPane().setBackground(Color.BLACK);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.add(G);
        mainFrame.addKeyListener(this);

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);

        mainFrame.setVisible(true);

    }

    public Snake getSnake() {
        return snake;
    }

    public void keyTyped(KeyEvent event) {
        //Not used
    }

    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        snake.setPrevDirection(snake.getDirection());
        if (keyCode == KeyEvent.VK_W) {
            snake.setDirection(UP);
        } else if (keyCode == KeyEvent.VK_S) {
            snake.setDirection(DOWN);
        } else if (keyCode == KeyEvent.VK_A) {
            snake.setDirection((LEFT));
        } else if (keyCode == KeyEvent.VK_D) {
            snake.setDirection(RIGHT);
        }
        else if (keyCode == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }

    }

    public void keyReleased(KeyEvent event) {
        //Not used
    }

    public void render() {
        G.repaint();
    }

    public int getScore(){
        return this.score;
    }

    private static void spawnFood(Node food) {
        Random rand = new Random();
        int x = rand.nextInt(defaultWidth);
        int leftOver = x % snake.getHead().getWidth();
        x-=leftOver;
        int y = rand.nextInt(defaultHeight);
        leftOver = y % snake.getHead().getWidth();
        y-=leftOver;

        x%=defaultWidth;
        y%=defaultHeight;

        if(y<0) {
            y -= snake.getHead().getWidth();
        }

        System.out.printf("Food Spawned at X: %d Y: %d\n",x,y);
        food.setCoords(x,y);
    }

    public static boolean check(){
        if(snake.getHead().getxPos()==food.getxPos() && snake.getHead().getyPos()==food.getyPos()){
            score++;
            snake.grow();
            spawnFood(food);
            System.out.printf("Food eaten, score increased to: %d\n",score);
        }
        if(snake.getHead().getxPos()>defaultWidth-snake.getHead().getWidth() || snake.getHead().getxPos()<0 ||
                snake.getHead().getyPos()>=defaultHeight-snake.getHead().getWidth()*2 ||
                snake.getHead().getyPos()<0){
                    return false;
        }
        else if(snake.getDirection()==DOWN && snake.getPrevDirection()==UP||
        snake.getDirection()==RIGHT && snake.getPrevDirection()==LEFT ||
        snake.getDirection()==LEFT && snake.getPrevDirection()==RIGHT ||
        snake.getDirection()==UP && snake.getPrevDirection()==DOWN){
            return false;
        }
        else{
            for(Node node : snake.getBody()){
                if(snake.getHead().getxPos()==node.getxPos() && snake.getHead().getyPos()==node.getyPos()){
                    return false;
                }
            }
        }
        return true;
    }
}