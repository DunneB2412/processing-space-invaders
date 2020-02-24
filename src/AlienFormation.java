import processing.core.PImage;

import java.util.ArrayList;

public class AlienFormation extends Alien{
    private int stepCounter2, dir2;

    AlienFormation(Ghost ghost, int buf, int startHelth, Vector position, PObject bulit, ArrayList<PObject> squad, PImage... images){
        super(ghost, buf, startHelth, position,bulit, squad, images);
        localForce.add(new Force(new Vector(1,0),0,0));
        stepCounter2 = -1;
        this.dir2 = 1;
    }
    @Override
    void tick(Force eForce){
        Vector eVector = new Vector(0,0);
        for(PObject o: grupe){
            if(this.collide(o)&& o instanceof Alien){
                Force h = o.localForce;
                o.localForce = localForce;
                localForce = h;
            }
        }
        if(IFraims==0 && position.X<W*1.5&&stepCounter2==-1){
            eVector = new Vector(localForce.momentom.X*-1,1);
            dir2 = 1;
            IFraims=stepCounter2 = (int)(H*1.5);
        }
        if(IFraims==0 && position.X>(ProgrammingProject.processing.width-(W*1.5))&&stepCounter2==-1){
            eVector = new Vector(localForce.momentom.X*-1,1);
            dir2 = -1;
            IFraims=stepCounter2 = (int)(H*1.5);
        }
        if(stepCounter2 == 0){
            eVector = new Vector(dir2, localForce.momentom.Y*-1);
            stepCounter2 = -1;
            IFraims = 10;
        }
        if(stepCounter2>0){
            stepCounter2 --;
        }
        super.tick(new Force(eVector,0,0));

    }
    PObject spawnAt(Vector position){
        return new AlienFormation(this.ghost,(reflexZone.W-W)/2,lives,position.clone(),spawn,grupe,super.textures);
    }
}
