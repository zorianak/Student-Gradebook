/*Kim Holmes
 CSC 20
 Dr Wang
 */

import javax.swing.*; //imported for the construction of GUI
import java.awt.*;
import java.awt.event.*;
import java.io.*; //standard Java input/output package
import java.io.Serializable;
import java.util.*;

public class gradeBook {

	//set all of our variables
	static JFrame frame = new JFrame("Teacher's Gradebook");
	static JTextField userOption = new JTextField("", 6);
	static JTextField studentCount = new JTextField("0", 3);
	static JTextField studentID = new JTextField("", 17);
	static JTextField studentName = new JTextField("", 16);
	static JTextField studentGender = new JTextField("", 6);
	static JTextField studentAge = new JTextField("", 5);
	static JTextField studentLevel = new JTextField("", 8);
	static JTextField seekSID = new JTextField("", 9);
	static JTextField setLabNumber = new JTextField("", 6);
	static JTextField setScore = new JTextField("", 3);
	static JLabel studentScore = new JLabel("Score for: ");
	static JLabel studentPosition = new JLabel("student 0 of 0");
	
	//radio buttons for student's gender
	static String femaleString = "F";
	static String maleString = "M";
	//placeholder
	static String sexString = "Sexy";
	static JRadioButton femaleButton = new JRadioButton(femaleString);
	static JRadioButton maleButton = new JRadioButton(maleString);
	static JRadioButton sexualButton = new JRadioButton(sexString);
	
	//radio buttons for student's class level
	static String classLevel0 = "0";
	static String classLevel1 = "1";
	static String classLevel2 = "2";
	static String classLevel3 = "3";
	static String classLevel4 = "4";
	static JRadioButton class0Button = new JRadioButton(classLevel0);
	static JRadioButton class1Button = new JRadioButton(classLevel1);
	static JRadioButton class2Button = new JRadioButton(classLevel2);
	static JRadioButton class3Button = new JRadioButton(classLevel3);
	static JRadioButton class4Button = new JRadioButton(classLevel4);
	
	//buttons for the different panes
	static JButton saveButton = new JButton("Save & Add Students");
	static JButton addMainMenu = new JButton("Back to menu");
	static JButton deleteMainMenu = new JButton("Back to menu");
	static JButton labMainMenu = new JButton("Back to menu");
	static JButton displayMainMenu = new JButton("Back to menu");
	
	//set up part of GUI
	static CardLayout graphicalInterface = new CardLayout();
	static JPanel studentRegistry = new JPanel(graphicalInterface);
	static LinkedList<Student> studentList= new LinkedList<Student>();
	static JTextArea showStudentsTextArea;
	
	
	//start code for the program itself
	static public class Student implements Serializable {
		
		//set info for students
		int ID = 0, age = 0, classLevel = 0;
		char gender = 'S';
		String name = "haxor";
		//lab score array w/ placeholders in thar
		int[] labScores = {0,0,0,0,0,0,0,0,0,0,0,0};
		
		//setup these variables as accessible methods
		public void setID(int ID) {
			this.ID = ID;
		}
		
		public void setGender(char gender) {
			this.gender = gender;
		}
		
		public void setAge(int age) {
			this.age = age;
		}
		
		public void setClassLevel(int classLevel) {
			this.classLevel = classLevel;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public void setLabScores(int labNumber, int score) {
			labScores[labNumber-1] = score;
		}
		
		public int getID() {
			return ID;
		}
		
		public String studentName() {
			return name;
		}
		
		public String toString() {
			String studentPeople = ID +" " + gender + " " + age + " " + classLevel +" "+ name + " ";
		
			for(int i = 0; i < labScores.length; i++) {
				studentPeople += labScores[i] + " ";
			}
			return studentPeople;
		}
	}

	
	public static void main(String[] args) {
	
		ButtonActionListener buttonListener = new ButtonActionListener();
		JPanel contentPane = (JPanel)frame.getContentPane();
		
		
		//Creates menu pane and initializes its pieces
		JPanel menuPane = new JPanel(new FlowLayout(1,250,10));
		
		JLabel label = new JLabel("CSC 20 Gradebook");
		
		JLabel[] options = new JLabel[10];
		
		JLabel choiceLabel = new JLabel("Choice: ");
		Container choicePane = new Container();
		choicePane.setLayout(new FlowLayout());
		choicePane.add(choiceLabel);
		
		//set the interface options
		options[0] = new JLabel("================");
		options[1] = new JLabel("0. Open the Student File");
		options[2] = new JLabel("1. Add Students            ");
		options[3] = new JLabel("2. Delete Students         ");
		options[4] = new JLabel("3. Enter Lab Scores        ");
		options[5] = new JLabel("4. Display Students       ");
		options[6] = new JLabel("5. Save File and Exit      ");
		options[7] = new JLabel("================");
		options[8] = new JLabel("Number of Students: ");//implement l8r
		
		//menu pane(menuPane) filled with labels and text field	
		userOption.addActionListener(buttonListener);
		choicePane.add(userOption);
		label.setHorizontalAlignment(JLabel.CENTER);
		menuPane.add(label);
		
		for(int i = 0; i <= 7; i++) {
			options[i].setHorizontalAlignment(JLabel.CENTER);
			menuPane.add(options[i]);
			if(i == 6) {
				menuPane.add(choicePane);
			}
		}
		
		//pane for the number of students=
		Container numOfStudentPane = new Container();
		numOfStudentPane.setLayout(new FlowLayout());
		numOfStudentPane.add(options[8]);
		
		studentCount.setEditable(false);
		numOfStudentPane.add(studentCount);
		
		menuPane.add(numOfStudentPane);
		
		graphicalInterface.addLayoutComponent(menuPane, "menu");
		studentRegistry.add(menuPane, "menu");

		
		//Makes Add Student interface; makes input fields and options
		JPanel panel1 = new JPanel(new FlowLayout(1,250,10));
		
		panel1.add(new JLabel("Enter Student information"));
		panel1.add(new JLabel("============================"));
		
		//pane for student ID
		Container SIDPane = new Container();
		SIDPane.setLayout(new FlowLayout());
		SIDPane.add(new JLabel("SID:"));
		SIDPane.add(studentID);
		
		//takes the name
		Container NamePane = new Container();
		NamePane.setLayout(new FlowLayout());
		NamePane.add(new JLabel("Name:"));
		NamePane.add(studentName);
		
		//sex label
		Container studentGenderPane = new Container();
		studentGenderPane.setLayout(new FlowLayout());		
		studentGenderPane.add(new JLabel("Sex:"));
		
		//buttons for male/female
		studentGenderPane.add(femaleButton);
		femaleButton.setActionCommand(femaleString);
    	
		studentGenderPane.add(maleButton);
		maleButton.setActionCommand(maleString);
    	maleButton.setSelected(false);
    	
    	sexualButton.setActionCommand(sexString);
		sexualButton.setSelected(true);
		
		//make a button group for possible selections
		ButtonGroup group = new ButtonGroup();
        group.add(maleButton);
        group.add(femaleButton);
		group.add(sexualButton);
		
		//Button listeners and actions for male/female option
        maleButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		studentGender.setText("M");
        	}
        });
            
        femaleButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		studentGender.setText("F");
        	}
        });
        
        //this option is here as a "dummy" option to deselect other buttons
        sexualButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentGender.setText("S");
			}
		});
        
		studentGenderPane.add(new JLabel("     Age:"));
		studentGenderPane.add(studentAge);
		
		Container classPane = new Container();
		classPane.setLayout(new FlowLayout());
		classPane.add(new JLabel("Class Level:"));
		
		//buttons for class level
		classPane.add(class1Button);
		class1Button.setActionCommand(classLevel1);
    	class1Button.setSelected(false);
		
		classPane.add(class2Button);
		class2Button.setActionCommand(classLevel2);
    	class2Button.setSelected(false);
    	
    	classPane.add(class3Button);
		class3Button.setActionCommand(classLevel3);
    	class3Button.setSelected(false);
    	
    	classPane.add(class4Button);
		class4Button.setActionCommand(classLevel4);
    	class4Button.setSelected(false);
    	
    	class0Button.setActionCommand(classLevel0);
		class0Button.setSelected(true);
		
		//make a button group for possible selections
		ButtonGroup group2 = new ButtonGroup();
        group2.add(class1Button);
        group2.add(class2Button);
		group2.add(class3Button);
		group2.add(class4Button);
		group2.add(class0Button);
		
		//Button listeners and actions for class level option
        class1Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		studentLevel.setText("1");
        	}
        });
            
        class2Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		studentLevel.setText("2");
        	}
        });
        
        class3Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentLevel.setText("3");
			}
		});
		
		 class4Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentLevel.setText("4");
			}
		});
		
		 class0Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentLevel.setText("0");
			}
		});
		
		Container addStudentButtonPane = new Container();
		addStudentButtonPane.setLayout(new FlowLayout());
		addStudentButtonPane.add(saveButton);
		addStudentButtonPane.add(addMainMenu);
		
		panel1.add(SIDPane);
		panel1.add(NamePane);
		panel1.add(studentGenderPane);
		panel1.add(classPane);
		panel1.add(addStudentButtonPane);

		//why are we adding these again? They're ugly
		panel1.add(new JLabel("============================"));
		
		graphicalInterface.addLayoutComponent(panel1, "add student");
		studentRegistry.add(panel1, "add student");
	
		saveButton.addActionListener(buttonListener);
		addMainMenu.addActionListener(buttonListener);
		
		
		//construction of delete student pane
		JPanel panel2 = new JPanel(new FlowLayout(1,250,15));
		
		panel2.add(new JLabel("Delete Student"));
		panel2.add(new JLabel("======================="));
		
		Container deleteStudentPane = new Container();
		deleteStudentPane.setLayout(new FlowLayout());
		deleteStudentPane.add(new JLabel("Student ID:"));
		seekSID.addActionListener(buttonListener);
		deleteStudentPane.add(seekSID);
		deleteStudentPane.add(deleteMainMenu);
		
		
		panel2.add(deleteStudentPane);
		panel2.add(deleteMainMenu);
		graphicalInterface.addLayoutComponent(panel2, "delete student");
		studentRegistry.add(panel2, "delete student");
		deleteMainMenu.addActionListener(buttonListener);
	
		//construction of lab scores pane
		JPanel panel3 = new JPanel(new FlowLayout(1,250,10));
		
		panel3.add(new JLabel("Enter Lab Scores"));
		panel3.add(new JLabel("========================="));
		
		//requests the lab number
		Container labNumberPane = new Container();
		labNumberPane.setLayout(new FlowLayout(1,2,30));
		labNumberPane.add(new JLabel("Lab Number:"));
		labNumberPane.add(setLabNumber);
		
		Container labScorePane = new Container();
		labScorePane.setLayout(new FlowLayout(1,1,20));
		
		//adds the student's score into the lab array for that student
		labScorePane.add(studentScore);
		setScore.addActionListener(buttonListener);
		labScorePane.add(setScore);
		labScorePane.add(studentPosition);
		
		panel3.add(labNumberPane);
		panel3.add(labScorePane);
		panel3.add(labMainMenu);
		
		labMainMenu.addActionListener(buttonListener);
		
		graphicalInterface.addLayoutComponent(panel3, "lab scores");
		studentRegistry.add(panel3, "lab scores");	
		
		
		//construction of display student pane
		JPanel panel4 = new JPanel(new FlowLayout(1,250,10));
		panel4.add(new JLabel("Student List"));
		
		showStudentsTextArea = new JTextArea(10,25);
		JScrollPane studentScrollArea = new JScrollPane(showStudentsTextArea);
		studentScrollArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		studentScrollArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
		
		panel4.add(studentScrollArea);
		displayMainMenu.addActionListener(buttonListener);
		panel4.add(displayMainMenu);
		
		graphicalInterface.addLayoutComponent(panel4, "show students");
		studentRegistry.add(panel4, "show students");	

		contentPane.add(studentRegistry);

		frame.pack();
		frame.setSize(350,350);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	static class ButtonActionListener implements ActionListener {
		
		int studentNumber = 0, index = 0, choice = 0;
		public void actionPerformed(ActionEvent e) {
			
			//check to see if choice was made
			try {
				choice = Integer.parseInt(userOption.getText());
				
				if(!(choice >= 0 && choice <= 5)) {
					JOptionPane.showMessageDialog(null,"Please enter an Integer between 0 and 5","Invalid Choice", JOptionPane.ERROR_MESSAGE);
					userOption.setText("");
					return;
				}
			}
			catch(Exception x) {
				JOptionPane.showMessageDialog(null,"Please enter an Integer between 0 and 5","Invalid Choice Input", JOptionPane.ERROR_MESSAGE);
				userOption.setText("");
				return;
			}
			
			//this switch determines what pane opens whenever you input a number into the "choice" box
			switch(choice) {
			
			case 0:
				break;
			
			case 1:
				graphicalInterface.show(studentRegistry, "add student");
				break;
			
			case 2:
				graphicalInterface.show(studentRegistry, "delete student");
				break;
				
			case 3:
				graphicalInterface.show(studentRegistry, "lab scores");
				break;
			
			case 4:
				graphicalInterface.show(studentRegistry, "show students");
				break;
			
			case 5:
				break;
			}
			
			//Back to Menu button code
			if(e.getSource() == addMainMenu || e.getSource() == deleteMainMenu || e.getSource() == labMainMenu || e.getSource() == displayMainMenu) {
				userOption.setText("");
				studentID.setText("");
				studentName.setText("");
				studentGender.setText("");
				studentAge.setText("");
				studentLevel.setText("");
				graphicalInterface.show(studentRegistry, "menu");
				showStudentsTextArea.setText("");
				sexualButton.setSelected(true);
				class0Button.setSelected(true);
				return;
			}
			
			//Add Students Code
			if(e.getSource() == saveButton) {
				int ID = 0, age = 0, classLevel = 0;
				char gender = ' ';
				String name = "";
				class0Button.setSelected(true);
				
				//This will see if a valid student ID was input
				try {
					ID = Integer.parseInt(studentID.getText());
					if((Integer.toString(ID).length()) != 9)
					{
						JOptionPane.showMessageDialog(null,"Invalid SID, must be a series of 9 integer values","Invalid SID", JOptionPane.ERROR_MESSAGE);
						studentID.setText("");
						return;
					}
				}
				
				catch(Exception x) {
					JOptionPane.showMessageDialog(null,"Invalid SID, must be a series of 9 integer values","Invalid SID", JOptionPane.ERROR_MESSAGE);
					studentID.setText("");
					return;
				}
				
				//This will see if a gender is chosen
				try {
					gender = (studentGender.getText()).charAt(0);
					String GenderString = Character.toString(gender);
				}
				
				catch(Exception x) {
					JOptionPane.showMessageDialog(null,"Select F or M to denote your Gender","Gender Error", JOptionPane.ERROR_MESSAGE);
					studentGender.setText("");
					sexualButton.setSelected(true);
					return;		
				}
				
				//get student age; checks for no/invalid input
				try {
					age = Integer.parseInt(studentAge.getText());
					if(!(age >= 1 && age <= 99)) {
						JOptionPane.showMessageDialog(null,"Age must be an integer value between 1 and 99","Invalid Age", JOptionPane.ERROR_MESSAGE);
						studentAge.setText("");
						return;
					}
				}
				
				catch(Exception x) {
					JOptionPane.showMessageDialog(null,"Please enter an age for the student","Missing Input", JOptionPane.ERROR_MESSAGE);
					studentAge.setText("");
					return;
				}
				
				//get student class level; checks for no/invalid input
				try {
					classLevel = Integer.parseInt(studentLevel.getText());
				}
				
				catch(Exception x) {
					JOptionPane.showMessageDialog(null,"Please choose a class level","Invalid Class Level", JOptionPane.ERROR_MESSAGE);
					class0Button.setSelected(true);
					return;
				}
				
				name = studentName.getText();
				String [] validName = name.split("\\s");
				if(validName.length != 2) {
					JOptionPane.showMessageDialog(null,"You must enter both first and last name, nothing else","Cannot read instructions error", JOptionPane.ERROR_MESSAGE);
					studentName.setText("");
					return;
				}
				
				String studentNameData = "";
				for(int i = 0; i < validName.length; i++) {
					if(i == 1) {
						studentNameData += validName[i].charAt(0);
					}
					
					else{
						studentNameData += validName[i] + " ";
					}
				}

							
				Student studentRecord = new Student();
				
				studentRecord.setID(ID);
				studentRecord.setGender(gender);
				studentRecord.setAge(age);
				studentRecord.setClassLevel(classLevel);
				studentRecord.setName(studentNameData);
								
				studentList.addLast(studentRecord);
				studentNumber++;
				studentCount.setText("" + studentNumber);
				userOption.setText("");
				studentID.setText("");
				studentName.setText("");
				studentGender.setText("");
				studentAge.setText("");
				studentLevel.setText("");
				graphicalInterface.show(studentRegistry, "menu");
				sexualButton.setSelected(true);
				
			}	
			
			
			//Open the Student File Code
			if(choice == 0) {
				FileInputStream fileStream = null;
				ObjectInputStream loadFile = null;
				
				try {
					String filename = "IwishIhadAcheezeburger.nfo";
					fileStream = new FileInputStream(filename);
					loadFile = new ObjectInputStream(fileStream);
				}catch(Exception x){JOptionPane.showMessageDialog(null,"File not found: No students to load","Invalid option", JOptionPane.ERROR_MESSAGE);};
				
				try {
					while(true) {
						studentList.add((Student)loadFile.readObject());
						studentNumber++;
						studentCount.setText("" + studentNumber);
						
					}
					
				}catch(Exception x){};
				userOption.setText("");
			}
			
			
			//Delete Students Code			
			if(choice == 2) {
				if(studentList.size() == 0) {
					JOptionPane.showMessageDialog(null,"No students to delete","Empty Gradebook error", JOptionPane.ERROR_MESSAGE);
					graphicalInterface.show(studentRegistry, "menu");
					userOption.setText("");
				}
			}
						
			if(e.getSource() == seekSID ) {
				int deleteID = 0;
				boolean IDexists = false;
				
				try {
					deleteID = Integer.parseInt(seekSID.getText());
				}
				
				catch(Exception x) {
					JOptionPane.showMessageDialog(null,"Invalid SID, must be a series of 9 integer values","Invalid SID for deletion", JOptionPane.ERROR_MESSAGE);
				}
				
				for(Student studentRecord: studentList) {
					if(studentRecord.getID() == deleteID) {
						studentList.remove(studentList.indexOf(studentRecord));
						studentNumber--;
						studentCount.setText("" + studentNumber);
						IDexists = true;
						seekSID.setText("");
						break;
					}
				}
				
				if(IDexists == false) {
					JOptionPane.showMessageDialog(null,"ID not found in Gradebook","Invalid SID for deletion", JOptionPane.ERROR_MESSAGE);
				}
				
				userOption.setText("");
				seekSID.setText("");
				graphicalInterface.show(studentRegistry, "menu");
			}
			
			
			//Enter Lab Scores Code
			if(choice == 3 && e.getSource() != setScore) {
				
				if(studentList.size() == 0) {
					JOptionPane.showMessageDialog(null,"No students to Enter grades for","Empty Gradebook error", JOptionPane.ERROR_MESSAGE);
					graphicalInterface.show(studentRegistry, "menu");
					userOption.setText("");
				}
				
				Student studentData;
				if(studentList.size() != 0) {
					studentData = studentList.get(index);

					studentScore.setText("Score for " + studentData.studentName() + ": ");
					studentPosition.setText("Student " + (studentList.indexOf(studentData)+1) +" of " + studentList.size());
				}
			}
			
			if(e.getSource() == setScore) {
				int labNumber = 0, labScore = 0;
				
				try {
					labNumber = Integer.parseInt(setLabNumber.getText());
					if(!(labNumber >= 0 && labNumber <= 12)) {
						JOptionPane.showMessageDialog(null,"Invalid lab number: please enter an integer between 1 and 12","Invalid lab number", JOptionPane.ERROR_MESSAGE);
						setLabNumber.setText("");
						return;
					}
				}
				
				catch(Exception x) {
					JOptionPane.showMessageDialog(null,"Invalid lab number: please enter an integer between 1 and 12","Invalid lab number", JOptionPane.ERROR_MESSAGE);
					setLabNumber.setText("");
					return;				
				}
				
				try {
					labScore = Integer.parseInt(setScore.getText());
					if(labScore >= 0 && labScore <= 100) {
						JOptionPane.showMessageDialog(null,"Invalid score: please enter an integer between 1 and 100","Invalid score", JOptionPane.ERROR_MESSAGE);
						setScore.setText("");
						return;
					}
				}
				
				catch(Exception x) {
					JOptionPane.showMessageDialog(null,"Invalid score: please enter an integer between 1 and 100","Invalid score", JOptionPane.ERROR_MESSAGE);
					setScore.setText("");
					return;			
				}
				
				Student studentData;
				if(studentList.size() != 0) {
					studentData = studentList.get(index);
					studentData.setLabScores(labNumber, labScore);
				
					if(studentList.get(index) == studentList.getLast()) {
						graphicalInterface.show(studentRegistry, "menu");
						userOption.setText("");
						return;
					}
					
					else {
						index++;
						studentData = studentList.get(index);
					}
					
					studentScore.setText("Score for " + studentData.studentName() + ": ");
					studentPosition.setText("Student " + (studentList.indexOf(studentData)+1) +" of " + studentList.size());
					setScore.setText("");
				}
			}
			
			else if (e.getSource() != setScore && choice == 3 && e.getSource() != userOption) {
				graphicalInterface.show(studentRegistry, "menu");
				userOption.setText("");
				return;
			}
			
			//Display Students Code
			if(choice == 4) {
				if(studentList.size() != 0) {
					for(Student studentData: studentList) {
						showStudentsTextArea.append(studentData.toString()+ "\n");
					}
					return;
				}
				
				else{
					JOptionPane.showMessageDialog(null,"No students to show","Empty Gradebook error", JOptionPane.ERROR_MESSAGE);
					graphicalInterface.show(studentRegistry, "menu");
					userOption.setText("");
					return;
				}
			}


			//Save File and Exit Code
			if(choice == 5) {
				String filename = "IwishIhadAcheezeburger.nfo";

				try {
					FileOutputStream fileStream = new FileOutputStream(filename);
					ObjectOutputStream saveFile = new ObjectOutputStream(fileStream);
					for(Student studentData: studentList) {
						saveFile.writeObject(studentData);
					}
					saveFile.close();
				}catch(Exception x){};
				
				System.exit(0);
			}
		}
	}
}