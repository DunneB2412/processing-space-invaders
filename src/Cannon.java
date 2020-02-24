import processing.core.PImage;

import java.util.ArrayList;

public class Cannon extends Spawner{
    private final int coolDown;
    private int coolDownCounter;
    private ArrayList<ArrayList<Upgrade>> upgrades;

    Cannon(int wMultiplier, int hMultiplier, Vector position, PObject spawn, Vector spawnChance, Vector offset, ArrayList<PObject> grupe, int coolDown,  PImage... images){
        super(wMultiplier, hMultiplier, position, spawn, spawnChance, offset, grupe, images);
        this.coolDown = coolDown;
        coolDownCounter = 0;
        upgrades = new ArrayList<ArrayList<Upgrade>>(3);
        for(int i=0; i<5; i++){
            upgrades.add(new ArrayList<Upgrade>());
        }
    }
    @Override
    void sprite(){
        if(coolDownCounter == 0){
            ProgrammingProject.processing.image(textures[0], -W/2, -H/2);
        }
        else{
            ProgrammingProject.processing.image(textures[((coolDown-coolDownCounter)/(coolDown/textures.length+1))], -W/2, -H/2);
        }
    }
    @Override
    protected boolean spawn(PObject lspawn){
        if(ProgrammingProject.processing.mousePressed && super.canSpawn && coolDownCounter==0){
            ((Bulit)lspawn).setAtributes(getUpgrade(1,1),getUpgrade(3,1),getUpgrade(4,1), false);
            Force pForce = new Force(new Vector(0,-1*(5+getUpgrade(2,0)/3)),0,0);
            pForce.momentom.rotate(angle);

            ((Bulit)lspawn).push(pForce);
            lspawn.angle = this.angle;
            if(super.spawn(lspawn)){
                coolDownCounter = (int)(coolDown-getUpgrade(0,1)*1.5);
                //SoundFile boom = ex1.sounds[4];
                //boom.amp(0.3);
                //boom.play();
                return true;
            }
        }
        if(coolDownCounter>0){
            coolDownCounter--;
        }
        return false;
    }
    void addUpgrade(Upgrade upgrade){
        if(upgrade.getP()>=upgrades.size()){
            return;
        }
        upgrades.get(upgrade.getP()).add(upgrade);
    }

    private float getUpgrade(int type, float min){
        float p = 0;
        if(upgrades!=null && upgrades.get(type) != null && upgrades.get(type).size()>0){
            Upgrade upgrade = upgrades.get(type).get(0);
            p = upgrade.power;
            upgrade.use();
            if(upgrade.toDeleat()){
                upgrades.get(type).remove(upgrade);
            }
        }
        return Math.max(p,min);
    }
    @Override
    void reflex(){
        this.wallHit();
    }
}
