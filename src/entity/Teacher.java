package entity;

public class Teacher {
	private int teacher_id;
	private String teacher_name;
	private int is_head;
	private int b_time[];

	public Teacher(int id, String name)
	{
		teacher_id = id;
		teacher_name = name;
		is_head = 0;
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

	public int is_head()
	{
		return is_head;
	}

	public int[] get_bt()
	{
		return b_time;
	}

	public void set_head(int num)
	{
		is_head = num;
	}

	public void set_bt(int time[])
	{
		b_time = time;
	}
}
