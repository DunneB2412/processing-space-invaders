package texturing;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Resource implements Comparable<Resource>{
    public static PImage TEXTURE_MAP;
    public static List<Resource> resourceList = new ArrayList<>();
    private final int X,Y,W,H;

    public Resource(PImage image, PApplet applet) {
        int[] cords;
        if(TEXTURE_MAP == null){
            TEXTURE_MAP = applet.createImage(0,0,2);
        }
        cords = fitTexture(image, applet);
        this.X = cords[0];
        this.Y = cords[1];
        this.W = image.width;
        this.H = image.height;
        resourceList.add(this);
    }

    private static int[] fitTexture(PImage image, PApplet applet) { // optomise the fit method
        int[] cords = new int[]{0,0};
        for(Resource resource: resourceList){
            if(resource.X<resource.Y) cords[0]+=resource.W;
            else cords[1]+=resource.H;
        }
        if(TEXTURE_MAP.width<cords[0]+image.width){
            PImage next = applet.createImage(cords[0]+image.width,TEXTURE_MAP.height,2);
            next.set(0,0,TEXTURE_MAP);
            TEXTURE_MAP = next;
        }
        if(TEXTURE_MAP.height<cords[1]+image.height){
            PImage next = applet.createImage(TEXTURE_MAP.width,cords[1]+image.height,2);
            next.set(0,0,TEXTURE_MAP);
            TEXTURE_MAP = next;
        }
        TEXTURE_MAP.set(cords[0],cords[1], image);
        return cords;
    }
    public PImage getTexture(){
        return TEXTURE_MAP.get(X,Y,W,H);
    }

    @Override
    public int compareTo(Resource o) {
        int out = 0;
        if((out=o.Y-Y)!=0){
            return out;
        }
        return o.X-X;
    }
}
