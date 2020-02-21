public class HitBox extends ProgrammingProject{

    final int W, H;
    protected int IFraims;
    protected Vector position;
    protected float angle;
    protected boolean hardWall;


    HitBox(Vector position, int w, int h){
        this.position = position;
        this.angle = 0;
        this.W = w;
        this.H = h;
        hardWall = false;
    }
    public HitBox clone(){
        return new HitBox(position.clone(), W, H);
    }

    protected int wallHit(){return wallHit(0.0f);}
    protected int wallHit(float inOut){
        // 11 01 21
        // 10 00 20
        // 12 02 22
        int out = 0;
        if((position.X-W/2)<(0-W*inOut)){
            out += 10;
        }
        if((position.Y-H/2)<(0-H*inOut)){
            out += 1;
        }
        if((position.X+(W/2))>(width+W*inOut)){
            out += 20;
        }
        if((position.Y+(H/2))>(height+H*inOut)){
            out += 2;
        }
        if(hardWall && out > 0){
            if(out/10==2){
                position.add(new Vector(-1,0));
            }
            if(out/10==1){
                position.add(new Vector(1,0));
            }
            if((out%10)==2){
                position.add(new Vector(0,-1));
            }
            if((out%10)==1){
                position.add(new Vector(0,1));
            }
            wallHit(inOut);
        }
        return out;
    }
    boolean collide(HitBox other){
        if(topLeft().X < other.bottomRight().X &&
                bottomRight().X > other.topLeft().X &&
                topLeft().Y < other.bottomRight().Y &&
                bottomRight().Y > other.topLeft().Y
                &&IFraims==0&&other.IFraims==0){
            return true;
        }
        return false;
    }
    protected void hardWall(boolean b){
        hardWall = b;
    }

    Vector topLeft(){
        return new Vector(position.X-(W/2), position.Y-(H/2));
    }
    Vector bottomRight(){
        return new Vector(position.X+(W/2), position.Y+(H/2));
    }
    void move(float newX, float newY){
        position.X = newX;
        position.Y = newY;
    }
    void setAsngle(float newAngle){
        this.angle = newAngle;
    }
    boolean toDeleat(){
        return false;
    }
    void show(){
        pushMatrix();
        translate(position.X, position.Y);
        rotate(angle);
        sprite();
        popMatrix();
    }
    void sprite(){
        fill(200);
        rect(-W/2, -H/2, W, H);
        fill(255);
        rect((-W/2)+3, (-H/2)+3, W-6, H-6);
    }

    //private void wallPhase(){
    //}
}
