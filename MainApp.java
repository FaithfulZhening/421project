package g45.project;

import java.io.IOException;
import java.sql.*;

import g45.project.model.CheckInData;
import g45.project.model.CheckOutData;
import g45.project.model.ExtendDateData;
import g45.project.model.Person;
import g45.project.model.Reservation;
import g45.project.model.ReservationCancelData;
import g45.project.model.RoomChangeData;
import g45.project.view.CancelReservationController;
import g45.project.view.CheckInDialogController;
import g45.project.view.CheckOutDialogController;
import g45.project.view.ExtendDateController;
import g45.project.view.PersonOverviewController;
import g45.project.view.ReservationEditDialogController;
import g45.project.view.RoomChangeDialogController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    public MainApp() throws SQLException{
		//build connection and statement
		Connection cnnt = connect();
		Statement stmt = cnnt.createStatement();

		//execute SQL query

		String query = "SELECT * FROM guest";
		ResultSet result = stmt.executeQuery(query);
		//read from result
		while(result.next()){
			String guest_id = result.getString("guest_id");
			String first_name = result.getString("first_name");
			String last_name = result.getString("last_name");
			String phone = result.getString("phone");
			String email = result.getString("email");
			Boolean membership = result.getBoolean("Membership");
			String personal_id = result.getString("personal_id");
			int payment_so_far = result.getInt("payment_so_far");
			personData.add(new Person(first_name,last_name,guest_id,phone,email
					,membership,personal_id,payment_so_far));
		}

		//end execute SQL query

		result.close();
		stmt.close();
		cnnt.close();
    }

    /**
     * Returns the data as an observable list of Persons.
     * @return
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Project Demo");

        initRootLayout();

        showPersonOverview();

    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    //connect database method
    public static Connection connect() throws SQLException{
		DriverManager.registerDriver(new org.postgresql.Driver());
		Connection conn = DriverManager.getConnection(
				"jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421",
				"cs421g45","Comp421g45"
		);
		return conn;
	}

    /**
     * Opens a dialog to edit a reservation. If the user
     * clicks OK, the changes are saved into the provided reservation object and true
     * is returned.
     *
     * @param reserv the Reservation object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showReservationEditDialog(Reservation reserv) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ReservationEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Reservation");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            //the mainapp receives the reservation from the controller and pass it to the controller
            ReservationEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setReservation(reserv);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @param checkInDetail the CheckInData object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showCheckInDialog(CheckInData checkInDetail) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CheckInDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("CheckIn");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            //the mainapp receives the reservation from the controller and pass it to the controller
            CheckInDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCheckInData(checkInDetail);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @param reservation the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showCancelReservationDialog(ReservationCancelData reservation) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CancelReservationDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cancel Reservation");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            //the mainapp receives the reservation from the controller and pass it to the controller
            CancelReservationController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setReservationData(reservation);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Opens a dialog to edit details date extending. If the user
     * clicks OK, the changes are saved into the provided ExtendDateData object and true
     * is returned.
     *
     * @param extendDate the ExtendDateData object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showExtendDateDialog(ExtendDateData extendDate) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ExtendDateDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Extend Date");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            //the mainapp receives the reservation from the controller and pass it to the controller
            ExtendDateController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setExtendDate(extendDate);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Opens a dialog to edit details for check out. If the user
     * clicks OK, the changes are saved into the provided checkOutData and true
     * is returned.
     *
     * @param checkOutData the CheckOutData to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showCheckOutDialog(CheckOutData checkOutData) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CheckOutDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Check out");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            //the mainapp receives the reservation from the controller and pass it to the controller
            CheckOutDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCheckOutData(checkOutData);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Opens a dialog to edit details for room change. If the user
     * clicks OK, the changes are saved into the provided RoomChangeData and true
     * is returned.
     *
     * @param roomChangData the RoomChangeData to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showRoomChangeDialog(RoomChangeData roomChangeData) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RoomChangeDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Change Room");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            //the mainapp receives the reservation from the controller and pass it to the controller
            RoomChangeDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setRoomChangeData(roomChangeData);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
