import acm.graphics.GRect;
import java.awt.*;

public class Brick extends GRect {

    public static int bricksNumber;
    //bricks colors

    //points where collision occurs (setting when collision happened)
    private double collisionX;
    private double collisionY;

    //brick centre points, setting when brick is created
    private double centreX;
    private double centreY;

    /**
     * creation Brick rectangle as GRect
     * @param v
     * @param v1
     * @param v2
     * @param v3
     */
    public Brick(double v, double v1, double v2, double v3) {
        super(v, v1, v2, v3);
        centreX = v+ v2/2;
        centreY = v1+ v3/2;
    }

    /**
     * Creation of start bricks
     *
     * @param numberBricksPerRow
     * @param numberOfRows
     * @param breakSep distance beetween all bricks (all sides)
     * @param brickWidth
     * @param brickHeigth
     * @param brickYOffset distance from 0 height
     * @param graphicsProgram main window
     */
    public static void createBricks(int numberBricksPerRow, int numberOfRows, int breakSep, int brickWidth, int brickHeigth, int brickYOffset, Main graphicsProgram){
        int x = 0;
        int y = brickYOffset;
        Color color;
        for (int i=0;i<numberOfRows;i++){


            for (int j=0;j<numberBricksPerRow;j++){

                int  colorNum = (int) (Math.random()*101);
                if (colorNum>98)color=Color.GREEN;
                else if (colorNum>95)color = Color.RED;
                else if (colorNum>90)color = Color.YELLOW;
                else if (colorNum>85)color = Color.CYAN;
                else if (colorNum>75)color = Color.MAGENTA;
                else color = Color.BLACK;

                Brick gRect = new Brick(x,y,brickWidth,brickHeigth);
                gRect.setFilled(true);
                gRect.setFillColor(color);
                graphicsProgram.add(gRect);
                bricksNumber++;
                x+=brickWidth+breakSep;
            }
            x=0;
            y+=brickHeigth+breakSep;
        }
    }



    public double getCentreX() {
        return centreX;
    }

    public double getCentreY() {
        return centreY;
    }

    public double getCollisionX() {
        return collisionX;
    }

    public void setCollisionX(double collisionX) {
        this.collisionX = collisionX;
    }

    public double getCollisionY() {
        return collisionY;
    }

    public void setCollisionY(double collisionY) {
        this.collisionY = collisionY;
    }

}
