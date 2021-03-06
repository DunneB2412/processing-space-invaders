import processing.core.PImage;
import texturing.Color;


public class Button extends PObject {
    public static final Button CLIOSE_BUTTON = new Button(new Vector(10,10),10,10,"close", -1, ProgrammingProject.processing.loadImage("face.png"));
    private final String name;
    private int state, timer;
    private final int code;

    Button(Vector position, int w, int h,String name , int code, PImage image) {
        super(position, w, h,1, Color.BLACK, image, Util.getBorder(image),Util.getfader(image));
        this.name = name;
        this.code = code;
        state = 0;
        timer = 0;
    }

    @Override
    void tick(Force eForce) {
        super.tick(Force.NONE);
        if(timer>0){
            timer--;
        }
    }

    @Override
    public void sprite(){

        ProgrammingProject.processing.image(resources[state],-W/2,-H/2);

        if(mouseOver()){
                ProgrammingProject.processing.image(resources[resources.length-1],-W/2,-H/2);
                ProgrammingProject.processing.image(resources[resources.length-2],-W/2,-H/2);
        }
    }
    public boolean isPressed(){
        return this.mouseOver()&&ProgrammingProject.processing.mousePressed&&this.timer==0;
    }

    public int getEventCode() {
        return code;
    }
    public void setState(int state, int cooldown){
        if(state<resources.length&&timer==0){
            this.state=state;
            timer = cooldown;
        }
    }
}
