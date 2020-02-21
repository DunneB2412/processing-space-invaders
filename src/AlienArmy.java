import java.util.ArrayList;

public class AlienArmy extends ProgrammingProject{
    private int spawnCount;
    private final Spawner[] spawners;
    ArrayList<PObject> objects;
    ArrayList<Upgrade> upgrades;
    AlienArmy(int size, ArrayList o, Spawner... spawners){
        spawnCount = size;
        this.spawners = spawners;
        objects = o;
    }
    void tick(Force... eForces){
        ArrayList<PObject> deleatList= new ArrayList<PObject>();
        for(PObject mob: objects){
            Force mobF = eForces.length>1? eForces[(int)random(eForces.length-1)+1].clone():new Force(new Vector(0.0f,0.01f),0,0);
            mobF.momentom.mul((mob instanceof Alien)?new Vector(((Alien)mob).dir,1): new Vector(1,1));
            mob.tick(mobF);

            for(Spawner spawner: spawners){
                if(spawner.collide(mob) ){
                    spawner.block();
                }
            }
            if (mob.toDeleat()){
                deleatList.add(mob);
            }
        }
        for(Spawner spawner: spawners){
            if(spawnCount==0){
                spawner.finish();
            }
            spawner.tick(eForces.length>0? eForces[0].clone():new Force(new Vector(0.01f,0),0,0));
            if(spawner.spawnedThisTick()){
                spawnCount--;
            }
        }
        for(PObject o: deleatList){
            objects.remove(o);
            if((o instanceof Alien)){
                PObject[] os = o.deathSpawns();
                if(os.length>0){
                    for(PObject ob: os){
                        objects.add(ob);
                    }
                }
            }
        }
    }
    boolean finished(){
        //System.out.println(objects.size());

        return spawnCount<=0 && objects.size()==0;
    }

    void check(PObject object){
        if(object instanceof Bulit){
            if(!((Bulit)object).isBomb()){
                for(PObject o: objects){
                    if(o.collide(object) && o instanceof Alien){
                        ((Alien)o).hit(((Bulit)object).hit());
                    }
                }
            }
        }
    }
}
