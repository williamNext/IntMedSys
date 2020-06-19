package intmedsys.utils.screen;

import intmedsys.IntMedSys;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public interface ScreenManager {
    default void changeScene(ScreenPath path) {
        try {
            IntMedSys.changeScene(new FXMLLoader(getClass().getResource(path.getPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
