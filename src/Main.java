
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.*;  

public class Main {
	
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
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
	}
	
	public static void main(String[] args) {
		Main app = new Main();
		app.run();
		
		// Create and configure frame
		JFrame frame = new JFrame();  
		frame.setSize(650,500);
		frame.setLayout(null);  
		frame.setVisible(true);
		
		// Create labels
		JLabel titleLabel = new JLabel("Employee Details");
		JLabel socialSecurityNumberLabel = new JLabel("SSn");
		JLabel dateOfBirthLabel = new JLabel("DOB");
		JLabel firstNameLabel = new JLabel("First Name");
		JLabel surnameLabel = new JLabel("Surname");
		JLabel salaryLabel = new JLabel("Salary");
		JLabel genderLabel = new JLabel("Gender");

		// Set bounds for labels
		titleLabel.setBounds(250, 20, 150, 50);  
		socialSecurityNumberLabel.setBounds(50, 100, 150, 20);  
		dateOfBirthLabel.setBounds(50, 130, 150, 20);  
		firstNameLabel.setBounds(50, 160, 150, 20);  
		surnameLabel.setBounds(50, 190, 150, 20);  
		salaryLabel.setBounds(50, 220, 150, 20);  
		genderLabel.setBounds(50, 250, 150, 20);  
		
		// Create text fields
		JTextField socialSecurityNumber = new JTextField();
		JTextField dateOfBirth = new JTextField();
		JTextField firstName = new JTextField();
		JTextField surname = new JTextField();
		JTextField salary = new JTextField();
		JTextField gender = new JTextField();
		JTextField search = new JTextField();
		
		// Set bounds for text fields
		socialSecurityNumber.setBounds(150, 100, 250, 20);  
		dateOfBirth.setBounds(150, 130, 250, 20);  
		firstName.setBounds(150, 160, 250, 20);  
		surname.setBounds(150, 190, 250, 20);  
		salary.setBounds(150, 220, 250, 20);  
		gender.setBounds(150, 250, 250, 20);
		search.setBounds(150, 360, 250, 20);
		
		// Create buttons
		JButton addButton = new JButton("Add");
		JButton deleteButton = new JButton("Delete");
		JButton updateButton = new JButton("Update");
		JButton searchButton = new JButton("Search");
		JButton clearButton = new JButton("Clear");
		JButton previousButton = new JButton("Previous");
		JButton nextButton = new JButton("Next");
		
		// Set bounds for buttons
		addButton.setBounds(110, 300, 100, 40);
		deleteButton.setBounds(220, 300, 100, 40);
		updateButton.setBounds(330, 300, 100, 40);
		previousButton.setBounds(460, 100, 100, 40);
		nextButton.setBounds(460, 150, 100, 40);
		clearButton.setBounds(460, 230, 100, 40);
		searchButton.setBounds(400, 360, 100, 20);
		
		// Add components to frame
		frame.add(addButton);
		frame.add(deleteButton);
		frame.add(updateButton);      
		frame.add(clearButton);
		frame.add(searchButton);
		frame.add(previousButton);
		frame.add(nextButton);
		frame.add(titleLabel);
		frame.add(socialSecurityNumberLabel);
		frame.add(dateOfBirthLabel);
		frame.add(firstNameLabel);
		frame.add(surnameLabel);
		frame.add(salaryLabel);
		frame.add(genderLabel);
		frame.add(socialSecurityNumber);
		frame.add(dateOfBirth);
		frame.add(firstName);
		frame.add(surname);
		frame.add(salary);
		frame.add(gender);
		frame.add(search);
	}
}
