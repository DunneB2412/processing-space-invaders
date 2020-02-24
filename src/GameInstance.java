import processing.core.PImage;

import java.util.ArrayList;

public class GameInstance{
    private ArrayList<PObject> bulits;
    private ArrayList<PObject> bulits2;
    private ArrayList<PObject> effects;
    private AlienArmy[] waves;
    private int wave;
    private final Player player;
    GameInstance(){
        bulits = new ArrayList<PObject>();
        bulits2 = new ArrayList<PObject>();
        effects = new ArrayList<PObject>();
        Bulit bulit = new Bulit(20,30, new Vector(-10,10), ProgrammingProject.processing.loadImage("fireBall_3.png"),
                ProgrammingProject.processing.loadImage("fireBall_1.png"), ProgrammingProject.processing.loadImage("fireBall_2.png"));
        Bulit bomb = new Bulit(20,40, new Vector(-10,10), ProgrammingProject.processing.loadImage("nuke.png"));


        PImage[] cannonImageSet = new PImage[]{ProgrammingProject.processing.loadImage("cannon/turret_10.png"),
                ProgrammingProject.processing.loadImage("cannon/turret_12.png"),ProgrammingProject.processing.loadImage("cannon/turret_13.png"),
                ProgrammingProject.processing.loadImage("cannon/turret_14.png"),ProgrammingProject.processing.loadImage("cannon/turret_15.png"),
                ProgrammingProject.processing.loadImage("cannon/turret_16.png"),ProgrammingProject.processing.loadImage("cannon/turret_17.png"),
                ProgrammingProject.processing.loadImage("cannon/turret_18.png"),ProgrammingProject.processing.loadImage("cannon/turret_19.png"),
                ProgrammingProject.processing.loadImage("cannon/turret_110.png"),ProgrammingProject.processing.loadImage("cannon/turret_111.png"),
                ProgrammingProject.processing.loadImage("cannon/turret_112.png"),ProgrammingProject.processing.loadImage("cannon/turret_113.png"),
                ProgrammingProject.processing.loadImage("cannon/turret_114.png")};

        Alien crazy = new Alien(new Ghost(70,60, new Vector(0,0),ProgrammingProject.processing.loadImage("ghost2.png"))
                ,4,4,new Vector(-300,-100),bomb, bulits2,ProgrammingProject.processing.loadImage("alien2.png"),
                ProgrammingProject.processing.loadImage("boom2.png"));

        Alien form = new AlienFormation(new Ghost(40,40, new Vector(0,0),ProgrammingProject.processing.loadImage("ghost.png")),
                8,8,new Vector(200,100),bomb, bulits2, ProgrammingProject.processing.loadImage("alien.png"));
        Cannon cannon = new Cannon(2,1, new Vector(ProgrammingProject.processing.width/2, ProgrammingProject.processing.height-40),
                bulit, new Vector(1,1), new Vector(0,-20), bulits, 30, cannonImageSet);

        ArrayList<PObject> aList = new ArrayList<PObject>();
        Spawner spawnerTwo = new Spawner(2,1, new Vector(ProgrammingProject.processing.width-200,100), crazy ,new Vector(50,1),
                new Vector(0,20), aList, new PImage[]{ProgrammingProject.processing.loadImage("spawner30.png"),
                ProgrammingProject.processing.loadImage("spawner31.png")});

        Spawner spawnerOne = new Spawner(2,1, new Vector(100,100), form ,new Vector(50,1),new Vector(0,20),
                aList, new PImage[]{ProgrammingProject.processing.loadImage("spawner10.png"),ProgrammingProject.processing.loadImage("spawner11.png")});

        waves = new AlienArmy[]{new AlienArmy(1, aList, spawnerOne, spawnerTwo)};
        this.player = new Player(100, 100,  cannon, ProgrammingProject.processing.loadImage("player1.png"), ProgrammingProject.processing.loadImage("player2.png"));
        player.hardWall(true);
    }

    void tick(){
        if(!player.toDeleat()){
            player.tick(pMove(player));
        }

        if(wave<waves.length){
            ArrayList<PObject> usedObjects = new ArrayList<PObject>();
            for(PObject object: bulits){
                object.tick(new Force(new Vector(0,0),0,0));
                player.check(object);
                waves[wave].check(object);
                if(object.toDeleat()){
                    usedObjects.add(object);
                }
            }
            for(PObject d: usedObjects){
                bulits.remove(d);
                for(PObject o: d.deathSpawns()){
                    effects.add(o);
                }
            }
            usedObjects.clear();
            waves[wave].tick();
            if(waves[wave].finished()){
                wave++;
            }
        }

        ArrayList<PObject> usedObjects2 = new ArrayList<PObject>();
        for(PObject object: bulits2){
            object.tick(new Force(new Vector(0,0),0,0));
            if(player.check(object)|| object.toDeleat()){
                usedObjects2.add(object);
            }
        }
        for(PObject d: usedObjects2){
            bulits2.remove(d);
            for(PObject o: d.deathSpawns()){
                effects.add(o);
            }
        }
        usedObjects2.clear();


        ArrayList<PObject> usedObjects3 = new ArrayList<PObject>();
        for(PObject object: effects){
            object.tick(new Force(new Vector(0,0),0,0));
            if(object.toDeleat()){
                usedObjects3.add(object);
            }
        }
        for(PObject d: usedObjects3){
            effects.remove(d);
        }
        usedObjects3.clear();


    }

    private Force pMove(PObject o){
        float a = -o.localForce.momentom.X/16;//inertial dampiners
        if (ProgrammingProject.processing.key == ProgrammingProject.processing.CODED && ProgrammingProject.processing.keyPressed) {
            if (ProgrammingProject.processing.keyCode == ProgrammingProject.processing.LEFT) {
                a=-1;
            }else if (ProgrammingProject.processing.keyCode == ProgrammingProject.processing.RIGHT) {
                a=1;
            }
        }
        return new Force(new Vector(a,0),0,0);
    }
    int state(){
        if(player.toDeleat()){
            return 1;
        }
        if( wave>=waves.length){
            return 2;
        }
        return 0;
    }
}
