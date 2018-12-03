public class Course
{
	private String courseID;
	private String cname;
	
	public Course()
	{
		
	}
	
	public Course(String courseID, String CourseName)
	{
		this.setCourse(courseID, CourseName);
	}

	public String getCourseID()
	{
		return courseID;
	}

	public boolean setCourseID(String courseID)
	{
		if(courseID == null || courseID.isEmpty())
		{
			return false;
		}
		else
		{
			this.courseID = courseID;
			return true;
		}
	}

	public String getCourseName() {
		return cname;
	}

	public boolean setCourseName(String cname) {
		if(cname == null || cname.isEmpty())
		{
			return false;
		}
		else
		{
			this.cname = cname;
			return true;
		}
	}
	
	public boolean setCourse(String courseID, String CourseName)
	{
		return this.setCourseID(courseID) && this.setCourseName(CourseName);
	}
	
	public void PrintCourse()
	{
		System.out.println(this);
	}
	
	@Override
	public String toString()
	{
		return String.format("%s-%s", this.getCourseID(), this.getCourseName());
	}
}