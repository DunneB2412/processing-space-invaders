import processing.core.PImage;

public class Explosion extends ImagePObject{
    //private static final PImage[] explosion = new PImage[]{loadImage("boom_3.png"), loadImage("boom_2.png"), loadImage("boom_1.png")}
    private final float force;
    private final PObject[] particals;
    private final int s;
    private int duration;

    Explosion(float aoe, int duration, PObject O, float force, int particalN, PImage... pImages){
        super(O.position.clone(), (int)aoe, (int)aoe, pImages);
        int root = (int)Math.sqrt(particalN);
        particalN = root*root;
        particals = new PObject[particalN];
        if(O instanceof ImagePObject && ((ImagePObject)O).textures.length>0){
            for(int i = 0; i< particals.length ;i++){
                particals[i] = new ImagePObject(new Vector(O.topLeft().X+((i%root)*(O.W/(particalN/root)))+(O.W/(particalN/root))/root,O.topLeft().Y+((i/root)*(O.H/(particalN/root)))+(O.H/(particalN/root))/root), O.W/(particalN/root),O.H/(particalN/root),((ImagePObject)O).textures[0].get((i%root)*(O.W/(particalN/root)), (i/root)*(O.H/(particalN/root)),O.W/(particalN/root),O.H/(particalN/root)));
            }
        }else{
            for(int i = 0; i< particals.length ;i++){
                particals[i] = new PObject(new Vector(O.topLeft().X+((i%root)*(O.W/(particalN/root)))+(O.W/(particalN/root))/root,O.topLeft().Y+((i/root)*(O.H/(particalN/root)))+(O.H/(particalN/root))/root), O.W/(particalN/root),O.H/(particalN/root));
            }
        }
        this.force = force;
        this.duration = duration;
        s = duration;
        if(!(this.wallHit(0.5f)>0)){
            //ex1.sounds[5].play();
        }
    }

    @Override
    void tick(Force eForce){
        super.tick(new Force(new Vector(0,0),0,0));
        for(PObject p: particals){
            if(p.position.abs(position)<W){
                float r;
                if((r=((position.X-p.position.X)-(position.Y-p.position.Y)))==0){
                    r = (position.X-p.position.X)+(position.Y-p.position.Y);
                }
                p.tick(new Force(new Vector((p.position.X-position.X)*force/2, (p.position.Y-position.Y)*force/2), (float) (0.01*((r)*force/2)),0));
            }else{
                p.tick(new Force(new Vector(0,0),0,0));
            }
        }
        this.duration--;
    }
    boolean toDeleat(){
        if(duration==0||wallHit()>0){
            return true;
        }
        return false;
    }

    @Override
    void sprite(){
        int t = (duration/(s/textures.length+1));

        ProgrammingProject.processing.image(textures[t], -W/2, -H/2);
    }
    @Override
    PObject[] deathSpawns(){
        return new PObject[]{};
    }
}
