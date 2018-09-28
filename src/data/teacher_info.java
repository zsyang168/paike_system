package data;

public class teacher_info {
	private int teacher_id;
	private String teacher_name;
	private int course_id;
	public teacher_info(int id, String name, int c_id)
	{
		teacher_id = id;
		teacher_name = name;
		course_id = c_id;
	}
	
	public int get_id()
	{
		return teacher_id;
	}
	
	public String get_name()
	{
		return teacher_name;
	}
	
	public int get_course_id()
	{
		return course_id;
	}
}
