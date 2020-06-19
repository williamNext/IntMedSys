package intmedsys.utils.alerts;

import javafx.scene.control.Alert;
import javafx.scene.layout.Region;

public interface AlertManager {
    default Alert makeAlert(Alert.AlertType type, String headerMessage, String contentMessage){
        Alert alert = new Alert(type);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setHeaderText(headerMessage);
        alert.setContentText(contentMessage);
        return  alert;
    }
}
