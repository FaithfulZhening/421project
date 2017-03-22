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
import g45.project.model.ExtendDateData;
import g45.project.model.Person;
import g45.project.model.Reservation;

/**
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
 *
 * I copied and made some change to the code from the javaFX tutorial--Taylor
 */
public class ExtendDateController {
    @FXML
    private TextField roomNumberField;
    @FXML
    private TextField leaveDateField;
    @FXML
    private TextField durationField;


    private Stage dialogStage;
    private ExtendDateData extendDate;
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

    public void setExtendDate(ExtendDateData extendDate){
    	this.extendDate = extendDate;
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
     * when ok is clicked, all info is loaded into the reservation object
     */
    @FXML
    private void handleOk() {
    	String roomNumber = roomNumberField.getText();
    	String leaveDate = leaveDateField.getText();
    	String duration = durationField.getText();
    	extendDate.setExtendDateData(roomNumber, leaveDate, duration);
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