import processing.core.PImage;

import java.util.ArrayList;

public class Alien extends Cannon{
    protected int lives, stepCounter, dir;
    protected HitBox reflexZone;
    final Ghost ghost;
    private boolean shot;

    Alien(Ghost ghost, int buf, int startHelth, Vector position, PObject bulit, ArrayList<PObject> squad, PImage... images){
        super(ghost.W/bulit.W, ghost.H/bulit.H, position, bulit,new Vector(1000,1),new Vector(0,ghost.H/2), squad,0, images);
        ghost.position = position;
        this.ghost = ghost;
        lives = startHelth;
        stepCounter = 0;
        reflexZone = new HitBox(this.position, this.W+(buf*2), this.H+(buf*2));
        dir = -1;
        shot = false;
    }
    void dammage(){
        lives--;
        IFraims = 10;
    }
    void kill(){
        lives = 0;
    }
    @Override
    void tick(Force eForce){
        if(this.wallHit(0.5f)>0){
            kill();
        }
        if(stepCounter==0){
            dir*=-1;
            stepCounter = 20;
        }else{
            stepCounter --;
        }
        super.tick(eForce);
        if(spawnedThisTick()){
            shot = true;
        }

    }
    @Override
    protected Force move(Force eForce){
        return eForce;
    }
    @Override
    void sprite(){
        image(textures[0], -W/2, -H/2);
    }

    HitBox cloneHitBox(){
        return reflexZone.clone();
    }
    PObject spawnAt(Vector position){
        return new Alien(this.ghost,(reflexZone.W-W)/2,lives,position.clone(),spawn,grupe,textures);
    }
    void hit(Float d){
        lives -= d;
    }
    boolean toDeleat(){
        return lives==0;
    }
    @Override
    PObject[] deathSpawns(){
        if(random(1)<=2){
            grupe.add( new Upgrade(this.position.clone(), (int)random(0,5), (int)random(20, 200), (int)random(5,15)));// maby drop bomb on death
        }
        return new PObject[]{new Explosion((float)Math.sqrt((W*W)+(H*H)),100, this, 0.02, 128), this.ghost.spawnAt(position)};
    }

    @Override
    protected boolean spawn(PObject lspawn){
        if(!shot){
            Force pForce = new Force(new Vector(0,-1*(5)),0,0);
            pForce.momentom.rotate(angle-(PI));

            ((Bulit)lspawn).push(pForce);
            if(canSpawn&& spawnZone.wallHit()==0
                    && random(spawnChance.X)<=spawnChance.Y){
                ((Bulit)lspawn).setAtributes(1, 2, 40, true);
                grupe.add(lspawn);
                //SoundFile boom = ex1.sounds[3];
                //boom.amp(0.3);
                //boom.play();
                return true;
            }
            return false;
        }
        return false;
    }
    @Override
    void reflex(){
        //this.wallHit();
    }
}
