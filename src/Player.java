import processing.core.PImage;

import java.util.ArrayList;

public class Player extends ImagePObject{
    private ArrayList<Cannon> cannons;
    private int helth, shieldHelth, toggleCooldown;
    private boolean shieldActive;
    private ArrayList<Upgrade> avalibleUpgrades;
    Player(int w, int h, Cannon startCannon, PImage... images){
        super(new Vector(width/2,height-((h/2)+5)),w,h,images);
        cannons = new ArrayList<Cannon>();
        //startCannon.addUpgrade(new Upgrade(position, 5, 1000000, 15));
        //startCannon.addUpgrade(new Upgrade(position, 4, 1000000, 15));
        //startCannon.addUpgrade(new Upgrade(position, 3, 1000000, 15));
        //startCannon.addUpgrade(new Upgrade(position, 2, 1000000, 15));
        //startCannon.addUpgrade(new Upgrade(position, 1, 1000000, 15));
        startCannon.addUpgrade(new Upgrade(position, 0, 1000000, 15));
        cannons.add(startCannon);
        helth = 1;
        shieldHelth = 10;
    }

    @Override
    void tick(Force eForce){
        if(shieldHelth > 0 && toggleCooldown == 0 && key == CODED && keyPressed && keyCode == DOWN){
            shieldActive = !shieldActive;
            toggleCooldown = 10;
        }
        if(toggleCooldown > 0){
            toggleCooldown --;
        }
        if(shieldHelth <= 0){
            shieldActive = false;
        }

        if(wallHit()>0&&hardWall){
            super.tick(new Force(new Vector(-localForce.momentom.X,0),0,0));
        }else{
            super.tick(eForce);
        }

        for(Cannon cannon: cannons){
            cannon.move(position.X+0, position.Y+0);


            // handle cannon angle better
            float rot = cannon.angle + cannon.position.getAngle(new Vector(mouseX, mouseY));
            float a = 0.05f;
            float eFocous = a;//*cannon.localForce.rpt;
            if(rot <= eFocous && rot >= -eFocous){
                rot = cannon.localForce.rpt/2;
            }else{
                rot *= 0.001;
            }
            if(cannon.angle>(PI/2)||cannon.angle<(-PI/2)){
                rot = cannon.localForce.rpt*1.1f;
                while(cannon.angle>(PI/2)){
                    cannon.angle -= PI/180;
                }
                while(cannon.angle<(-PI/2)){
                    cannon.angle += PI/180;
                }
            }
            cannon.tick(new Force(new Vector(0,0),-rot,0));
        }
    }
    @Override
    void sprite(){
        if(shieldActive){
            image(textures[(int)random(textures.length-1)+1], -W/2, -H/2);
        }else{
            image(textures[0], -W/2, -H/2);
        }
    }

    boolean check(PObject object){
        if(object.collide(this)){
            if(object instanceof Bulit){
                Bulit bulit = (Bulit)object;
                if(shieldActive){
                    shieldHelth -= bulit.hit();
                    return true;
                }else if(bulit.isBomb()){
                    helth -= bulit.hit();
                    return true;
                }
            }
            if(object instanceof Upgrade){
                cannons.get(0).addUpgrade(((Upgrade)object));
                return true;
            }
        }
        return false;
    }
    @Override
    boolean toDeleat(){
        return helth<=0;
    }
}
