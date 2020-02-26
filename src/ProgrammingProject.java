import processing.core.PApplet;
import texturing.Color;

import java.util.ArrayList;

public class ProgrammingProject extends PApplet{
    public static PApplet processing;
    public static void main(String... args){
        PApplet.main("ProgrammingProject");
    }

    public static final Screen[] screens = new Screen[5];//maby use a list later
    public static int screenPointer = 0;
    ArrayList<Integer> c;

    public void settings(){
        size(1800,1000);
    }


    public void setup(){
        processing = this;
        c = new ArrayList<>();
        screens[0] = new Screen(c,0, Color.BLACK);
        screens[1] = new Screen(c,1, Color.RED);
        screens[2] = new Screen(c,2, Color.GREEN);
        screens[3] = new Screen(c,3, Color.BLUE);
        screens[4] = new Screen(c,4, Color.GREIGH);

        for(int yOffset =1; yOffset<=4; yOffset++){
            screens[0].addButton(new Button(new Vector(width/2, (180+20)*yOffset), 80, 80,"test", yOffset, loadImage("test.png")));
        }
        screens[4].addButton(new Slider(new Vector(800,200),10,1,100,100,"slider",loadImage("ghost.png")));
        background(0);
    }

    public void draw(){
        background(0);

        screens[screenPointer].tick();

    }
}
