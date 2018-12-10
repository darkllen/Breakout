import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

import java.awt.*;

public class Menu extends GCompound {
    Color my_color=new Color(169,157,212);
    public Menu(int WIDTH,int HEIGHT) {
        Main.background.setSize(WIDTH,HEIGHT);
        add(Main.background);

        int rect_height=HEIGHT/9;
        int rect_width=WIDTH/3;
        GRect start=new GRect(rect_width,rect_height,rect_width,rect_height);
        start.setFillColor(my_color);
        start.setFilled(true);
        add(start);

        GRect options=new GRect(rect_width,rect_height*3,rect_width,rect_height);
        options.setFillColor(my_color);
        options.setFilled(true);
        add(options);

        GRect exit=new GRect(rect_width,rect_height*5,rect_width,rect_height);
        exit.setFillColor(my_color);
        exit.setFilled(true);
        add(exit);

        GRect H_S=new GRect(rect_width,rect_height*7,rect_width,rect_height);
        H_S.setFillColor(my_color);
        H_S.setFilled(true);
        add(H_S);

        GLabel start_l=new GLabel("START");
        add(start_l,WIDTH/2-start_l.getWidth()/2,rect_height*2-rect_height/2+start_l.getHeight()/2);

        GLabel options_l=new GLabel("OPTIONS");
        add(options_l,WIDTH/2-options_l.getWidth()/2,rect_height*4-rect_height/2+options_l.getHeight()/2);

        GLabel exit_l=new GLabel("EXIT");
        add(exit_l,WIDTH/2-exit_l.getWidth()/2,rect_height*6-rect_height/2+exit_l.getHeight()/2);

        GLabel H_S_l=new GLabel("HIGH SCORE: "+Main.high_score);
        add(H_S_l,WIDTH/2-H_S_l.getWidth()/2,rect_height*8-rect_height/2+H_S_l.getHeight()/2);
    }

}
