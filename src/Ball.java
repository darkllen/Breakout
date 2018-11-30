import acm.graphics.*;

import java.awt.*;

import static acm.util.JTFTools.pause;

public class Ball extends GCompound {

    private double centreX;
    private double centreY;

    public Ball(double radius, double centreX, double centreY) {
    for (int i = 0; i<360; i++){
        GLine point = new GLine(centreX+radius*GMath.cosDegrees(i), centreY+radius*GMath.sinDegrees(i),centreX+radius*GMath.cosDegrees(i), centreY+radius*GMath.sinDegrees(i));
        this.add(point);

    }
        this.centreX=centreX;
        this.centreY=centreY;
    this.setColor(Color.RED);
    }

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
