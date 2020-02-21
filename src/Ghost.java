import processing.core.PImage;

public class Ghost extends ImagePObject{
    Ghost(int w, int h, Vector position,  PImage... images){
        super(position,w,h, images);
    }
    void tick(Force eForce){
        super.tick(new Force(new Vector(0,-0.01f),0,0));
    }

    PObject spawnAt(Vector position){
        return new Ghost(W,H,position.clone(),textures);
    }
}
