import acm.graphics.*;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.awt.event.*;

public class Main extends GraphicsProgram {

    //changing ball coordinates for each move
    double x=1;
    double y=1;
    /** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 850;
    public static final int APPLICATION_HEIGHT = 700;

    /** Dimensions of game board (usually the same) */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /** Dimensions of the paddle */
    private static final double PADDLE_WIDTH = 80;
    private static final int PADDLE_HEIGHT = 8;

    /** Offset of the paddle up from the bottom */
    private static final int PADDLE_Y_OFFSET = 30;

    /** Number of bricks per row */
    private static final int NBRICKS_PER_ROW = 7;

    /** Number of rows of bricks */
    private static final int NBRICK_ROWS = 6;

    /** Separation between bricks */
    private static final int BRICK_SEP = 4;

    /** Width of a brick */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /** Height of a brick */
    private static final int BRICK_HEIGHT = 8;

    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 6;

    /** Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 120;

    /** Number of turns */
    private int NTURNS = 3;

    //pause between next move (less amount is faster speed)
    private static final double speed = 0.1;
    private static final double paddleSpeed = 2;

    //points for 1 brick
    private static final int POINTS_FOR_BRICK=1;

    //table height
    private static final int TABLE_HEIGHT=BRICK_Y_OFFSET-50;


    public static int high_score=0;
    double prev = 0;
    Ball ball;
    GPaddle paddle;
    ScoreTable table;
    boolean isStart = false;
    public static GImage background=new GImage("background.jpg");
    Menu menu;
    boolean isView = true;
    int score=0;
    int level = 1;


    /**
     * change x and y changes for each move
     */

    //TODO change speed for random
    public void setSpeed(double lvl){
        lvl = 1+ lvl/10;
        double p = Math.random()*(Math.PI/2) + Math.PI/4;
        while(Math.sin(p)>=Math.sin(Math.PI/2.2)){
            p = Math.random()*(Math.PI/2) + Math.PI/4;
        }
        y =-lvl* Math.sin(p);
        x=lvl* Math.cos(p);
    }

    public void run(){
        this.setSize(WIDTH,HEIGHT);


        addMouseListeners();
        setup();
        addMenu();
        setSpeed(level);



        while(true){
            //send ball back to avoid problem with ball returning
            ball.sendToBack();
            background.sendToBack();

            //TODO if fix is possible
            //fix it someone please
            while(!isStart){
               System.out.print("");
            }

      /*      Thread tr = new Thread(){
                @Override
                public void run() {
                    try{
                        paddleMove();
                    }catch (Exception e){
                        paddle.move(prev,0);
                        paddle.setCentreX(paddle.getCentreX()+prev);
                    }
                }

            };*/

            //checking for bricks and walls collision, changing speed according to those collisions
            while (true){
             /*   tr.run();*/


                GObject object = getCollidingObject();
                //if there is collision with brick
                if (object!=null){

                    //TODO don`t forget to delete this pause after debugging
                    //pause for debugging(
                  //  pause(500);

                    if (object instanceof Brick){
                        if (((Brick) object).getFillColor()==Color.YELLOW){
                            paddle.setSize(PADDLE_WIDTH/100*70, paddle.getHeight());
                        }
                        if (((Brick) object).getFillColor()==Color.CYAN){
                            paddle.setSize(PADDLE_WIDTH/100*130, paddle.getHeight());
                        }
                        if (((Brick) object).getFillColor()== Color.MAGENTA){
                            score+=2*POINTS_FOR_BRICK*level;
                        }
                        if (((Brick) object).getFillColor()==Color.GREEN){
                            NTURNS++;
                            table.setLives(table.getLives()+1);
                        }
                        if (((Brick) object).getFillColor()==Color.RED){
                            x = x*1.2;
                            y=y*1.2;
                        }
                        score+=POINTS_FOR_BRICK*level;
                        table.setScore(score);
                        switch (jumpSide((Brick) object)){
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
                    } else {
                       // paddle.tr = false;
                        if (paddle.tr==true){
                            paddle.tr=false;
                            y=-y;
                        }

                    }

                    //TODO fix this if, it should be check in getCollision method
                    //fix it, someone, too
                    //check if brick isn`t a paddle
                    if (object.getWidth()!=paddle.getWidth()){
                        remove(object);
                        Brick.bricksNumber--;
                        if (Brick.bricksNumber==0){
                            score+=score/4;
                            table.setScore(score);
                            level++;
                            table.setLevel(level);
                            setSpeed(level);
                            Brick.createBricks(NBRICKS_PER_ROW,NBRICK_ROWS,BRICK_SEP,BRICK_WIDTH,BRICK_HEIGHT,BRICK_Y_OFFSET,this);
                            break;
                        }
                    }
                }
                //checking for walls collision
                //creation new ball if it`s a bottom one
                if (checkWalls()){
                    if (wallNumber()==1){
                        x=-x;
                    }
                    if (wallNumber()==2){
                        y=-y;
                    } else
                    if (wallNumber()==0){
                        if (NTURNS==1){
                            table.minusLives();
                            remove(ball);

                            priintLose();

                            waitForClick();
                            removeAll();
                            setup();
                            addMenu();
                            NTURNS=4;
                            score=0;
                            Brick.bricksNumber=NBRICK_ROWS*NBRICKS_PER_ROW;
                            level = 1;
                            isView=true;

                            table.setScore(score);
                            table.setLives(NTURNS);
                            table.setLevel(level);
                            continue;
                        }else {
                            setSpeed(level);
                            NTURNS--;
                            table.minusLives();
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
            paddle.setSize(PADDLE_WIDTH,PADDLE_HEIGHT);
            add(ball);
        }

    }

    private void addMenu() {
     menu=new Menu(WIDTH,HEIGHT);
     add(menu);

    }
    /**
     * move paddle with some speed to the current cursor position
     */
    public void paddleMove(){
        if (paddle.getX()>0&&paddle.getX()+paddle.getWidth()<WIDTH){
            if (getMousePosition().x>paddle.getCentreX()){
                paddle.move(paddleSpeed,0);
                paddle.setCentreX(paddle.getCentreX()+paddleSpeed);
                prev = paddleSpeed;
            } else if (getMousePosition().x<paddle.getCentreX()){
                paddle.move(-paddleSpeed,0);
                paddle.setCentreX(paddle.getCentreX()-paddleSpeed);
                prev = -paddleSpeed;
            }
        }else if (paddle.getX()<0){
            paddle.move(0.1,0);
            paddle.setCentreX(paddle.getCentreX()+0.1);
            pause(0.1);
        }else {
            paddle.move(-0.1,0);
            paddle.setCentreX(paddle.getCentreX()-0.1);
            pause(0.1);
        }

    }


    public void priintLose(){
        add(ACMMethods.writeText("YOU LOSE", WIDTH/2, HEIGHT/2, 26));
        if(high_score<score)
            high_score=score;
    }

    /**
     * create start bricks, paddle and ball
     */
    public void setup(){
        background.setSize(WIDTH,HEIGHT-TABLE_HEIGHT);
        add(background,0,TABLE_HEIGHT);

        GPaddle pad = new GPaddle(WIDTH/2-PADDLE_WIDTH/2, HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT,this);
        paddle= pad;
        add(paddle);
        Brick.createBricks(NBRICKS_PER_ROW,NBRICK_ROWS,BRICK_SEP,BRICK_WIDTH,BRICK_HEIGHT,BRICK_Y_OFFSET,this);
        ball =new Ball(BALL_RADIUS, WIDTH/2, HEIGHT/2);
        add(ball);

        table= new ScoreTable(WIDTH,TABLE_HEIGHT,NTURNS);
        add(table);

    
    }

    /**
     * Start game for each click
     *
     * @param e
     */
    //TODO waitForClick instead of this

    int optionsCount = 0;
    public void mouseClicked(MouseEvent e){
        System.out.println(Brick.bricksNumber);
        System.out.println(y);
        if (getElementAt(e.getX(),e.getY()) instanceof SeOptions){
           remove(getElementAt(e.getX(),e.getY()));
           remove(menu);
            background.sendToBack();
           add(background);

           add(menu);
           return;
        }else
        if (isView) {
            Object object = menu.getElementAt(e.getX(), e.getY());
            System.out.println(object);
            if (object instanceof GLabel) {
                GLabel label = (GLabel) object;
                if (label.getLabel().equals("START")) {
                    isView = false;
                    remove(menu);
                    remove(background);
                    background = new GImage("background.jpg");
                    background.sendToBack();
                    background.setSize(WIDTH, HEIGHT - TABLE_HEIGHT);
                    add(background, 0, TABLE_HEIGHT);
                    background.sendToBack();

                    return;
                }
                if (label.getLabel().equals("EXIT")){
                    System.exit(1);
                }
                if (label.getLabel().equals("OPTIONS")){
                     SeOptions options = new SeOptions(WIDTH, HEIGHT, BRICK_WIDTH, BRICK_HEIGHT);
                        add(options);

                return;
                }
            }}
        isStart = true;

    }
    public void mouseMoved(MouseEvent e){
        if (e.getX()>paddle.getWidth()/2&&e.getX()<WIDTH-paddle.getWidth()/2)
        paddle.move(e.getX()-(paddle.getX()+paddle.getWidth()/2),0);
    }


    /**
     *
     * @param object brick
     * @return 1 for sides, 2 for bottom and top, 3 for corner
     */
    //TODO more accuracy
    private int jumpSide(Brick object){
        double centreX = object.getCentreX();
        double centreY = object.getCentreY();

        double cornerX1 = object.getX();
        double cornerY1 = object.getY();
        double cornerX4 = object.getX() + object.getWidth();
        double cornerY4 = object.getY() + object.getHeight();

        if (ball.getCentreX()>cornerX1&&ball.getCentreX()<cornerX4){
            return 2;
        }else if (ball.getCentreY()>cornerY1&&ball.getCentreY()<cornerY4){
            return 1;
        } else {
            double x = Math.abs(centreX-ball.getCentreX());
            double y = Math.abs(centreY-ball.getCentreY());
            if (x>y){
                return 2;
            }else if (x<y){
                return 2;
            }else
            return 1;
        }

    }



    /**
     *
     * @return true if there is a wall
     */
    //TODO should return wall number
    private boolean checkWalls(){
        if(ball.getCentreX()-BALL_RADIUS<=0){
            paddle.tr=true;
            return true;
        }
        if(ball.getCentreY()-BALL_RADIUS<=TABLE_HEIGHT) {
            paddle.tr=true;
            return true;
        }
        if(ball.getCentreX()+2*BALL_RADIUS>WIDTH){
           paddle.tr=true;
            return true;
        }
        if(ball.getCentreY()+2*BALL_RADIUS>=HEIGHT){
           paddle.tr=true;
            return true;
        }
        return false;
    }

    /**
     *
     * @return 1 for left and right wall, 2 for upper wall and 0 for bottom wall
     */
    //TODO delete this method or call it in ckeckWalls
    private int wallNumber(){
        if(ball.getCentreX()-BALL_RADIUS<=0) return 1;
        if(ball.getCentreY()-BALL_RADIUS<=TABLE_HEIGHT) return 2;
        if(ball.getCentreX()+BALL_RADIUS+BALL_RADIUS>=WIDTH)return 1;
        if(ball.getCentreY()+BALL_RADIUS+BALL_RADIUS>=HEIGHT)return 0;
        return 0;

    }


    /**
     *
     * @return brick if there is a collision, else return null;
     */
    //TODO more accuracy, don`t return a paddle
    private GObject getCollidingObject(){
        for (int i =0;i<360;i=i+10){
            double ballCentreX = ball.getCentreX()+x/2;
            double ballVectorX = (BALL_RADIUS+0.1)*GMath.cosDegrees(i);
            double ballCentreY =ball.getCentreY()+y/2;
            double ballVectorY = (BALL_RADIUS+0.1)*GMath.sinDegrees(i);

            GObject object = getElementAt(ballCentreX+ballVectorX,ballCentreY+ballVectorY);
            if (object instanceof Brick){
                System.out.println(object);
                Brick brick = (Brick) object;
                brick.setCollisionX(ballCentreX+ballVectorX);
                brick.setCollisionY(ballCentreY+ballVectorY);
                paddle.tr=true;
                return brick;
            } else if (object instanceof GPaddle){
                    return object;
            }
        }
       return null;
    }
}
