import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame implements ActionListener
{
	private String[] Labels = {"New Student", "New Course", "List Students", "List Courses", "Add Grade", "List Grade"};
	private JPanel options = new JPanel(new GridLayout(6, 1, 10, 5));
	private JPanel view;
	private JPanel[] viewPanels = new JPanel[6];
	
	private JButton NewStudent_AddButton = new JButton("Add");
	private JButton NewStudent_ClearButton = new JButton("Clear");
	private JTextField NewStudent_ID = new JTextField(); 
	private JTextField NewStudent_Name = new JTextField(); 
	private JTextField NewStudent_Surname = new JTextField(); 
	
	private JButton NewCourse_AddButton = new JButton("Add");
	private JButton NewCourse_ClearButton = new JButton("Clear");
	private JTextField NewCourse_ID = new JTextField(); 
	private JTextField NewCourse_Title = new JTextField();
	
	private JTextArea ListStudents_Area = new JTextArea();
	
	private JTextArea ListCourses_Area = new JTextArea();
	
	private JTextField AddGrade_SID = new JTextField();
	private JTextField AddGrade_CID = new JTextField();
	private JTextField AddGrade_Year = new JTextField();
	private JTextField AddGrade_Grade = new JTextField();
	private JButton AddGrade_AddButton = new JButton("Add");
	private JButton AddGrade_ClearButton = new JButton("Clear");
	
	private JTextField ListGarde_SID = new JTextField();
	private JButton ListGrade_SearchButton = new JButton("Search");
	private JTextArea ListGrade_Result = new JTextArea();
	
	public Main()
	{
		super();
		//---
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("Student Database");
		GridBagLayout g = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		this.setLayout(new GridLayout(1, 2, 10, 0));
		this.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));
		
		//---options panel
		ButtonGroup rbg = new ButtonGroup();
		for(int i = 0;i < this.Labels.length;i++)
		{
			JRadioButton rb = new JRadioButton(this.Labels[i]);
			if(i == 0)
			{
				rb.setSelected(true);
			}
			rbg.add(rb);
			rb.addActionListener(this);
			options.add(rb);
			this.viewPanels[i] = new JPanel();
			this.viewPanels[i].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(this.Labels[i]), new EmptyBorder(5,5,5,5)));
		}
		options.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Options"), new EmptyBorder(5,5,5,5)));
		//---adding event to buttons
		NewStudent_AddButton.addActionListener(this);
		NewStudent_ClearButton.addActionListener(this);
		NewCourse_AddButton.addActionListener(this);
		NewCourse_ClearButton.addActionListener(this);
		AddGrade_AddButton.addActionListener(this);
		AddGrade_ClearButton.addActionListener(this);
		ListGrade_SearchButton.addActionListener(this);
		//---panels
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 10, 5, 0);
		//---new student
		this.viewPanels[0].setLayout(g);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.viewPanels[0].add(new JLabel("Student ID:"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.viewPanels[0].add(NewStudent_ID, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.viewPanels[0].add(new JLabel("Student Name:"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.viewPanels[0].add(NewStudent_Name, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.viewPanels[0].add(new JLabel("Student Surname:"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.viewPanels[0].add(NewStudent_Surname, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.viewPanels[0].add(NewStudent_AddButton, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		this.viewPanels[0].add(NewStudent_ClearButton, gbc);
		//---new course
		this.viewPanels[1].setLayout(g);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.viewPanels[1].add(new JLabel("Course ID:"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.viewPanels[1].add(NewCourse_ID, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.viewPanels[1].add(new JLabel("Course Title:"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.viewPanels[1].add(NewCourse_Title, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.viewPanels[1].add(NewCourse_AddButton, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.viewPanels[1].add(NewCourse_ClearButton, gbc);
		//---list students
		BoxLayout b = new BoxLayout(this.viewPanels[2], BoxLayout.Y_AXIS);
		this.viewPanels[2].setLayout(b);
		ListStudents_Area.setColumns(30);
		ListStudents_Area.setBorder(BorderFactory.createLoweredBevelBorder());
		ListStudents_Area.setEditable(false);
		this.viewPanels[2].add(ListStudents_Area);
		//---list courses
		b = new BoxLayout(this.viewPanels[3], BoxLayout.Y_AXIS);
		this.viewPanels[3].setLayout(b);
		ListCourses_Area.setColumns(30);
		ListCourses_Area.setBorder(BorderFactory.createLoweredBevelBorder());
		ListCourses_Area.setEditable(false);
		this.viewPanels[3].add(ListCourses_Area);
		//---add grade
		this.viewPanels[4].setLayout(g);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.viewPanels[4].add(new JLabel("Student ID:"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.viewPanels[4].add(AddGrade_SID, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.viewPanels[4].add(new JLabel("Course ID:"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.viewPanels[4].add(AddGrade_CID, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.viewPanels[4].add(new JLabel("Year:"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.viewPanels[4].add(AddGrade_Year, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.viewPanels[4].add(new JLabel("Grade:"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.viewPanels[4].add(AddGrade_Grade, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		this.viewPanels[4].add(AddGrade_AddButton, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		this.viewPanels[4].add(AddGrade_ClearButton, gbc);
		//---list grade
		this.viewPanels[5].setLayout(g);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.viewPanels[5].add(new JLabel("Student ID:"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		ListGarde_SID.setColumns(10);
		this.viewPanels[5].add(ListGarde_SID, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		this.viewPanels[5].add(ListGrade_SearchButton, gbc);
		
		gbc.gridx = 0;
		gbc.gridwidth = 3;
		gbc.gridy = 2;
		ListGrade_Result.setRows(7);
		JScrollPane sp = new JScrollPane(ListGrade_Result);
		this.viewPanels[5].add(sp, gbc);
		//---view panel, first time the app runs, make 'new student' panel shown
		this.view = this.viewPanels[0];
		//---add panels
		this.add(options);
		this.add(view);
		//---pack
		this.pack();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() instanceof JRadioButton)
		{
			String label = ((JRadioButton)e.getSource()).getText();
			//---new student
			if(label.equals(this.Labels[0]))
			{
				this.view = this.viewPanels[0];
			}
			//---new course
			else if(label.equals(this.Labels[1]))
			{
				this.view = this.viewPanels[1];
			}
			//---list student
			else if(label.equals(this.Labels[2]))
			{
				this.ListStudents_Area.setText(Main.listStudents(false));
				this.view = this.viewPanels[2];
			}
			//---list courses
			else if(label.equals(this.Labels[3]))
			{
				this.ListCourses_Area.setText(Main.listCourses(false));
				this.view = this.viewPanels[3];
			}
			//---add grade
			else if(label.equals(this.Labels[4]))
			{
				this.view = this.viewPanels[4];
			}
			//---list grade
			else if(label.equals(this.Labels[5]))
			{
				this.view = this.viewPanels[5];
			}
			else 
			{
				System.out.println("WTF");
			}
			//---update UI
			this.getContentPane().removeAll();
			this.getContentPane().repaint();
			this.add(options);
			this.add(view);
			this.pack();
		}
		if(e.getSource() instanceof JButton)
		{
			JButton bt = (JButton)e.getSource();
			if(bt == NewStudent_AddButton)
			{
				if(NewStudent_ID.getText().trim().isEmpty() || NewStudent_Name.getText().trim().isEmpty() || NewStudent_Surname.getText().trim().isEmpty())
				{
					JOptionPane.showMessageDialog(this, "All fields must be entered!");
				}
				else
				{
					Student student = new Student(NewStudent_ID.getText().trim(), format(NewStudent_Name.getText()), format(NewStudent_Surname.getText()));
					writeToFile(StudentsFilePath, student.toString(), true);
					JOptionPane.showMessageDialog(this, "Student saved!");
				}
			}
			else if(bt == NewStudent_ClearButton)
			{
				NewStudent_ID.setText("");
				NewStudent_Name.setText("");
				NewStudent_Surname.setText("");
			}
			else if(bt == NewCourse_AddButton)
			{
				if(NewCourse_ID.getText().trim().isEmpty() || NewCourse_Title.getText().trim().isEmpty())
				{
					JOptionPane.showMessageDialog(this, "All fields must be entered!");
				}
				else
				{
					Course course = new Course(NewCourse_ID.getText().trim(), format(NewCourse_Title.getText()));
					writeToFile(coursesFilePath, course.toString(), true);
					JOptionPane.showMessageDialog(this, "Course saved!");
				}
			}
			else if(bt == NewCourse_ClearButton)
			{
				NewCourse_ID.setText("");
				NewCourse_Title.setText("");
			}
			else if(bt == AddGrade_AddButton)
			{
				if(AddGrade_CID.getText().trim().isEmpty() || AddGrade_SID.getText().trim().isEmpty() || AddGrade_Year.getText().trim().isEmpty() || AddGrade_Grade.getText().trim().isEmpty())
				{
					JOptionPane.showMessageDialog(this, "All fields must be entered!");
				}
				else
				{
					String ID = AddGrade_SID.getText().trim();
					Student stduent = findStduentByID(ID);
					ID = AddGrade_CID.getText().trim();
					Course course = findCourseByID(ID);
					if(stduent == null)
					{
						JOptionPane.showMessageDialog(this, "No such student could be found!");
					}
					else if(course == null)
					{
						JOptionPane.showMessageDialog(this, "No such course could be found!");
					}
					else
					{
						String term = AddGrade_Year.getText().trim();
						String letter = AddGrade_Grade.getText().trim();
						String info = String.format("%s-%s-%s-%s", stduent.getStudentNo(), course.getCourseID(), term, letter);
						writeToFile(takenCoursesFilePath, info, true);
						JOptionPane.showMessageDialog(this, "Course assigned to student!");
					}
				}
			}
			else if(bt == AddGrade_ClearButton)
			{
				AddGrade_CID.setText("");
				AddGrade_SID.setText("");
				AddGrade_Year.setText("");
				AddGrade_Grade.setText("");
			}
			else if(bt == ListGrade_SearchButton)
			{
				if(ListGarde_SID.getText().trim().isEmpty())
				{
					JOptionPane.showMessageDialog(this, "Enter a student ID!");
				}
				else
				{
					String ID = ListGarde_SID.getText().trim();
					Student stduent = findStduentByID(ID);
					if(stduent == null)
					{
						JOptionPane.showMessageDialog(this, "No such student could be found!");
					}
					else
					{
						ArrayList<GradeInput> courses = findCoursesTakenByStudent(ID);
						if(courses.isEmpty())
						{
							JOptionPane.showMessageDialog(this, stduent.getName() + " " + stduent.getSurname() + " has not taken any courses!");
							
						}
						else
						{
							StringBuilder text = new StringBuilder();
							text.append(String.format("%s\t%-60s\t%s\t%s", "Course ID", "Title", "Year", "Grade"));
							text.append(System.getProperty("line.separator"));
							text.append(System.getProperty("line.separator"));
							for(int i = 0;i < courses.size();i++)
							{
								GradeInput g = courses.get(i);
								text.append(String.format("%s\t%-60s\t%s\t%s",g.getCourse().getCourseID(), g.getCourse().getCourseName(), g.getYear(), g.getGrade()));
								text.append(System.getProperty("line.separator"));
							}
							this.ListGrade_Result.setText(text.toString());
						}
					}
				}
			}
		}
		
	}
	
	//----STATIC MEMBERS
	//---file paths1
	private static String StudentsFilePath = "students.txt";
	private static String coursesFilePath = "courses.txt";
	private static String takenCoursesFilePath = "takenCourses.txt";
	
	public static void main(String[] args)
	{
		//---logic for entering the correct mode, Console or GUI
		boolean valid = false;
		byte choice = 2;
		while(!valid)
		{
			try
			{
				String input = JOptionPane.showInputDialog(null, "Console: 1\nGUI: 2");
				if(input == null)
				{
					//---of user cancels
					System.exit(0);
				}
				else
				{
					choice = Byte.parseByte(input);
					//---if not 1 or 2, ask again
					if(choice == 1 || choice == 2)
					{
						valid = true;
					}
					else
					{
						throw new Exception();
					}
				}
			}
			//---if a number is not entered, ask again
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Enter '1' or '2'!");
			}
		}
		//---logic for Console
		if(choice == 1)
		{
			boolean exit = false;
			byte option;
			while(!exit)
			{
				//---display main options
				System.out.println("----------------------------------------------------");
				System.out.printf("%-10s %-10s %-10s %-10s", "1-Add", "2-List", "3-Erase", "4-Quit");
				System.out.println();
				System.out.println("----------------------------------------------------");
				//----take option
				option = getOption(4, false);
				if(option == 1)
				{
					//---display sub-options
					System.out.println("\t1-Student");
					System.out.println("\t2-Course");
					System.out.println("\t3-Assign Course");
					//----take option
					option = getOption(3, true);
					if(option == 1)
					{
						String no = prompt("Enter student ID", true, false);
						String name = prompt("Enter student name", true, true);
						String surname = prompt("Enter student surname", true, true);
						Student student = new Student(no, name, surname);
						writeToFile(StudentsFilePath, student.toString(), true);
					}
					else if(option == 2)
					{
						String CourseID = prompt("Enter course ID", true, false);
						String CourseTitle = prompt("Enter course title", true, true);
						Course course = new Course(CourseID, CourseTitle);
						writeToFile(coursesFilePath, course.toString(), true);
					}
					else
					{
						String ID = prompt("Enter student ID", true, false);
						Student stduent = findStduentByID(ID);
						while(stduent == null)
						{
							System.out.println("\tNo such student could be found!");
							ID = prompt("Enter student ID", true, false);
							stduent = findStduentByID(ID);
						}
						
						ID = prompt("Enter course ID", true, false);
						Course course = findCourseByID(ID);
						while(course == null)
						{
							System.out.println("\tNo such course could be found!");
							ID = prompt("Enter course ID", true, false);
							course = findCourseByID(ID);
						}
						
						String term = prompt("Enter year", true, true);
						String letter = prompt("Enter grade", true, false);
						String info = String.format("%s-%s-%s-%s", stduent.getStudentNo(), course.getCourseID(), term, letter);
						writeToFile(takenCoursesFilePath, info, true);
					}
				}
				else if(option == 2)
				{
					//---display sub-options
					System.out.println("\t1-Students");
					System.out.println("\t2-Courses");
					System.out.println("\t3-Taken Courses");
					//----take option
					option = getOption(3, true);
					if(option == 1)
					{
						System.out.println(listStudents(true));
					}
					else if(option == 2)
					{
						System.out.println(listCourses(true));
					}
					else
					{
						String ID = prompt("Enter student ID", true, false);
						Student stduent = findStduentByID(ID);
						while(stduent == null)
						{
							System.out.println("\tNo such student could be found!");
							ID = prompt("Enter student ID", true, false);
							stduent = findStduentByID(ID);
						}
			
						ArrayList<GradeInput> courses = findCoursesTakenByStudent(ID);
						if(courses.isEmpty())
						{
							System.out.printf("\t%s has not taken any courses!", stduent.getName() + " " + stduent.getSurname());
							System.out.println();
						}
						else
						{
							System.out.printf("\t%s has taken the following courses:", stduent.getName() + " " + stduent.getSurname());
							System.out.println();
							System.out.println();
							System.out.printf("\t%-10s %-20s %-10s %s", "Course ID", "Title", "Year", "Grade");
							System.out.println();
							System.out.println("\t---------- -------------------- ---------- --------");
							for(int i = 0;i < courses.size();i++)
							{
								GradeInput g = courses.get(i);
								System.out.printf("\t%-10s %-20s %-10s %s", g.getCourse().getCourseID(), g.getCourse().getCourseName(), g.getYear(), g.getGrade());
								System.out.println();
							}
						}
					}
				}
				else if(option == 3)
				{
					//---display sub-options
					System.out.println("\t1-Student");
					System.out.println("\t2-Course");
					System.out.println("\t3-Drop Course");
					//----take option
					option = getOption(3, true);
					if(option == 1)
					{
						String ID = prompt("Enter student ID", true, false);
						while(findStduentByID(ID) == null)
						{
							System.out.println("\tNo such student could be found!");
							ID = prompt("Enter student ID", true, false);
						}
						deleteRow(ID, 0, StudentsFilePath);
						deleteRow(ID, 0, takenCoursesFilePath);
						System.out.println("\n\tStudent deleted successfully");
					}
					else if(option == 2)
					{
						String ID = prompt("Enter course ID", true, false);
						while(findCourseByID(ID) == null)
						{
							System.out.println("\tNo such course could be found!");
							ID = prompt("Enter course ID", true, false);
						}
						deleteRow(ID, 0, coursesFilePath);
						deleteRow(ID, 1, takenCoursesFilePath);
						System.out.println("\n\tCourse deleted successfully");
					}
					else
					{
						String ID = prompt("Enter student ID", true, false);
						Student stduent = findStduentByID(ID);
						while(stduent == null)
						{
							System.out.println("\tNo such student could be found!");
							ID = prompt("Enter student ID", true, false);
							stduent = findStduentByID(ID);
						}
						ArrayList<GradeInput> courses = findCoursesTakenByStudent(ID);
						if(courses.isEmpty())
						{
							System.out.printf("\t%s has not taken any courses, you can't drop any course!", stduent.getName() + " " + stduent.getSurname());
							System.out.println();
						}
						else
						{
							System.out.printf("\t%s has taken the following courses:", stduent.getName() + " " + stduent.getSurname());
							System.out.println();
							System.out.println();
							System.out.printf("\t%-10s %s", "Course ID", "Title");
							System.out.println();
							System.out.println("\t---------- --------------------");
							for(int i = 0;i < courses.size();i++)
							{
								GradeInput g = courses.get(i);
								System.out.printf("\t%-10s %s", g.getCourse().getCourseID(), g.getCourse().getCourseName());
								System.out.println();
							}
							//---ask for course ID
							System.out.println();
							String CID = prompt("Enter the ID of the course you want to drop", true, false);
							while(!deleteTakenCourse(ID, CID))
							{
								System.out.println("\tThat course is not taken by" + stduent.getName() + " " + stduent.getSurname() + "!");
								CID = prompt("Enter the ID of the course you want to drop", true, false);
							}
							
							System.out.println();
							System.out.println("\tCourse droped successfully");
						}
					}
				}
				else
				{
					System.out.println("Thanks for using our program :)");
					exit = true;
				}
				//---some space for next menu display
				System.out.println();
				System.out.println();
			}
		}
		//---logic for GUI
		else
		{
			Main frame = new Main();
			frame.setVisible(true);
		}
		
	}
	
	//---a method to read a valid number between 1 and max
	private static byte getOption(int max, boolean indent)
	{
		Scanner s = new Scanner(System.in);
		boolean valid = false;
		byte choice = -1;
		while(!valid)
		{
			if(indent)
			{
				System.out.printf("\t");
			}
			System.out.printf(">>");
			try
			{
				choice = s.nextByte();
			}
			catch(Exception e)
			{
				if(indent)
				{
					System.out.printf("\t");
				}
				System.out.println("Enter a number!");
				//----to consume the un-read '\n'
				s.nextLine();
				//---skip to next iteration
				continue;
			}
			//----to consume the un-read '\n'
			s.nextLine();
			if(choice >= 1 && choice <= max)
			{
				valid = true;
			}
			else
			{
				if(indent)
				{
					System.out.printf("\t");
				}
				System.out.println("Enter a choice between 1-" + max + "!");
			}
		}
		return choice;
	}
	
	private static String listStudents(boolean indent)
	{
		StringBuilder result = new StringBuilder();
		try
		{
			Scanner s = new Scanner(new File(StudentsFilePath));
			if(indent)
			{
				result.append(String.format("\t%-10s %-15s %s", "Studen ID", "Name", "Surname"));
			}
			else
			{
				result.append(String.format("%-10s\t%-15s\t%s", "Studen ID", "Name", "Surname"));
			}
			result.append(System.getProperty("line.separator"));
			if(indent)
			{
				result.append(String.format("\t---------- --------------- ---------------"));
			}
			result.append(System.getProperty("line.separator"));
			
			String[] parts;
			while(s.hasNextLine())
			{
				parts = s.nextLine().split("-");
				if(indent)
				{
					result.append(String.format("\t%-10s %-15s %s", parts[0], parts[1], parts[2]));
				}
				else
				{
					result.append(String.format("%-10s\t%-15s\t%s", parts[0], parts[1], parts[2]));
				}
				result.append(System.getProperty("line.separator"));
			}
		}
		catch (Exception e)
		{
			if(indent)
			{
				result.append("\tError reading file!");	
			}
			else
			{
				result.append("Error reading file!");
			}
			result.append(System.getProperty("line.separator"));
		}
		return result.toString();
	}
	
	private static String listCourses(boolean indent)
	{
		StringBuilder result = new StringBuilder();
		try
		{
			Scanner s = new Scanner(new File(coursesFilePath));
			if(indent)
			{
				result.append(String.format("\t%-10s %s", "Course ID", "Title"));
			}
			else
			{
				result.append(String.format("%-10s\t%s", "Course ID", "Title"));
			}
			result.append(System.getProperty("line.separator"));
			if(indent)
			{
				result.append(String.format("\t---------- ------------------------------"));
			}
			result.append(System.getProperty("line.separator"));
			
			String[] parts;
			while(s.hasNextLine())
			{
				parts = s.nextLine().split("-");
				if(indent)
				{
					result.append(String.format("\t%-10s %s", parts[0], parts[1]));
				}
				else
				{
					result.append(String.format("%-10s\t%s", parts[0], parts[1]));
				}
				result.append(System.getProperty("line.separator"));
			}
		}
		catch (Exception e)
		{
			if(indent)
			{
				result.append("\tError reading file!");	
			}
			else
			{
				result.append("Error reading file!");
			}
			result.append(System.getProperty("line.separator"));
		}
		return result.toString();
	}
	
	//---deletes all the rows that have a matching ID in the specified column
	private static void deleteRow(String ID, int column, String filePath)
	{
		try
		{
			Scanner fs = new Scanner(new File(filePath));
			String[] parts;
			String row;
			StringBuilder info = new StringBuilder();
			while(fs.hasNextLine())
			{
				row = fs.nextLine();
				parts = row.split("-");
				if(!parts[column].equals(ID))
				{
					info.append(row);
					info.append(System.getProperty("line.separator"));
				}
			}
			fs.close();
			writeToFile(filePath, info.toString(), false);
		}
		catch (Exception e)
		{
			System.out.println("\tError reading file!");
		}
	}
	
	private static boolean deleteTakenCourse(String SID, String CID)
	{
		boolean found = false;
		try
		{
			Scanner fs = new Scanner(new File(takenCoursesFilePath));
			String[] parts;
			String row;
			StringBuilder info = new StringBuilder();
			while(fs.hasNextLine())
			{
				row = fs.nextLine();
				parts = row.split("-");
				if(parts[0].equals(SID))
				{
					if(!parts[1].equals(CID))
					{
						info.append(row);
						info.append(System.getProperty("line.separator"));
					}
					else
					{
						found = true;
					}
				}
				else
				{
					info.append(row);
					info.append(System.getProperty("line.separator"));
				}
			}
			fs.close();
			writeToFile(takenCoursesFilePath, info.toString(), false);
		}
		catch (Exception e)
		{
			System.out.println("\tError reading file!");
		}
		return found;
	}
	
	private static Student findStduentByID(String ID)
	{
		Student student = null;
		try
		{
			Scanner s = new Scanner(new File(StudentsFilePath));
			String[] parts;
			while(s.hasNextLine())
			{
				parts = s.nextLine().split("-");
				if(parts[0].equals(ID))
				{
					student = new Student(parts[0], parts[1], parts[2]);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("\tError reading file!");
		}
		return student;
	}
	
	private static ArrayList<GradeInput> findCoursesTakenByStudent(String ID)
	{
		ArrayList<GradeInput> coursesTaken = new ArrayList<GradeInput>(2);
		try
		{
			Scanner s = new Scanner(new File(takenCoursesFilePath));
			Course course;
			GradeInput grade;
			String[] parts;
			while(s.hasNextLine())
			{
				parts = s.nextLine().split("-");
				if(parts[0].equals(ID))
				{
					course = findCourseByID(parts[1]);
					if(course != null)//---if course is found
					{
						grade = new GradeInput(course, parts[2], parts[3]);
						coursesTaken.add(grade);
					}
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("\tError reading file!");
		}
		return coursesTaken;
	}
	
	private static Course findCourseByID(String ID)
	{
		Course course = null;
		try
		{
			Scanner s = new Scanner(new File(coursesFilePath));
			String[] parts;
			while(s.hasNextLine())
			{
				parts = s.nextLine().split("-");
				if(parts[0].equals(ID))
				{
					course = new Course(parts[0], parts[1]);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("\tError reading file!");
		}
		return course;
	}
	
	private static String format(String string)
	{
		String[] parts = string.toLowerCase().trim().split("[\\s]+");
		StringBuilder input = new StringBuilder();
		char initial;
		for(int i = 0;i < parts.length;i++)
		{
			initial = Character.toUpperCase(parts[i].charAt(0));
			input.append(initial + parts[i].substring(1) + " ");
		}
		return input.toString().trim();
	}
	
	private static String prompt(String msg, boolean indent, boolean format)
	{
		Scanner s = new Scanner(System.in);
		if(indent)
		{
			System.out.printf("\t");
		}
		System.out.printf("%s: ", msg);
		String string = s.nextLine().trim();
		while(string.isEmpty())
		{
			if(indent)
			{
				System.out.printf("\t");
			}
			System.out.printf("%s: ", msg);
			string = s.nextLine().trim();
		}
		if(format)
		{
			return format(string);
		}
		else
		{
			return string;
		}
	}
	
	private static void writeToFile(String filePath, String info, boolean append)
	{
		File f  = new File(filePath);
		try
		{
			FileWriter fw = new FileWriter(f, append);
			fw.write(info);
			if(append)
			{
				fw.write(System.getProperty("line.separator"));
			}
			fw.close();
		} 
		catch (Exception e)
		{
			System.out.println("\tError writing to file!");
		}
	}
}