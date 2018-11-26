import acm.graphics.GOval;

public class Ball {

    public static GOval createBall(int radius, int startX, int startY, Main main){
        GOval oval = new GOval(startX-radius,startY-radius, radius, radius);
        oval.setFilled(true);
        main.add(oval);
        return oval;
    }
}