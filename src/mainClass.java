public class mainClass {
    public static void main(String [] args){
        GameFrame gameFrame = new GameFrame();

        gameFrame.getSnake().grow();

        boolean running = true;
        int pauseTime = 100;
        //Main Game Loop
        while(gameFrame.running){

            gameFrame.render();
            gameFrame.getSnake().advance();
            if(!gameFrame.check()){
                gameFrame.running = false;
                gameFrame.render();
            }

            try {
                Thread.sleep(pauseTime-(gameFrame.getScore()/5)*10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


     }
}
