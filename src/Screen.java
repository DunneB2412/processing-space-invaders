
import processing.core.PImage;
import texturing.Color;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    public final List<PObject> objects;
    private final List<Integer> eventCodes;
    private boolean editMode;
    private int parentScreenCode;
    private final int screenCode;
    private final Color backgroundColor;
    Screen(ArrayList<Integer> eventCodes, int screenCode, Color backgroundColor){
        objects = new ArrayList<>();
        this.eventCodes = eventCodes;
        objects.add(Button.CLIOSE_BUTTON);
        parentScreenCode = 0;
        this.screenCode = screenCode;
        this.backgroundColor = backgroundColor;
    }
    public void tick(){
        int[] c = backgroundColor.get();
        ProgrammingProject.processing.background(c[0],c[1],c[2]);
        for(PObject o: objects){
            o.tick(Force.NONE);
            if(o instanceof Button && ((Button) o).isPressed()) {
                eventCodes.add(((Button) o).getEventCode());
                ((Button) o).setState(0, 30);
//                if(o instanceof Slider){
//                    if(((Slider) o).percent>=99) {
//                        PImage image = ProgrammingProject.processing.loadImage("face.png");
//                        image.resize(ProgrammingProject.processing.width, ProgrammingProject.processing.height);
//                        ProgrammingProject.processing.image(image, 0, 0);
//                        System.out.println("WALDRON!!!");
//                    }
//                }
            }
        }

        for (int code: eventCodes){
            if(code==-1){
                if(editMode) {
                    editMode = false;
                }else {
                    ProgrammingProject.screenPointer = parentScreenCode;
                    ProgrammingProject.screens[parentScreenCode].parentScreenCode=this.screenCode;
                }
            }
            else if(code>0) {
                ProgrammingProject.screenPointer = code;
                ProgrammingProject.screens[parentScreenCode].parentScreenCode = this.screenCode;
            }
        }
        eventCodes.clear();
    }
    public void addButton(Button button){
        objects.add(button);
    }
}
