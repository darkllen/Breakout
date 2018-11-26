import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

public class Brick {

    public static void createBricks(int numberBricksPerRow, int numberOfRows, int breakSep, int brickWidth, int brickHeigth, int brickYOffset, Main graphicsProgram){
        int x = 0;
        int y = brickYOffset;
        for (int i=0;i<numberOfRows;i++){
            for (int j=0;j<numberBricksPerRow;j++){
                GRect gRect = new GRect(x,y,brickWidth,brickHeigth);
                graphicsProgram.add(gRect);
                x+=brickWidth+breakSep;
            }
            x=0;
            y+=brickHeigth+breakSep;
        }

    }


}
