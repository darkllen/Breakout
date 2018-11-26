import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

public class Brick {
    static Color y0 = Color.RED;
    static Color y1 = Color.ORANGE;
    static Color y2 = Color.YELLOW;
    static Color y3 = Color.GREEN;
    static Color y4 = Color.CYAN;

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
                GRect gRect = new GRect(x,y,brickWidth,brickHeigth);
                gRect.setFilled(true);
                gRect.setFillColor(color);
                graphicsProgram.add(gRect);
                x+=brickWidth+breakSep;
            }
            x=0;
            y+=brickHeigth+breakSep;
        }

    }


}
