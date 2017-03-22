package g45.project.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;

import java.sql.SQLException;

import g45.project.MainApp;
import g45.project.model.CheckInData;
import g45.project.model.CheckOutData;
import g45.project.model.ExtendDateData;
import g45.project.model.Person;
import g45.project.model.Reservation;
import g45.project.model.ReservationCancelData;
import g45.project.model.RoomChangeData;

public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private TableColumn<Person,String>	guestIDColumn;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label guestIDLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label membershipLabel;
    @FXML
    private Label personalIDLabel;
    @FXML
    private Label paymentSoFarLabel;


    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        guestIDColumn.setCellValueFactory(cellData -> cellData.getValue().guestIDProperty());

        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param person the person or null
     */
    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            guestIDLabel.setText(person.getGuestID());
            phoneLabel.setText(person.getPhone());
            emailLabel.setText(person.getEmail());
            if(person.getMembership()) membershipLabel.setText("Yes");
            else membershipLabel.setText("No");
            personalIDLabel.setText(person.getPersonalID());
            paymentSoFarLabel.setText(String.valueOf(person.getPaymentSoFar()));
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            guestIDLabel.setText("");
            phoneLabel.setText("");
            emailLabel.setText("");
            membershipLabel.setText("");
            personalIDLabel.setText("");
            paymentSoFarLabel.setText("");
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new reservation.
     * @throws SQLException
     */
    @FXML
    private void handleNewReservation() throws SQLException {
    	//create a new reservation and pass it to reservationEditDialog
    	Reservation tempReserv = new Reservation();
    	//the info needed is filled in the dialog
        boolean okClicked = mainApp.showReservationEditDialog(tempReserv);
        if (okClicked) {
        	String executeResult = tempReserv.run();
        	if (executeResult.compareTo("Failed") == 0){
        		Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Make reservation");
                alert.setHeaderText("Error");
                alert.setContentText("Fail to add reservation. No avail room on demand");

                alert.showAndWait();
        	}
        	else{
        		Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Make reservation");
                alert.setHeaderText("Success!");
                alert.showAndWait();
        	}
        }
    }

    /**
     * Called when the user clicks the check in button. Opens a dialog to edit
     * details for a check in.
     * @throws SQLException
     */
    @FXML
    private void handleCheckIn() throws SQLException {
    	//create a new reservation and pass it to reservationEditDialog
    	CheckInData tempCheckInData = new CheckInData();
    	//the info needed is filled in the dialog
        boolean okClicked = mainApp.showCheckInDialog(tempCheckInData);
        if (okClicked) {
        	String executeResult = tempCheckInData.checkIn();
        	if (executeResult.compareTo("Guest doesn't have any reservation.") == 0){
        		Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Check In");
                alert.setHeaderText("Error");
                alert.setContentText("Fail to check in. Guest doesn't have any reservation.");
                alert.showAndWait();
        	}
        	else{
        		Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Check In");
                alert.setHeaderText("Success!");
                alert.setContentText("Room number is " + executeResult);
                alert.showAndWait();
        	}
        }
    }

    /**
     * Called when the user clicks the cancel reservation button. Opens a dialog to edit
     * details for a reservation cancel.
     * @throws SQLException
     */
    @FXML
    private void handelCancelReservation() throws SQLException {
    	//create a new reservation and pass it to reservationEditDialog
    	ReservationCancelData tempReserv = new ReservationCancelData();
    	//the info needed is filled in the dialog
        boolean okClicked = mainApp.showCancelReservationDialog(tempReserv);
        if (okClicked) {
        	String executeResult = tempReserv.cancelReservation();
        	if (executeResult.compareTo("false") == 0){
        		Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Cancel Reservation");
                alert.setHeaderText("Error");
                alert.setContentText("Fail to cancel Reservation. Reservation Number does not exist");
                alert.showAndWait();
        	}
        	else{
        		Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Cancel Reservation");
                alert.setHeaderText("Success!");
                alert.showAndWait();
        	}
        }
    }

    /**
     * Called when the user clicks the cancel reservation button. Opens a dialog to edit
     * details for a reservation cancel.
     * @throws SQLException
     */
    @FXML
    private void handleExtendDate() throws SQLException {
    	//create a new reservation and pass it to reservationEditDialog
    	ExtendDateData tempData = new ExtendDateData();
    	//the info needed is filled in the dialog
        boolean okClicked = mainApp.showExtendDateDialog(tempData);
        if (okClicked) {
        	String executeResult = tempData.extendDate();
        	if (executeResult.compareTo("false") == 0){
        		Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Extend Date");
                alert.setHeaderText("Error");
                alert.setContentText("No available room");
                alert.showAndWait();
        	}
        	else{
        		Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Extend Date");
                alert.setHeaderText("Success!");
                alert.showAndWait();
        	}
        }
    }

    /**
     * Called when the user clicks the check out button. Opens a dialog to edit
     * details for a check out operation.
     * @throws SQLException
     */
    @FXML
    private void handCheckOut() throws SQLException {
    	//create a new reservation and pass it to reservationEditDialog
    	CheckOutData tempData = new CheckOutData();
    	//the info needed is filled in the dialog
        boolean okClicked = mainApp.showCheckOutDialog(tempData);
        if (okClicked) {
        	String executeResult = tempData.checkOut();
        	if (executeResult.compareTo("Invalid combination of room number and departure date!") == 0){
        		Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Check out");
                alert.setHeaderText("Error");
                alert.setContentText("Invalid combination of room number and departure date!");
                alert.showAndWait();
        	}
        	else{
        		Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Check out");
                alert.setHeaderText("Success!");
                alert.setContentText(executeResult);
                alert.showAndWait();
        	}
        }
    }

    /**
     * Called when the user clicks the cancel reservation button. Opens a dialog to edit
     * details for a reservation cancel.
     * @throws SQLException
     */
    @FXML
    private void handleRoomChange() throws SQLException {
    	//create a new reservation and pass it to reservationEditDialog
    	RoomChangeData tempData = new RoomChangeData();
    	//the info needed is filled in the dialog
        boolean okClicked = mainApp.showRoomChangeDialog(tempData);

        // to be continued..
        //
        //
        //
        if (okClicked) {
        	String executeResult = tempData.changeRoom();
        	if (executeResult.compareTo("Invalid combination of room number and dates!") == 0){
        		Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Change Room");
                alert.setHeaderText("Error");
                alert.setContentText("Invalid combination of room number and dates!");
                alert.showAndWait();
        	}
        	else if (executeResult.compareTo("No available room!")==0){
        		Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Change Room");
                alert.setHeaderText("Error");
                alert.setContentText("No available room!");
                alert.showAndWait();
        	}
        	else{
        		Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Change Room");
                alert.setHeaderText("Success!");
                alert.setContentText(executeResult);
                alert.showAndWait();
        	}
        }
    }


}