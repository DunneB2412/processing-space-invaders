import processing.core.PImage;

public class Util {

    private static final int[] DEFAULT_EVENT_CODES = new int[]{-1};//make enum

    public static PImage getfader(PImage image){
        PImage border = image.copy();
        for(int w=0; w< border.width;w++){
            for(int h=0; h<border.height;h++){
                if(image.get(w,h)!=0){
                    border.set(w,h,0x4F0A0A0A);
                }
            }
        }
        return border;
    }
    public static PImage getBorder(PImage image) {
        PImage border = image.copy();
        for(int w=0; w< border.width;w++){
            for(int h=0; h<border.height;h++){
                if(borderPixle(w,h,10*image.width/100,image)){
                    border.set(w,h,0xFFAAAAAA);
                }
                else{
                    border.set(w,h,0);
                }
            }
        }
        return border;
    }
    private static boolean borderPixle(int x, int y, int borderSize, PImage image){
        if(image.get(x,y)==0){
            return false;
        }
        int r = borderSize/2;
        for(int layer = 0; layer< r; layer++){
            if((x-layer<0||x+layer>image.width)||(y-layer<0||y+layer>image.height)){
                return true;
            }
            for(int i=1; i<(layer*2);i++){
                if(((Math.sqrt(Math.pow((-layer)+i,2)+Math.pow(-layer,2))<=r) && (image.get((x-layer)+i,y-layer)==0))
                        ||((Math.sqrt(Math.pow(layer,2)+Math.pow(-layer+i,2))<=r) && (image.get(x+layer,(y-layer)+i)==0))
                        ||((Math.sqrt(Math.pow((layer)-i,2)+Math.pow(layer,2))<=r) && (image.get((x+layer)-i,y+layer)==0))
                        ||((Math.sqrt(Math.pow((-layer),2)+Math.pow(layer-i,2))<=r) && (image.get(x-layer,(y+layer)-i)==0))){
                    return true;
                }
            }
        }
        return false;
    }
}
