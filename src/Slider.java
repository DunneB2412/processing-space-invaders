import processing.core.PImage;

public class Slider extends Button{
    private Float percent;
    private final Button button;

    Slider(Vector position,int wM, int hM, int w, int h, String name, PImage image) {
        super(position, w*wM, h*hM, name,0, ProgrammingProject.processing.loadImage("test.png"));
        this.button = new Button(new Vector(position.X-(W/2-w/2),position.Y),w,h, name, -20, image);
    }
    @Override
    public void tick(Force force){
        super.tick(Force.NONE);
        if(button.isPressed()||this.isPressed()){
            button.position = followMouse();
        }
        percent = 100+(((button.position.X-((position.X+W/2)-button.W/2))/(W-button.W))*100);
        System.out.println(percent);
        button.setAsngle((float) (((Math.PI*4)/100)*percent));
        button.tick(Force.NONE);

    }

    private Vector followMouse() {
        int x = (int) Math.max(Math.min(ProgrammingProject.processing.mouseX,position.X+(W/2-button.W/2)),position.X-(W/2-button.W/2));
        int y = (int) Math.max(Math.min(ProgrammingProject.processing.mouseY,position.Y+(H/2-button.H/2)),position.Y-(H/2-button.H/2));
        return new Vector(x,y);
    }

}
