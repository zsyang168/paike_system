package data;

import common.oa_time;

public class course_info {
	private int course_id;
	private String course_name;
	private int course_periods;
	private boolean is_major;
	private oa_time m_time;

	public course_info(int id, String name)
	{
		course_id = id;
		course_name = name;
		course_periods = 0;
		is_major = false;
		m_time = null;
	}
	
	public int get_id()
	{
		return course_id;
	}
	
	public String get_name()
	{
		return course_name;
	}
	
	public int get_periods()
	{
		return course_periods;
	}
	
	public boolean is_major()
	{
		return is_major;
	}

	public oa_time get_meet_time()
	{
		return m_time;
	}

	public void set_period(int periods)
	{
		course_periods = periods;
	}

	public void set_major(boolean flag)
	{
		is_major = flag;
	}

	public void set_meet_time(oa_time time)
	{
		m_time = time;
	}
}
