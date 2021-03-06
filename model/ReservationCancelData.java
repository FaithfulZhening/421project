package g45.project.model;
import java.sql.*;
public class ReservationCancelData {
	private String reservationNum;

	//constructor
	public ReservationCancelData(){

	}

	public void setReservationCancelData(String reservationNum){
		this.reservationNum = reservationNum;
	}

	public Connection connect() throws SQLException{
			DriverManager.registerDriver(new org.postgresql.Driver());
			Connection conn = DriverManager.getConnection(
					"jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421",
					"cs421g45","Comp421g45"
					);
			return conn;
		}

	public String cancelReservation() throws SQLException{
			// delete info in calendar first then reservation
			String result = "true";
			Connection conn = connect();
			Statement stmt = conn.createStatement();
			String reservationCheckSql = "SELECT reservation_number FROM reservation WHERE reservation_number = '" + reservationNum + "'";
			ResultSet rs = stmt.executeQuery(reservationCheckSql);
			if(!rs.next())
				return "false";
			rs.close();

			String calendarSql = "UPDATE calendar SET reservation_number = NULL WHERE reservation_number = '" + reservationNum + "'";
			stmt.executeUpdate(calendarSql);
			String reservationSql = "DELETE FROM reservation WHERE reservation_number = '" + reservationNum + "'";
			stmt.executeUpdate(reservationSql);
			stmt.close();
			conn.close();
			return result;
		}
}