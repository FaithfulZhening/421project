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
import g45.project.model.RoomChangeData;

/**
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
 *
 * I copied and made some change to the code from the javaFX tutorial--Taylor
 */
public class RoomChangeDialogController {


    @FXML
    private TextField roomNumberField;
    @FXML
    private TextField startDateField;
    @FXML
    private TextField endDateField;


    private Stage dialogStage;
    private RoomChangeData roomChangeData;
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

    public void setRoomChangeData(RoomChangeData roomChangeData){
    	this.roomChangeData = roomChangeData;
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
    	String roomNumber = roomNumberField.getText();
    	String startDate = startDateField.getText();
    	String endDate = endDateField.getText();
    	roomChangeData.setRoomChangeData(roomNumber, startDate, endDate);
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