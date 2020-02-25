import java.util.ArrayList;
import java.util.List;

public class Screen {
    protected final List<PObject> objects;
    protected final int[] eventCodes;
    private boolean editMode;
    Screen(){
        objects = new ArrayList<>();
        objects.add(Button.CLIOSE_BUTTON);
        eventCodes = new int[10];
    }
    public void show(){
        for(PObject o: objects){
            o.tick(Force.NONE);
        }
    }
    private void addButton(Button button){
        objects.add(button);
    }
}
