import java.awt.Color;
import acm.program.*;
import acm.graphics.*;

public class GPaddle {

    public static Brick createPaddle(int x,int y,int width,int height, Main main) {
         Brick paddle = new Brick(x,y,width,height);
         paddle.setFilled(true);
         main.add(paddle);
         return(paddle);


    }

}
