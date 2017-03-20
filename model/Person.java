package g45.project.model;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * written based on the code writing by @author Marco Jakob
 *
 * @author Zhening Zhang
 */
public class Person {

	private final StringProperty guestID;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty phone;
    private final StringProperty email;
    private final BooleanProperty membership;
    private final StringProperty personalID;
    private final IntegerProperty paymentSoFar;
    //private final ObjectProperty<LocalDate> birthday;

    /**
     * Default constructor.
     */
    public Person() {
        this(null, null,null,null,null,null,null,0);
    }

    /**
     * Constructor with some initial data.
     *
     * @param firstName
     * @param lastName
     */
    public Person(String firstName, String lastName, String guestID,
    		String phone,String email,Boolean membership,String personalID, int paymentSoFar  ) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.guestID = new SimpleStringProperty(guestID);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.membership = new SimpleBooleanProperty(membership);
        this.personalID = new SimpleStringProperty(personalID);
        this.paymentSoFar = new SimpleIntegerProperty(paymentSoFar);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getGuestID() {
        return guestID.get();
    }

    public void setGuestID(String street) {
        this.guestID.set(street);
    }

    public StringProperty guestIDProperty() {
        return guestID;
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public boolean getMembership(){
    	return membership.get();
    }

    public void setMembership(boolean membership){
    	this.membership.set(membership);
    }

    public BooleanProperty membershipProperty(){
    	return membership;
    }

    public String getPersonalID() {
        return personalID.get();
    }

    public void setPersonalID(String personalID) {
        this.personalID.set(personalID);
    }

    public StringProperty personalIDProperty() {
        return personalID;
    }

    public int getPaymentSoFar() {
        return paymentSoFar.get();
    }

    public void setPaymentSoFar(int paymentSoFar) {
        this.paymentSoFar.set(paymentSoFar);
    }

    public IntegerProperty paymentSoFarProperty() {
        return paymentSoFar;
    }


}
