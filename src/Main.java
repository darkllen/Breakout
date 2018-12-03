import acm.graphics.GMath;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import java.awt.event.*;

public class Main extends GraphicsProgram {

    //changing ball coordinates for each move
    double x=1;
    double y=1;

    /** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 800;

    /** Dimensions of game board (usually the same) */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /** Dimensions of the paddle */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /** Offset of the paddle up from the bottom */
    private static final int PADDLE_Y_OFFSET = 30;

    /** Number of bricks per row */
    private static final int NBRICKS_PER_ROW = 10;

    /** Number of rows of bricks */
    private static final int NBRICK_ROWS = 10;

    /** Separation between bricks */
    private static final int BRICK_SEP = 4;

    /** Width of a brick */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /** Height of a brick */
    private static final int BRICK_HEIGHT = 28;

    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 10;

    /** Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 70;

    /** Number of turns */
    private int NTURNS = 3;

    //pause between next move (less amount is faster speed)
    private static final double speed = 0.01;
    private static final double paddleSpeed = 1;

    Ball ball;
    GPaddle paddle;
    boolean isStart = false;

    /**
     * change x and y changes for each move
     */

    //TODO change speed for random
    public void setSpeed(){
        y=0.1;
        x=0.1;
    }

    public void run(){
        addMouseListeners();
        setup();

        while(true){
            //send ball back to avoid problem with ball returning
            ball.sendToBack();

            //TODO if fix is possible
            //fix it someone please
            while(!isStart){
               System.out.println();
            }

            //checking for bricks and walls collision, changing speed according to those collisions
            while (true){

                try{
                    paddleMove();
                }catch (Exception e){
                    System.out.println(e);
                }

                Brick object = getCollidingObject();
                //if there is collision with brick
                if (object!=null){

                    //TODO don`t forget to delete this pause after debugging
                    //pause for debugging(
                    pause(500);

                    switch (jumpSide(object)){
                        case 1:
                            x=-x;
                            break;
                        case 2:
                            y=-y;
                            break;
                        case 3:
                            x=-x;
                            y=-y;
                    }
                    //TODO fix this if, it should be check in getCollision method
                    //fix it, someone, too
                    //check if brick isn`t a paddle
                    if (object.getWidth()!=PADDLE_WIDTH){
                        remove(object);
                    }
                }
                //checking for walls collision
                //creation new ball if it`s a bottom one
                if (checkWalls()){
                    if (wallNumber()==1){
                        x=-x;
                    }
                    if (wallNumber()==2 || wallNumber()==0){
                        y=-y;
                    } else
                    if (wallNumber()==0){
                        if (NTURNS==0){
                            return;
                        }else {
                            setSpeed();
                            NTURNS--;
                        }
                        ball.setLocation(WIDTH/2,HEIGHT/2);
                        break;
                    }
                }
                ball.moveBall(x,y);
                //final pause to regulate speed of the game
                pause(speed);
            }
            //create new ball if previous is lost
            isStart=false;
            remove(ball);
            ball =new Ball(BALL_RADIUS, WIDTH/2, HEIGHT/2);
            add(ball);
        }

    }


    /**
     * move paddle with some speed to the current cursor position
     */
    public void paddleMove(){
        if (getMousePosition().x>paddle.getCentreX()){
            paddle.move(paddleSpeed,0);
            paddle.setCentreX(paddle.getCentreX()+paddleSpeed);
        } else if (getMousePosition().x<paddle.getCentreX()){
            paddle.move(-paddleSpeed,0);
            paddle.setCentreX(paddle.getCentreX()-paddleSpeed);
        }
    }

    /**
     * create start bricks, paddle and ball
     */
    public void setup(){
        this.setSize(WIDTH,HEIGHT);
        GPaddle pad = new GPaddle(WIDTH/2-PADDLE_WIDTH/2, HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT,this);
        paddle= pad;
        add(paddle);
        Brick.createBricks(NBRICKS_PER_ROW,NBRICK_ROWS,BRICK_SEP,BRICK_WIDTH,BRICK_HEIGHT,BRICK_Y_OFFSET,this);
        ball =new Ball(BALL_RADIUS, WIDTH/2, HEIGHT/2);
        add(ball);

    }

    /**
     * Start game for each click
     *
     * @param e
     */
    //TODO waitForClick instead of this
    public void mouseClicked(MouseEvent e){
        isStart=true;
    }


    /**
     * Move paddle to the coordinates of mouse (speed is the same as mouse speed)
     *
     * @param e
     */
/*    public void mouseMoved(MouseEvent e) {
        if (isStart){
            while(e.getX()>paddle.getX()+PADDLE_WIDTH/2){
                if(paddle.getX()+PADDLE_WIDTH<WIDTH) {
                    paddle.move(speed,0);
                }else {paddle.move(-speed,0);return;}}
            while(e.getX()<paddle.getX()+PADDLE_WIDTH/2){
                if(paddle.getX()>0) paddle.move(-speed,0);else{ paddle.move(speed,0);return;}}
        }
        }*/

    /**
     *
     * @param object brick
     * @return 1 for sides, 2 for bottom and top, 3 for corner
     */
    //TODO more accuracy
    private int jumpSide(Brick object){

        double cornerX1 = object.getX();
        double cornerY1 = object.getY();
        double cornerX4 = object.getX() + object.getWidth();
        double cornerY4 = object.getY() + object.getHeight();

        if (ball.getCentreX()>cornerX1&&ball.getCentreX()<cornerX4){
            return 2;
        }else if (ball.getCentreY()>cornerY1&&ball.getCentreY()<cornerY4){
            return 1;
        } else return 3;

    }



    /**
     *
     * @return true if there is a wall
     */
    //TODO should return wall number
    private boolean checkWalls(){
        if(ball.getCentreX()-BALL_RADIUS<=0) return true;
        if(ball.getCentreY()-BALL_RADIUS<=0) return true;
        if(ball.getCentreX()+2*BALL_RADIUS>WIDTH)return true;
        if(ball.getCentreY()+2*BALL_RADIUS>=HEIGHT)return true;
        return false;
    }

    /**
     *
     * @return 1 for left and right wall, 2 for upper wall and 0 for bottom wall
     */
    //TODO delete this method or call it in ckeckWalls
    private int wallNumber(){
        if(ball.getCentreX()-BALL_RADIUS<=0) return 1;
        if(ball.getCentreY()-BALL_RADIUS<=0) return 2;
        if(ball.getCentreX()+BALL_RADIUS+BALL_RADIUS>=WIDTH)return 1;
        if(ball.getCentreY()+BALL_RADIUS+BALL_RADIUS>=HEIGHT)return 0;
        return 0;

    }


    /**
     *
     * @return brick if there is a collision, else return null;
     */
    //TODO more accuracy, don`t return a paddle
    private Brick getCollidingObject(){
        for (int i =0;i<360;i++){
            double ballCentreX = ball.getCentreX()+x/2;
            double ballVectorX = (BALL_RADIUS)*GMath.cosDegrees(i);
            double ballCentreY =ball.getCentreY()+y/2;
            double ballVectorY = (BALL_RADIUS)*GMath.sinDegrees(i);

            GObject object = getElementAt(ballCentreX+ballVectorX,ballCentreY+ballVectorY);
            if (object instanceof Brick){
                System.out.println(object);
                Brick brick = (Brick) object;
                brick.setCollisionX(ballCentreX+ballVectorX);
                brick.setCollisionY(ballCentreY+ballVectorY);
                return brick;
            }
        }
       return null;
    }
}
