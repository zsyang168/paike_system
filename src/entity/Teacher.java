package entity;

public class Teacher {
	private int teacher_id;
	private String teacher_name;
	private boolean is_master;
	private int b_time[];

	public Teacher(int id, String name)
	{
		teacher_id = id;
		teacher_name = name;
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

	public boolean is_master()
	{
		return is_master;
	}

	public int[] get_busy_time()
	{
		return b_time;
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
