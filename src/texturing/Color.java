package texturing;

public class Color{
    public static final Color BLACK = new Color(0,0,0);
    public static final Color WHITE = new Color(255,255,255);
    public static final Color GREIGH = new Color(127,127,127);
    public static final Color RED = new Color(255,0,0,255);
    public static final Color GREEN = new Color(0,255,0);
    public static final Color BLUE = new Color(0,0,255);
    public static Color setTransparency(double percentAlpha, Color color){
        return new Color(color.red, color.green, color.blue, (color.alpha*255)*percentAlpha/100);
    }

    private final double red, green, blue, alpha;

    public Color(double red, double green, double blue, double alpha) {
        this.red = red%256;
        this.green = green%256;
        this.blue = blue%256;
        this.alpha = (alpha%256)/255;
    }
    public Color(int colorAsInt){this((colorAsInt >> 16) & 0xff, (colorAsInt >> 8) & 0xff , (colorAsInt) & 0xff, (colorAsInt >> 24) & 0xff);}
    public Color(String hex) { this(Integer.parseInt(hex, 16));}
    public Color(double red, double green, double blue){ this(red,green,blue,255); }

    public int toInt() {
        int color = 0;
        color += (int) (this.alpha * 255) <<24;
        color += (int) (this.red ) <<16;
        color += (int) (this.green ) <<8;
        color += (int) (this.blue);
        return color;
    }
    @Override
    public String toString(){
        return "a:"+alpha+",r:"+red+",g:"+green+",b:"+blue;
    }

    public int[] get(){
        return new int[]{(int) red, (int) green, (int) blue};
    }
}


