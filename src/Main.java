import acm.graphics.GMath;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import java.awt.event.*;

public class Main extends GraphicsProgram {

    double x=4;
    double y=-3;
    /** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

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
    private static final int NBRICK_ROWS = 2;

    /** Separation between bricks */
    private static final int BRICK_SEP = 4;

    /** Width of a brick */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /** Height of a brick */
    private static final int BRICK_HEIGHT = 8;

    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 10;

    /** Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 70;

    /** Number of turns */
    private int NTURNS = 3;

    /**paddle's speed*/
    private static final double speed = 0.4;
    GOval ball;
    GRect paddle;
    boolean isStart = false;

    public void setSpeed(){
        x=Math.random()+0.2;
        y=-1*Math.random()*(1-x)+0.2;
    }

    public void run(){

        addMouseListeners();
        setup();
        while(true){
            while(!isStart){

                System.out.println();
            }
            setSpeed();
            while (true){

                moveBall(x,y);
                GObject object = getCollidingObject();
                if (object!=null){
                    if (object.getWidth()!=PADDLE_WIDTH){
                        remove(object);
                    }
                    if (jumpSide(object)==1) x=-x;else y=-y;

                }
                if (checkWalls()){
                    if (wallNumber()==1){
                        x=-x;
                    }
                    if (wallNumber()==2){
                        y=-y;
                    }
                    if (wallNumber()==0){
                        System.out.println("sda");
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
                pause(speed);
            }
            isStart=false;
            remove(ball);
            ball = Ball.createBall(BALL_RADIUS, WIDTH/2, HEIGHT/2, this);
        }

    }

    private void moveBall(double x, double y) {
        ball.move(x,y);
    }

    public void setup(){
        this.setSize(WIDTH,HEIGHT);

       paddle= GPaddle.createPaddle(WIDTH/2-PADDLE_WIDTH/2, HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT,this);
        Brick.createBricks(NBRICKS_PER_ROW,NBRICK_ROWS,BRICK_SEP,BRICK_WIDTH,BRICK_HEIGHT,BRICK_Y_OFFSET,this);
        ball = Ball.createBall(BALL_RADIUS, WIDTH/2, HEIGHT/2, this);

    }
    public void mouseClicked(MouseEvent e){
        isStart=true;
    }


    public void mouseMoved(MouseEvent e)
    {
        if (isStart){
            while(e.getX()>paddle.getX()+PADDLE_WIDTH/2){
                if(paddle.getX()+PADDLE_WIDTH<WIDTH) {
                    paddle.move(speed,0);
                }else {paddle.move(-speed,0);return;}}
            while(e.getX()<paddle.getX()+PADDLE_WIDTH/2){
                if(paddle.getX()>0) paddle.move(-speed,0);else{ paddle.move(speed,0);return;}}
        }
        }

    //1-left/right
    //2-up/bottom
    private int jumpSide(GObject object){
        if(ball.getX()>=object.getX()+object.getWidth()-x) return 1;
        if(ball.getX()+ball.getWidth()-x<=object.getX()) return 1;
        return 2;

    }


    private boolean checkWalls(){
        if(ball.getX()<=0) return true;
        if(ball.getY()<=0) return true;
        if(ball.getX()+BALL_RADIUS>=WIDTH)return true;
        if(ball.getY()+BALL_RADIUS>=HEIGHT)return true;
        return false;
    }

    //1-left/right
    //2-up
    //0-bottom
    private int wallNumber(){
        if(ball.getX()<=0) return 1;
        if(ball.getY()<=0) return 2;
        if(ball.getX()+BALL_RADIUS>=WIDTH)return 1;
        if(ball.getY()+BALL_RADIUS>=HEIGHT)return 0;
        return 0;

    }



    private GObject getCollidingObject(){
        for (int i =0;i<360;i++){
            GObject object = getElementAt(ball.getX()+ball.getWidth()/2+(BALL_RADIUS)* GMath.cosDegrees(i),ball.getY()+ball.getHeight()/2+(BALL_RADIUS)*GMath.sinDegrees(i));

            if (object!=null){
                System.out.println(object);
                return object;
            }
        }
       return null;
    }


}
