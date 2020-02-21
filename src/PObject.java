public class PObject extends HitBox{
    protected Force localForce;
    private Color colour;
    private float weight;

    PObject(Vector position, int w, int h){
        this(position, w, h, 1, new Color(100,100,100){
            public int[] get(){
                return new int[]{this.red, this.green, this.blue};
            }
        });//default color
    }
    PObject(Vector position, int w, int h, float weigth, Color colour){
        super(position, w, h);
        this.weight = weigth;
        localForce = new Force(new Vector(0,0), 0, this.weight);
        this.colour = colour;

    }
    void tick(Force eForce){
        if(IFraims>0){
            IFraims --;
        }
        this.localForce.add(eForce);
        position.add(localForce.momentom);
        angle += (2*PI)*localForce.rpt;
        show();
    }

    @Override
    void sprite(){
        int[] colour = this.colour.get();
        fill(colour[0], colour[1], colour[2]);
        rect(-W/2, -H/2, W, H);
    }

    HitBox cloneHitBox(){
        return super.clone();
    }
    PObject spawnAt(Vector position){
        return new PObject(position, W,H,weight, colour);
    }
    PObject[] deathSpawns(){
        return new PObject[]{new Explosion((float)Math.sqrt((W*W)+(H*H)),100, this, (float)0.02, 16)};
    }

}
