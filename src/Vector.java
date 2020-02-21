public class Vector {
    public float X, Y;
    public Vector(float x, float y){
        X=x;
        Y=y;
    }
    Vector add(Vector other){
        this.X += other.X;
        this.Y += other.Y;
        return this;
    }
    Vector mul(float i){
        X *= i;
        Y *= i;
        return this;
    }
    Vector mul(Vector v){
        X *= v.X;
        Y *= v.Y;
        return this;
    }

    public Vector clone(){
        return new Vector(X,Y);
    }
    Vector rotate(float degree){
        float hX= (float) (Math.cos(degree)*X-Math.sin(degree)*Y);
        Y= (float) (Math.sin(degree)*X+Math.cos(degree)*Y);
        X = hX;
        return this;
    }
    float abs(){
        return (float)Math.sqrt((X*X)+(Y*Y));
    }
    float abs(Vector ov){
        return new Vector(this.X-ov.X,this.Y-ov.Y).abs();
    }
    float getAngle(Vector O){
        float op = O.Y-Y;
        float ad = O.X-X;
        return (float) Math.atan(ad/op);
    }
}
