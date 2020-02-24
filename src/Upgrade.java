import processing.core.PImage;

public class Upgrade extends ImagePObject{
    private final int type;
    public final float power;
    private int uces;


    Upgrade(Vector position, int p, int d, float s, PImage... pImages){
        super(position, 40, 40,pImages);
        type = p;
        power = s;
        uces = d;

    }
    void tick(Force eForce){
        super.tick(new Force(new Vector(0,+0.01f),0,0));
        ProgrammingProject.processing.image(textures[(int)ProgrammingProject.processing.random(textures.length)], topLeft().X, topLeft().Y);
    }
    PObject spawnAt(Vector position){
        return new Ghost(W,H,position.clone(),textures);
    }
    @Override
    boolean toDeleat(){
        boolean d = ((this.wallHit() & 0x3) == 2|| uces<=0);
        return d;
    }
    int getP(){
        return type;
    }
    void use(){
        uces--;
    }

}
