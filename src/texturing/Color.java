package texturing;

public class Color{
    protected int red, dr, green, dg, blue, db;
    public Color(int red, int green, int blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
        dr = dg = db = 1;
    }

    public int[] get(){
        return new int[]{red, green, blue};
    }
}
