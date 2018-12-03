import acm.graphics.GCompound;
import acm.graphics.GRect;

public class GPaddle extends GRect {


  private   double centreX;
   private   double centreY;


    public GPaddle(double x, double y, double width, double height, Main main) {
        super(x,y,width,height);
        this.setFilled(true);
        centreX = x+width/2;
        centreY = y+height/2;
    }

    /**
     *
     * @param x start x paddle point
     * @param y start y paddle point
     * @param width paddle width
     * @param height paddle height
     * @param main main class to draw paddle
     * @return return Brick paddle object
     */
    public  Brick createPaddle(int x,int y,int width,int height, Main main) {
         Brick paddle = new Brick(x,y,width,height);
         paddle.setFilled(true);
         main.add(paddle);
         centreX = x+width/2;
         centreY = y+height/2;
         return(paddle);
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

