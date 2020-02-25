import processing.core.PApplet;
import texturing.Color;
import texturing.Resource;

public class ProgrammingProject extends PApplet{
    public static PApplet processing;
    public static void main(String... args){
        PApplet.main("ProgrammingProject");
    }
    private Color color = new Color(0,0,0);

    public PObject[] buttone = new PObject[3];
    private Slider slider;

    public void settings(){
        size(1800,1000);
    }
    Resource test;


    public void setup(){
        processing = this;
        slider = new Slider(new Vector(800,200),10,1,100,100,"slider",loadImage("face.png"));
        Color[] colors = new Color[]{new Color(255,10,10),
                new Color(10,255,10),new Color(10,10,255) };
        for(int yOffset =1; yOffset<= buttone.length; yOffset++){
            buttone[yOffset-1] = new Button(new Vector(width/2, (180+20)*yOffset), 80, 80,"test",  loadImage("test.png"));
            buttone[yOffset-1].setAsngle((float) (-Math.PI/2));
            if((yOffset-1)<colors.length){
               buttone[yOffset-1].setColour(colors[yOffset-1]);
            }
        }
        background(0);
    }

    public void draw(){

        int[] c = color.get();
        background(c[0],c[1],c[2]);
        slider.tick(Force.NONE);
        for(int i=0; i<buttone.length; i++) {
            buttone[i].tick(new Force(new Vector(0,0),0,0));
            if(buttone[i].mouseOver()&&!(buttone[i] instanceof Explosion)){
                if(mousePressed) {
                    Color color2 = buttone[i].getColour();
                    buttone[i] = new Explosion(buttone[i].W, 200, buttone[i], 0.0075f, 256, ProgrammingProject.processing.loadImage("boom_3.png"));
                    buttone[i].setAsngle((float) (-Math.PI / 2));
                    buttone[i].setColour(color2);
                    color = buttone[i].getColour();
                }
            }
            if(buttone[i].toDeleat()) {
                Color color = buttone[i].getColour();
                buttone[i] = new Button(buttone[i].position, 80, 80, "", loadImage("nuke.png"));
                buttone[i].setAsngle((float) (-Math.PI/2));
                buttone[i].setColour(color);
            }
        }

    }
}
