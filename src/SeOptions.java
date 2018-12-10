import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

import java.awt.*;

public class SeOptions extends GCompound {
    Color my_color = new Color(169, 157, 212);

    public SeOptions(int WIDTH, int HEIGHT,int BRICK_WIDTH,int BRICH_HEIGHT) {
        Main.background.setSize(WIDTH, HEIGHT);
        add(Main.background);

        GLabel title = new GLabel("MEANING OF THE BLOCK`S COLOR");
        title.setFont("Algerian-20");
        add(title,WIDTH/2-title.getWidth()/2,HEIGHT/9);

        GRect Brick=new GRect(WIDTH/6,(HEIGHT - HEIGHT/9 )/6 +20,BRICK_WIDTH,BRICH_HEIGHT);
        Brick.setFilled(true);
        Brick.setFillColor(Color.BLACK);
        add(Brick);
        add(new GLabel("- REGULAR BRICK",WIDTH/6+BRICK_WIDTH*3/2,(HEIGHT - HEIGHT/9 )/6 +30));

        GRect Brick2=new GRect(WIDTH/6,(HEIGHT - HEIGHT/9 )/6 +20,BRICK_WIDTH,BRICH_HEIGHT);
        Brick2.setFilled(true);
        Brick2.setFillColor(Color.GREEN);
        Brick2.move(0,HEIGHT/9);
        add(Brick2);
        add(new GLabel("- LIVE BRICK",WIDTH/6+BRICK_WIDTH*3/2,(HEIGHT - HEIGHT/9 )/6 +30+HEIGHT/9));

        GRect Brick3=new GRect(WIDTH/6,(HEIGHT - HEIGHT/9 )/6 +20,BRICK_WIDTH,BRICH_HEIGHT);
        Brick3.setFilled(true);
        Brick3.setFillColor(Color.RED);
        Brick3.move(0,HEIGHT/9*2);
        add(Brick3);
        add(new GLabel("- SPEED BRICK",WIDTH/6+BRICK_WIDTH*3/2,(HEIGHT - HEIGHT/9 )/6 +30+HEIGHT/9*2));

        GRect Brick4=new GRect(WIDTH/6,(HEIGHT - HEIGHT/9 )/6 +20,BRICK_WIDTH,BRICH_HEIGHT);
        Brick4.setFilled(true);
        Brick4.setFillColor(Color.YELLOW);
        Brick4.move(0,HEIGHT/9*3);
        add(Brick4);
        add(new GLabel("- SMALL PADDLE BRICK",WIDTH/6+BRICK_WIDTH*3/2,(HEIGHT - HEIGHT/9 )/6 +30+HEIGHT/9*3));

        GRect Brick5=new GRect(WIDTH/6,(HEIGHT - HEIGHT/9 )/6 +20,BRICK_WIDTH,BRICH_HEIGHT);
        Brick5.setFilled(true);
        Brick5.setFillColor(Color.CYAN);
        Brick5.move(0,HEIGHT/9*4);
        add(Brick5);
        add(new GLabel("- BIG PADDLE BRICK",WIDTH/6+BRICK_WIDTH*3/2,(HEIGHT - HEIGHT/9 )/6 +30+HEIGHT/9*4));

        GRect Brick6=new GRect(WIDTH/6,(HEIGHT - HEIGHT/9 )/6 +20,BRICK_WIDTH,BRICH_HEIGHT);
        Brick6.setFilled(true);
        Brick6.setFillColor(Color.MAGENTA);
        Brick6.move(0,HEIGHT/9*5);
        add(Brick6);
        add(new GLabel("- X3 SCORE BRICK",WIDTH/6+BRICK_WIDTH*3/2,(HEIGHT - HEIGHT/9 )/6 +30+HEIGHT/9*5));


        GRect exit=new GRect(WIDTH/3,HEIGHT/9*7+20,WIDTH/3,HEIGHT/9);
        exit.setFillColor(my_color);
        exit.setFilled(true);
        add(exit);
        GLabel exit_l=new GLabel("EXIT");
        exit_l.setFont("Algerian-20");
        add(exit_l,WIDTH/2-exit_l.getWidth()/2,HEIGHT/9*8-HEIGHT/9/2+20+exit_l.getHeight()/2 -10);

    }
}
