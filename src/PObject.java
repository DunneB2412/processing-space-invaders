import processing.core.PImage;
import texturing.Color;
import texturing.Resource;

public class PObject extends HitBox{
    protected Force localForce;
    protected PImage[] resources;
    private Color colour;
    private float weight;

    PObject(Vector position, int w, int h){
        this(position, w, h, 1, new Color(100,100,100,255));//default color
    }
    PObject(Vector position, int w, int h, float weigth, Color colour, PImage... pImages){
        super(position, w, h);
        this.weight = weigth;
        localForce = new Force(new Vector(0,0), 0, this.weight);
        this.colour = colour;
        resources = pImages;
        for(PImage image: resources){
            image.resize(w,h);
        }

    }
    void tick(Force eForce){
        if(IFraims>0){
            IFraims --;
        }
        this.localForce.add(eForce);
        position.add(localForce.momentom);
        angle += (2*Math.PI)*localForce.rpt;
        show();
    }
    @Override
    void sprite(){
        showResource((int)ProgrammingProject.processing.random(resources.length));
    }
    protected void showResource(int index){
        if(resources.length>0){
            ProgrammingProject.processing.image(resources[Math.max(Math.min(index,resources.length),0)],-W/2,-H/2);
        }
        else{
            int[] colour = this.colour.get();
            ProgrammingProject.processing.fill(colour[0], colour[1], colour[2]);
            ProgrammingProject.processing.rect(-W/2,-H/2,W,H);
        }
    }

    HitBox cloneHitBox(){
        return super.clone();
    }
    PObject spawnAt(Vector position){
        return new PObject(position, W,H,weight, colour, resources);
    }
    PObject[] deathSpawns(){
        return new PObject[]{new Explosion((float)Math.sqrt((W*W)+(H*H)),100, this, (float)0.02, 16)};
    }
    public Color getColour(){
        return colour;
    }
    public void setColour(Color colour){
        this.colour=colour;
    }
}
