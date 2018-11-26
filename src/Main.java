import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import java.awt.event.*;

public class Main extends GraphicsProgram {

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
    private static final int NTURNS = 3;

    /**paddle's speed*/
    private static final double speed = 1;
    GOval ball;
    GRect paddle;
    boolean isStart = false;

    public void init(){
        addMouseListeners();
        setup();
    }
    public void setup(){
        this.setSize(WIDTH,HEIGHT);

       paddle= GPaddle.createPaddle(WIDTH/2-PADDLE_WIDTH/2, HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT,this);
        Brick.createBricks(NBRICKS_PER_ROW,NBRICK_ROWS,BRICK_SEP,BRICK_WIDTH,BRICK_HEIGHT,BRICK_Y_OFFSET,this);
        ball = Ball.createBall(BALL_RADIUS, WIDTH/2, HEIGHT/2, this);

    }
    public void mouseClicked(){
        isStart=true;
    }
    public void mouseMoved(MouseEvent e)
    {
       while(e.getX()>paddle.getX()+PADDLE_WIDTH/2){
           if(paddle.getX()+PADDLE_WIDTH<WIDTH) paddle.move(speed,0);else {paddle.move(-speed,0);return;}}
        while(e.getX()<paddle.getX()+PADDLE_WIDTH/2){
          if(paddle.getX()>0) paddle.move(-speed,0);else{ paddle.move(speed,0);return;}}
    }
}
