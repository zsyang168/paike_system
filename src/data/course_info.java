package data;

public class course_info {
	private int course_id;
	private String course_name;
	private int course_period;
	public course_info(int id, String name)
	{
		course_id = id;
		course_name = name;
	}
	
	public int get_id()
	{
		return course_id;
	}
	
	public String get_name()
	{
		return course_name;
	}
	
	public int get_period()
	{
		return course_period;
	}
	
	public void set_period(int period)
	{
		course_period = period;
	}
}
