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
    private static final int BRICK_HEIGHT = 8;

    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 10;

    /** Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 70;

    /** Number of turns */
    private int NTURNS = 3;

    /**paddle's speed*/
    private static final double speed = 0.01;
    Ball ball;
    GRect paddle;
    boolean isStart = false;

    public void setSpeed(){
        y=0.1;
        x=0.1;
    }

    public void run(){

        addMouseListeners();
        setup();
        while(true){
            ball.sendToBack();
            while(!isStart){
               System.out.println();
            }
            while (true){

                ball.moveBall(x,y);
                GObject object = getCollidingObject();
                if (object!=null){
                    if (jumpSide(object)==1) x=-x;else y=-y;

                    if (object.getWidth()!=PADDLE_WIDTH){
                        remove(object);
                    }

                }
                if (checkWalls()){
                    if (wallNumber()==1){
                        x=-x;
                    }
                    if (wallNumber()==2 || wallNumber()==0){
                        y=-y;
                    } else
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
            ball =new Ball(BALL_RADIUS, WIDTH/2, HEIGHT/2);
            add(ball);
        }

    }



    public void setup(){
        this.setSize(WIDTH,HEIGHT);

       paddle= GPaddle.createPaddle(WIDTH/2-PADDLE_WIDTH/2, HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT,this);
        Brick.createBricks(NBRICKS_PER_ROW,NBRICK_ROWS,BRICK_SEP,BRICK_WIDTH,BRICK_HEIGHT,BRICK_Y_OFFSET,this);
        ball =new Ball(BALL_RADIUS, WIDTH/2, HEIGHT/2);
        add(ball);

    }
    public void mouseClicked(MouseEvent e){
        isStart=true;
    }


    public void mouseMoved(MouseEvent e)

    {
        System.out.println(getElementAt(e.getX(),e.getY()));
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
        if(ball.getCentreX()-BALL_RADIUS>=object.getX()+object.getWidth()) return 1;
        if(ball.getCentreX()+BALL_RADIUS<=object.getX()) return 1;
        return 2;

    }


    private boolean checkWalls(){
        if(ball.getCentreX()-BALL_RADIUS<=0) return true;
        if(ball.getCentreY()-BALL_RADIUS<=0) return true;
        if(ball.getCentreX()+2*BALL_RADIUS>WIDTH)return true;
        if(ball.getCentreY()+2*BALL_RADIUS>=HEIGHT)return true;
        return false;
    }

    //1-left/right
    //2-up
    //0-bottom
    private int wallNumber(){
        if(ball.getCentreX()-BALL_RADIUS<=0) return 1;
        if(ball.getCentreY()-BALL_RADIUS<=0) return 2;
        if(ball.getCentreX()+BALL_RADIUS+BALL_RADIUS>=WIDTH)return 1;
        if(ball.getCentreY()+BALL_RADIUS+BALL_RADIUS>=HEIGHT)return 0;
        return 0;

    }



    private GObject getCollidingObject(){
        for (int i =0;i<360;i++){
            double ballCentreX = ball.getCentreX();
            double ballVectorX = (BALL_RADIUS+1.01)*GMath.cosDegrees(i);
            double ballCentreY =ball.getCentreY();
            double ballVectorY = (BALL_RADIUS+1.01)*GMath.sinDegrees(i);
            double  kx = 0;
            double  ky = 0;

          /*  if (i<90){
             kx=1.1;
             ky=-1.1;
            }else
            if (i<180){
                kx=-0.1;
                ky=-0.1;
            }else
            if (i<270){
                kx=-0.1;
                ky=0.1;
            }else
            if (i<360){
                kx=0.1;
                ky=0.1;
            }*/

            GObject object = getElementAt(ballCentreX+ballVectorX+kx,ballCentreY+ballVectorY);
            if (object instanceof Ball == false){
                System.out.println(i);
                System.out.println(ballCentreX);
                System.out.println(ballCentreY);
                System.out.println(ballVectorX);
                System.out.println(ballVectorY);
                System.out.println(ballCentreX+ballVectorX+kx);
                System.out.println(ballCentreY+ballVectorY+ky);
                System.out.println(object);
                return object;
            }
        }
       return null;
    }



}
