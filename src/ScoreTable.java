import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

import java.awt.*;

public class ScoreTable extends GCompound{
    private int score;
    private int lives;
    private int level;
    private int width;
    private int height;
    GLabel scoreLabel;
    GLabel levelLabel;
    private int LIVES_BALLS_RADIUS=5;
    private int LIVES_BALLS_SEP=5;
    public ScoreTable(int width,int height,int lives) {
        this.width=width;
        this.height=height;
        GRect table = new GRect(0,0,width,height);
        add(table);
        score=0;
        this.lives=lives;
        level=1;
        scoreLabel=new GLabel("Score = "+score);
        scoreLabel.setLocation(width/10,height/2+scoreLabel.getHeight()/2);
        add(scoreLabel);
        levelLabel= new GLabel("LEVEL: "+level);
        levelLabel.setLocation(width/2-levelLabel.getWidth()/2,height/2+levelLabel.getHeight()/2);
        add(levelLabel);

        addLives();

    }

    private void addLives() {
        int x=width-LIVES_BALLS_SEP-LIVES_BALLS_RADIUS*2;
        int y=height/2-LIVES_BALLS_RADIUS;
        for(int i=0;i<lives;i++){

            GOval ball=new GOval(x,y,LIVES_BALLS_RADIUS*2,LIVES_BALLS_RADIUS*2);
            ball.setFillColor(Color.BLACK);
            ball.setFilled(true);
            add(ball);
            x-=LIVES_BALLS_SEP+LIVES_BALLS_RADIUS*2;
        }

    }

    public void removeLive(){

       // if(getElementAt(width-(LIVES_BALLS_SEP+LIVES_BALLS_RADIUS)*lives+1,height/2-LIVES_BALLS_RADIUS/2)==GOval)
            remove(getElementAt(width-(LIVES_BALLS_SEP+LIVES_BALLS_RADIUS*2)*lives+LIVES_BALLS_RADIUS,height/2));

    }

    public void setScore(int score){
        this.score=score;
        scoreLabel.setLabel("Score = "+score);
    }

    public void setLevel(int level){
        this.level=level;
        remove(levelLabel);
        levelLabel.setLabel("LEVEL: "+level);
        add(levelLabel);
    }

    public void plusLevel(){
        level++;
        remove(levelLabel);
        levelLabel.setLabel("LEVEL: "+level);
        add(levelLabel);
    }

    public void setLives(int lives){
        while(this.lives!=0)
        {
            removeLive();
            this.lives--;
        }
        this.lives=lives;
        addLives();
    }
    public void minusLives(){
        removeLive();
        lives--;

    }

    public int getScore(){
        return score;
    }
    public int getLives(){
        return lives;
    }
    public int getLevel(){
        return level;
    }

}
