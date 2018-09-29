package data;

public class teacher_info {
	private int teacher_id;
	private String teacher_name;
	private int course_id;
	private boolean is_master;
	private int b_time[];

	public teacher_info(int id, String name)
	{
		teacher_id = id;
		teacher_name = name;
		course_id = -1;
		is_master = false;
		b_time = null;
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

	public boolean is_master()
	{
		return is_master;
	}

	public int[] get_busy_time()
	{
		return b_time;
	}

	public void set_course_id(int id)
	{
		course_id = id;
	}

	public void set_master(boolean flag)
	{
		is_master = flag;
	}

	public void set_busy_time(int time[])
	{
		b_time = time;
	}
}
