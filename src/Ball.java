import acm.graphics.*;
import java.awt.*;

public class Ball extends GCompound {

    //centre coordinates
    private double centreX;
    private double centreY;

    /**
     * create ball from 360 points and polygon (also 360 points)
     *
     * @param radius radius of ball
     * @param centreX centre X ball point
     * @param centreY centre Y ball point
     */
    public Ball(double radius, double centreX, double centreY) {
        GPolygon polygon = new GPolygon();
        this.add(polygon);
    for (int i = 0; i<360; i++){
        GLine point = new GLine(centreX+radius*GMath.cosDegrees(i), centreY+radius*GMath.sinDegrees(i),centreX+radius*GMath.cosDegrees(i), centreY+radius*GMath.sinDegrees(i));
        this.add(point);
        polygon.addVertex(centreX+radius*GMath.cosDegrees(i), centreY+radius*GMath.sinDegrees(i));
    }
        this.centreX=centreX;
        this.centreY=centreY;
        polygon.setFilled(true);
    this.setColor(Color.RED);
    }

    /**
     * Move ball by x,y and change centre according to new coordinates
     *
     * @param x
     * @param y
     */
    public void moveBall(double x, double y) {
        this.move(x,y);
        centreX+=x;
        centreY+=y;
    }

    public double getCentreX() {
        return centreX;
    }

    public void setCentreX(double centreX) {
        this.centreX = centreX;
    }

    public double getCentreY() {
        return centreY;
    }

    public void setCentreY(double centreY) {
        this.centreY = centreY;
    }
}
