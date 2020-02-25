import processing.core.PImage;


public class Button extends ImagePObject {
    public static final Button CLIOSE_BUTTON = new Button(new Vector(10,10),10,10,"close", ProgrammingProject.processing.loadImage("close.png"));
    private final String name;

    Button(Vector position, int w, int h,String name , PImage image) {
        super(position, w, h, image);//, Util.getBorder(image),Util.getfader(image));
        this.name = name;
    }

    @Override
    void tick(Force eForce) {
        super.tick(Force.NONE);
    }

    @Override
    public void sprite(){
        ProgrammingProject.processing.image(textures[0],-W/2,-H/2);
        if(mouseOver()){
            //ProgrammingProject.processing.image(textures[textures.length-1],-W/2,-H/2);
            //ProgrammingProject.processing.image(textures[textures.length-2],-W/2,-H/2);

        }
    }
    public boolean isPressed(){
        return this.mouseOver()&&ProgrammingProject.processing.mousePressed;
    }

}
