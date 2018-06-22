package lapr.project.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Custom utility class for JavaFX GUI specific methods.
 * <p>
 * Created by Diogo Capela (1171316@isep.ipp.pt) on 01/06/2018.
 */
public final class FXUtils {

    /**
     * Private constructor to hide implicit public one.
     * Static utility classes don't need to be instantiated.
     */
    private FXUtils() {

    }

    /**
     * Opens a JavaFX success alert box.
     *
     * @param message Success message
     */
    public static void openAlertSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Success");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Opens a JavaFX error alert box.
     *
     * @param message Error message
     */
    public static void openAlertError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Opens a JavaFX confirmation box
     * @param message Confirmation message
     * @return Button of the confirmation box
     */
    public static Optional<ButtonType> openDialogBox(String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Confirm?");
        alert.setContentText(message);
        return alert.showAndWait();
    }
}
