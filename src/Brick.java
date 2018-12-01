import acm.graphics.GRect;
import java.awt.*;

public class Brick extends GRect {
    //bricks colors
    static Color y0 = Color.RED;
    static Color y1 = Color.ORANGE;
    static Color y2 = Color.YELLOW;
    static Color y3 = Color.GREEN;
    static Color y4 = Color.CYAN;

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
        Color color = y0;
        for (int i=0;i<numberOfRows;i++){
            if (i>1)color=y1;
            if (i>3)color=y2;
            if (i>5)color=y3;
            if (i>7)color=y4;
            for (int j=0;j<numberBricksPerRow;j++){
                Brick gRect = new Brick(x,y,brickWidth,brickHeigth);
                gRect.setFilled(true);
                gRect.setFillColor(color);
                graphicsProgram.add(gRect);
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
