package stock;

import java.io.File;
import java.util.ArrayList;

public class DataSet {
   private final ArrayList<Entery> enteries= new ArrayList<>();
   private final File file;

    public DataSet(File file) {
        this.file = file;

    }
}
