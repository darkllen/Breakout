import acm.graphics.GLabel;
import acm.graphics.GOval;

import java.awt.*;

public final class ACMMethods {
    public static int getCentreX(int width){
      return width/2;
    }
    public static int getCentreY(int height){
        return height/2;
    }

    public static GLabel writeText(String text, int centreX, int centreY, int size){
        GLabel label = new GLabel(text);
        label.setFont("SansSherif-"+size);
        label.setLocation(centreX-label.getWidth()/2, centreY+label.getHeight()/2);
        return label;
    }
    public static GOval addCircle(int x, int y, int radius, boolean color){
        int startW = x-radius;
        int startH = y-radius;
        GOval oval = new GOval(startW,startH,2*radius, 2*radius);
        oval.setFilled(true);
        if (color==true)
            oval.setFillColor(Color.RED);
        else oval.setFillColor(Color.WHITE);
       return oval;
    }
}
