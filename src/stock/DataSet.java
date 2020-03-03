package stock;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSet {
   private final ArrayList<Entery> enteries= new ArrayList<>();
   private final File file;

    public DataSet(File file) throws FileNotFoundException {
        this.file = file;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            enteries.add(new Entery(scanner.nextLine()));
        }
        scanner.close();
    }

    @Override
    public String toString() {
        return file.getName()+"["+enteries.toString()+"]";
    }
}
