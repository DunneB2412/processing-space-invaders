import processing.core.PImage;
import texturing.Color;

import java.util.ArrayList;

public class Spawner extends PObject{
    protected boolean canSpawn, finished, spawnedThisTick;
    final HitBox spawnZone;
    protected final PObject spawn;
    protected final ArrayList<PObject> grupe;
    protected final Vector offset, spawnChance;
    private int dir;

    Spawner(int wMultiplier, int hMultiplier, Vector position, PObject spawn, Vector spawnChance, Vector offset, ArrayList<PObject> grupe, PImage... images){
        super(position, wMultiplier*spawn.W, hMultiplier*spawn.H,1, Color.BLACK, images);
        this.spawn = spawn;
        this.grupe = grupe;
        spawnZone = spawn.cloneHitBox();
        spawnZone.position.add(offset);
        this.offset = offset;
        this.spawnChance = spawnChance;
        canSpawn = true;
        finished = false;
        spawnedThisTick = false;
        dir = 1;
    }
    void block(){
        canSpawn = false;
    }
    void finish(){
        this.finished = true;
    }
    boolean spawnedThisTick(){
        return spawnedThisTick;
    }

    @Override
    void tick(Force eForce){
        spawnedThisTick = false;
        super.tick(move(eForce));
        Vector eOffset = offset.clone().rotate(angle);
        spawnZone.move(position.X+eOffset.X,position.Y+eOffset.Y);
        spawnedThisTick = spawn(spawn.spawnAt(spawnZone.position));
        canSpawn = true;
    }
    protected Force move(Force eForce){
        if(! finished){
            eForce.momentom.mul(new Vector(dir*W/10, 1*H/50));
            if(localForce.momentom.abs()>(0.1*W)){
                eForce.momentom.mul(new Vector(0, 1));
                reflex();
            }
        }
        return eForce;
    }
    protected boolean spawn(PObject lspawn){
        if(canSpawn&& spawnZone.wallHit()==0
                && ProgrammingProject.processing.random(spawnChance.X)<=spawnChance.Y){
            grupe.add(lspawn);
            return true;
        }
        return false;
    }
    void reflex(){
        if(IFraims == 0 && this.wallHit()>=10){
            IFraims = 10;
            this.localForce.momentom.add(new Vector(this.localForce.momentom.X*-2,0));
            dir *= -1;
        }
        this.wallHit();
    }
}
