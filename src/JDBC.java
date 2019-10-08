import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JTextField;


public class JDBC {
	
	/** The local record of the SQL table **/
	private ResultSet resultSet;

	/** The count for navigating the ResultSet object **/
	private int count = 0;

	/** Checks if connection is establishing for first time **/
	public Boolean firstRun = true;

	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "root";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are using */
	private final String dbName = "test";
	
	/** The name of the table we are testing with */
	private final String tableName = "employee";
	
	/** Getters **/
	private ResultSet getResultSet() {
		return resultSet;
	}
	
	private int getCount() {
		return count;
	}
	
	public Boolean getFirstRun() {
		return firstRun;
	}

	/** Setters **/
	private void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	
	private void setCount(int count) {
		this.count = count;
	}
	
	public void setFirstRun(Boolean firstRun) {
		this.firstRun = firstRun;
	}
	
	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);

		return conn;
	}
	
	public void run() {
		// Connect to MySQL
		try {
			this.getConnection();
			System.out.println("Connected to database");
			this.getResultSetObject();
			System.out.println(this.getResultSet().toString());
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
	}
	
	public void getResultSetObject() {
		try {
			Statement s = this.getConnection().createStatement();
			s.executeQuery("SELECT * FROM employee");
			setResultSet(s.getResultSet());
			setCount(0);
			getResultSet().close();
			s.close();
			System.out.println (count + " rows were retrieved");
		} catch (SQLException e) {
			System.err.println ("Error message: "+ e.getMessage());
			System.err.println ("Error number: " + e.getErrorCode());
		}
	}

	public void addEmployee(int socialSecurityNumber, String dateOfBirth, String firstName, String surname, int salary, String gender) {
		try {
			Statement s = this.getConnection().createStatement();
			s.executeUpdate("INSERT INTO employee ("
					+ "social_security_number,"
					+ "date_of_birth,"
					+ "first_name,"
					+ "surname,"
					+ "salary,"
					+ "gender)"
					+ " VALUES ("
					+ socialSecurityNumber + ", '"
					+ dateOfBirth + "', '"
					+ firstName + "', '"
					+ surname + "',"
					+ salary + ", '"
					+ gender + "')");
			s.close();
			System.out.println("Row inserted!");
			this.getResultSetObject();
		} catch (SQLException e) {
			System.err.println ("Error message: "+ e.getMessage());
			System.err.println ("Error number: " + e.getErrorCode());
		}
	}
	
	public void deleteEmployee(int socialSecurityNumber) {
		try {
			Statement s = this.getConnection().createStatement();
			s.executeUpdate("DELETE FROM employee WHERE " 
				+ "social_security_number = " + socialSecurityNumber);
			s.close();
			System.out.println("Row deleted!");
			this.getResultSetObject();
		} catch (SQLException e) {
			System.err.println ("Error message: "+ e.getMessage());
			System.err.println ("Error number: " + e.getErrorCode());
		}
	}
	
	public void updateEmployee(int socialSecurityNumber, String dateOfBirth, String firstName, String surname, int salary, String gender) {
		try {
			Statement s = this.getConnection().createStatement();
			s.executeUpdate("UPDATE employee SET "
				+ "social_security_number = " + socialSecurityNumber
				+ ", date_of_birth = '" + dateOfBirth
				+ "', first_name = '"+ firstName
				+ "', surname = '" + surname
				+ "', salary = " + salary
				+ ", gender = '" + gender
				+ "' WHERE social_security_number = " + socialSecurityNumber );
			s.close();
			System.out.println("Row updated!");
			this.getResultSetObject();
		} catch (SQLException e) {
			System.err.println ("Error message: "+ e.getMessage());
			System.err.println ("Error number: " + e.getErrorCode());
		}
	}
	
	public void searchEmployee(String surname) {
		try {
			Statement s = this.getConnection().createStatement();
			s.executeQuery("SELECT * FROM employee WHERE surname = " + surname);
			ResultSet rs = s.getResultSet();
			System.out.println ("Row was retrieved of id:" + rs.getInt("id"));
			rs.close();
			s.close();
			this.getResultSetObject();
		} catch (SQLException e) {
			System.err.println ("Error message: "+ e.getMessage());
			System.err.println ("Error number: " + e.getErrorCode());
		}
	}
	
	public void nextEmployee() {
		try {
			while (getResultSet().next()) {
				int id = getResultSet().getInt("id");
				System.out.println("Current ID: " + id);
				setCount(getCount()+1);
			}
		} catch (SQLException e) {
			System.err.println ("Error message: "+ e.getMessage());
			System.err.println ("Error number: " + e.getErrorCode());
		}
	}
}
