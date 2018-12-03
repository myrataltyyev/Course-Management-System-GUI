public class GradeInput
{
	private Course course;
	private String year;
	private String grade;
	
	public GradeInput()
	{
		
	}
	
	public GradeInput(Course course, String year, String grade)
	{
		this.setGradeInput(course, year, grade);
	}

	public Course getCourse() {
		return course;
	}

	public boolean setCourse(Course course) {
		if(course == null)
		{
			return false;
		}
		else
		{
			this.course = course;
			return true;
		}
	}

	public String getYear() {
		return year;
	}

	public boolean setYear(String year) {
		if(year == null || year.isEmpty())
		{
			return false;
		}
		else
		{
			this.year = year;
			return true;
		}
	}

	public String getGrade() {
		return grade;
	}

	public boolean setGrade(String grade) {
		if(grade == null || grade.isEmpty() || grade.length() > 2)//check if it is more than two letters
		{
			return false;
		}
		else
		{
			this.grade = grade;
			return true;
		}
	}
	
	public boolean setGradeInput(Course course, String year, String grade)
	{
		return this.setCourse(course) && this.setYear(year) && this.setGrade(grade);
	}
	
	public void PrintGrade()
	{
		System.out.println(this);
	}
	
	public String toString()
	{
		return String.format("%s-%s-%s", this.course, this.year, this.grade);
	}
}