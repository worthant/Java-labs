package gui;

import javafx.scene.control.Alert;

/**
 * Utility class for GUI alerts setup
 */
public class AlertUtility {
    /**
     * Shows an alert dialog with the given information.
     * @param info The information to be shown in the alert dialog.
     */
    public static void infoAlert(String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(info);
        alert.showAndWait();
    }

    /**
     * Shows an alert dialog with the given error message.
     * @param error The error message to be shown in the alert dialog.
     */
    public static void errorAlert(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(error);
        alert.showAndWait();
    }
}
