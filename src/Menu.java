import acm.graphics.GCompound;
import acm.graphics.GRect;

public class Menu extends GCompound {
    public void create(int width, int height){
        this.add(new GRect(0,0,width,height));
    }
}
