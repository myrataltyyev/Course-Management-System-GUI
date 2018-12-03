public class Student
{
	private String studentNo;
	private String name;
	private String surname;
	private GradeInput[] grades = new GradeInput[5];
	private byte lastIndexOfGrade = 0;//to keep track of GradeInput's in grades;
	
	public Student()
	{
		
	}
	
	public Student(String studentNo, String name, String surname)
	{
		this.setStudentID(studentNo, name, surname);
	}

	public boolean setStudentID(String no, String name, String surname)
	{
		return this.setStudentNo(no) && this.setName(name) && this.setSurname(surname);
	}
	
	public String getStudentNo() {
		return studentNo;
	}

	public boolean setStudentNo(String studentNo) {
		if(studentNo == null || studentNo.isEmpty())
		{
			return false;
		}
		else
		{
			this.studentNo = studentNo;
			return true;
		}
	}

	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		if(name == null || name.isEmpty())
		{
			return false;
		}
		else
		{
			this.name = name;
			return true;
		}
	}

	public String getSurname() {
		return surname;
	}

	public boolean setSurname(String surname) {
		if(surname == null || surname.isEmpty())
		{
			return false;
		}
		else
		{
			this.surname = surname;
			return true;
		}
	}

	public GradeInput[] getGrades() {
		return grades;
	}

	public boolean AddGrade(GradeInput grade)
	{
		if(grade == null)
		{
			return false;
		}
		else if(this.lastIndexOfGrade < 5)
		{
			this.grades[this.lastIndexOfGrade] = grade;
			this.lastIndexOfGrade++;
			return true;
		}
		//---no room for more grades
		else
		{
			return false;
		}
	}
	
	public boolean setGrades(GradeInput[] grades) {
		if(grades == null)
		{
			return false;
		}
		else
		{
			this.grades = grades;
			return true;
		}
	}
	
	public void PrintStudent()
	{
		System.out.println(this);
	}
	
	public String toString()
	{
		return String.format("%s-%s-%s", this.studentNo, this.name, this.surname);
	}
}