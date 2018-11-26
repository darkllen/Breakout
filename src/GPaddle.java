import java.awt.Color;
import acm.program.*;
import acm.graphics.*;

public class GPaddle {

    public static void createPaddle(int x,int y,int width,int height, Main main) {
         GRect paddle = new GRect(x,y,width,height);
         paddle.setFilled(true);
         main.add(paddle);


    }

}
