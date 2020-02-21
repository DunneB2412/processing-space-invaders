public abstract class Color implements GetColor{
    protected int red, dr, green, dg, blue, db;
    Color(int red, int green, int blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
        dr = dg = db = 1;
    }

    public int[] get(){
        return new int[]{red, green, blue};
    }
}

interface GetColor{
    public int[] get();
}