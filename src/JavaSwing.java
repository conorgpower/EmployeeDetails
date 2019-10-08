import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JavaSwing extends JFrame implements ActionListener {
	JDBC db = new JDBC();

	JLabel titleLabel;
	JLabel socialSecurityNumberLabel;
	JLabel dateOfBirthLabel;
	JLabel firstNameLabel;
	JLabel surnameLabel;
	JLabel salaryLabel;
	JLabel genderLabel;
	
	JTextField socialSecurityNumber;
	JTextField dateOfBirth;
	JTextField firstName;
	JTextField surname;
	JTextField salary;
	JTextField gender;
	JTextField search;
	
	JButton addButton;
	JButton deleteButton;
	JButton updateButton;
	JButton clearButton;
	JButton previousButton;
	JButton nextButton;
	JButton searchButton;
	
	JavaSwing() {
		// Create labels
		titleLabel = new JLabel("Employee Details");
		socialSecurityNumberLabel = new JLabel("SSn");
		dateOfBirthLabel = new JLabel("DOB");
		firstNameLabel = new JLabel("First Name");
		surnameLabel = new JLabel("Surname");
		salaryLabel = new JLabel("Salary");
		genderLabel = new JLabel("Gender");
	
		// Set bounds for labels
		titleLabel.setBounds(250, 20, 150, 50);  
		socialSecurityNumberLabel.setBounds(50, 100, 150, 20);  
		dateOfBirthLabel.setBounds(50, 130, 150, 20);  
		firstNameLabel.setBounds(50, 160, 150, 20);  
		surnameLabel.setBounds(50, 190, 150, 20);  
		salaryLabel.setBounds(50, 220, 150, 20);  
		genderLabel.setBounds(50, 250, 150, 20);  
		
		// Create text fields
		socialSecurityNumber = new JTextField();
		dateOfBirth = new JTextField();
		firstName = new JTextField();
		surname = new JTextField();
		salary = new JTextField();
		gender = new JTextField();
		search = new JTextField();
		
		// Set bounds for text fields
		socialSecurityNumber.setBounds(150, 100, 250, 20);  
		dateOfBirth.setBounds(150, 130, 250, 20);  
		firstName.setBounds(150, 160, 250, 20);  
		surname.setBounds(150, 190, 250, 20);  
		salary.setBounds(150, 220, 250, 20);  
		gender.setBounds(150, 250, 250, 20);
		search.setBounds(150, 360, 250, 20);
		
		// Create buttons
		addButton = new JButton("Add");
		deleteButton = new JButton("Delete");
		updateButton = new JButton("Update");
		clearButton = new JButton("Clear");
		previousButton = new JButton("Previous");
		nextButton = new JButton("Next");
		searchButton = new JButton("Search");
		
		// Add action listeners to buttons
		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		updateButton.addActionListener(this);
		previousButton.addActionListener(this);
		nextButton.addActionListener(this);
		clearButton.addActionListener(this);
		searchButton.addActionListener(this);
		
		// Set bounds for buttons
		addButton.setBounds(110, 300, 100, 40);
		deleteButton.setBounds(220, 300, 100, 40);
		updateButton.setBounds(330, 300, 100, 40);
		previousButton.setBounds(460, 100, 100, 40);
		nextButton.setBounds(460, 150, 100, 40);
		clearButton.setBounds(460, 230, 100, 40);
		searchButton.setBounds(400, 360, 100, 20);
		
		// Add components to frame
		add(addButton);
		add(deleteButton);
		add(updateButton);      
		add(clearButton);
		add(previousButton);
		add(nextButton);
		add(searchButton);
		add(titleLabel);
		add(socialSecurityNumberLabel);
		add(dateOfBirthLabel);
		add(firstNameLabel);
		add(surnameLabel);
		add(salaryLabel);
		add(genderLabel);
		add(socialSecurityNumber);
		add(dateOfBirth);
		add(firstName);
		add(surname);
		add(salary);
		add(gender);
		add(search);
	}
	
	public void clear() {
		socialSecurityNumber.setText("");
		dateOfBirth.setText("");
		firstName.setText("");
		surname.setText("");
		salary.setText("");
		gender.setText("");
		search.setText("");
	}
	
	public void firstRun() {
		db.run();
		db.setFirstRun(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if(button == addButton) {
			int socialSecurityNumberInt = Integer.parseInt(socialSecurityNumber.getText());
			int salaryInt = Integer.parseInt(salary.getText());
			db.addEmployee(socialSecurityNumberInt, dateOfBirth.getText(), firstName.getText(), surname.getText(), salaryInt, gender.getText());
		} else if (button == deleteButton) {
			int socialSecurityNumberInt = Integer.parseInt(socialSecurityNumber.getText());
			db.deleteEmployee(socialSecurityNumberInt);
		} else if (button == updateButton) {
			int socialSecurityNumberInt = Integer.parseInt(socialSecurityNumber.getText());
			int salaryInt = Integer.parseInt(salary.getText());
			db.updateEmployee(socialSecurityNumberInt, dateOfBirth.getText(), firstName.getText(), surname.getText(), salaryInt, gender.getText());
		} else if (button == clearButton) {
			clear();
//		} else if (button == previousButton) {
//			db.previousEmployee();
		} else if (button == nextButton) {
			db.nextEmployee();
		} else if (button == searchButton) {
			db.searchEmployee(search.getText());
		} else {
			System.out.println("ERROR!! Action Listener Broken!");
		}
	}
}
