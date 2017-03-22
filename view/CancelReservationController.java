package g45.project.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import g45.project.model.CheckInData;
import g45.project.model.Person;
import g45.project.model.Reservation;
import g45.project.model.ReservationCancelData;

/**
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
 *
 * I copied and made some change to the code from the javaFX tutorial--Taylor
 */
public class CancelReservationController {
    @FXML
    private TextField reservationNumField;

    private Stage dialogStage;
    private ReservationCancelData reservation;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setReservationData(ReservationCancelData reservation){
    	this.reservation = reservation;
    }
    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     * when ok is clicked, all info is loaded into the checkInData object
     */
    @FXML
    private void handleOk() {
    	String reservationNum = reservationNumField.getText();
    	reservation.setReservationCancelData(reservationNum);
	    okClicked = true;
	    dialogStage.close();
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

}