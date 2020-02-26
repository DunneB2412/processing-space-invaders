package texturing;

public class Color{
    public static final Color BLACK = new Color(0,0,0);
    public static final Color WHITE = new Color(255,255,255);
    public static final Color GREIGH = new Color(127,127,127);
    public static final Color RED = new Color(255,0,0);
    public static final Color GREEN = new Color(0,255,0);
    public static final Color BLUE = new Color(0,0,255);

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
