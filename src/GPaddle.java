public class GPaddle {
    /**
     *
     * @param x start x paddle point
     * @param y start y paddle point
     * @param width paddle width
     * @param height paddle height
     * @param main main class to draw paddle
     * @return return Brick paddle object
     */
    public static Brick createPaddle(int x,int y,int width,int height, Main main) {
         Brick paddle = new Brick(x,y,width,height);
         paddle.setFilled(true);
         main.add(paddle);
         return(paddle);
    }
}
