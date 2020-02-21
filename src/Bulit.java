import processing.core.PImage;

public class Bulit extends ImagePObject{
    private int cycle, dir, durability;
    private float dm, aoe;
    private boolean bomb;


    Bulit(int w, int h, Vector position, PImage... images){
        super(position,w,h,images);
        this.durability = 1;
        dm = 1;
        aoe = 1;
        cycle = 0;
        dir = 1;
        bomb = false;
    }
    boolean isBomb(){
        return this.bomb;
    }
    void setAtributes(float du, float dm, float aoe, boolean bomb){
        durability = (int)du;
        this.dm = dm;
        this.aoe = aoe;
        this.bomb = bomb;
    }
    @Override
    void tick(Force eForce){
        super.tick(new Force(new Vector(0,0),0,0));
    }
    @Override
    void sprite(){
        if(textures.length<=1){
            image(textures[0], -W/2, -H/2);
            return;
        }
        if(durability>1){
            image(textures[1], -W/2, -H/2);
        }
        if(cycle+dir>(textures.length*6)-1||cycle+dir<0){
            dir*=-1;
        }
        cycle+=dir;
        image(textures[(cycle/6)], -W/2, -H/2);
    }
    @Override
    boolean toDeleat(){
        return this.wallHit(1)%2==1 || durability<=0;
    }

    void kill(){
        durability = 0;
    }
    float hit(){
        durability--;
        return 1*dm;
    }

    void push(Force eForce){
        this.localForce.add(eForce);
    }
    PObject[] deathSpawns(){
        if(aoe>1){
            return new PObject[]{new Explosion(aoe+10,20, this, 0.02f, 64)};
        }
        return new PObject[]{};
    }

    @Override
    PObject spawnAt(Vector position){
        return new Bulit(W,H,position.clone(),textures);
    }
}
