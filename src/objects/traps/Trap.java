package objects.traps;

import java.nio.file.Files;
import java.util.ArrayList;

public interface Trap {

    ArrayList<Files> images();
    void changeState();

}
