import processing.core.PImage;

public class ImagePObject extends PObject{
    protected PImage[] textures;
    ImagePObject(Vector position, int w, int h,  PImage... images){
        super(position,w,h);
        this.textures = images;
        w = (w<=0?1:w);
        h = (h<=0?1:h);
        for(PImage i:this.textures){
            i.resize(w,h);
        }
    }
    void tick(Force eForce){
        super.tick(eForce);
    }

    boolean toDeleat(){
        return this.wallHit(1)>0;
    }


    PObject spawnAt(Vector position){
        return new ImagePObject(position.clone(),W,H,textures);
    }
}
