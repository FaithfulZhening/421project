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
import g45.project.model.Person;
import g45.project.model.Reservation;

/**
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
 *
 * I copied and made some change to the code from the javaFX tutorial--Taylor
 */
public class ReservationEditDialogController {
	ObservableList<String> roomTypeList = FXCollections.observableArrayList("Single","Twin","Double");
	ObservableList<String> paymentList = FXCollections.observableArrayList("Cash","Debit Card","Credit Card");

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField email;
    @FXML
    private CheckBox membership;
    @FXML
    private TextField personalID;
    @FXML
    private TextField startDate;
    @FXML
    private TextField endDate;
    @FXML
    private ComboBox roomType;
    @FXML
    private CheckBox onlineReserved;
    @FXML
    private ComboBox paymentType;


    private Stage dialogStage;
    private Reservation reserv;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	paymentType.setItems(paymentList);
    	roomType.setItems(roomTypeList);
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setReservation(Reservation res){
    	this.reserv = res;
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
    	String firstName = firstNameField.getText();
    	String lastName = lastNameField.getText();
    	String phone = phoneNumber.getText();
    	String mail = email.getText();
    	Boolean member = membership.isSelected();
    	String personID = personalID.getText();
    	String start = startDate.getText();
    	String end = endDate.getText();
    	String room = (String) roomType.getValue();
    	Boolean reserved = onlineReserved.isSelected();
    	String pType = (String) paymentType.getValue();
    	reserv.setReservation(firstName, lastName, phone, mail, member, personID,
    			start, end, room, reserved, pType);
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