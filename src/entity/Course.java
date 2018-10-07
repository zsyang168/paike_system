package entity;

public class Course {
	private int course_id;
	private String c_grade;
	private String course_name;
	private String short_name;
	private int course_periods;
	private int prepare_time[];

	public Course()
	{
		course_id = 0;
		c_grade = null;
		course_name = null;
		short_name = null;
		course_periods = 0;
		prepare_time = null;
	}
	
	public int get_id()
	{
		return course_id;
	}
	
	public String get_grade()
	{
		return c_grade;
	}
	
	public String get_name()
	{
		return course_name;
	}
	
	public String get_short_name()
	{
		return short_name;
	}
	
	public int get_periods()
	{
		return course_periods;
	}
	
	public int[] get_prepare_time()
	{
		return prepare_time;
	}
	
	public int init(Object data[])
	{
		int length=data.length;
		if ( length != 6)
		{
			System.out.println("data form error!");
			return -1;
		}	
		
		try{
			course_id = (int) data[0];
			c_grade = String.valueOf(data[1]);
			course_name = String.valueOf(data[2]);
			short_name = String.valueOf(data[3]);
			course_periods = (int) data[4];
			if (data[5] != null)
				prepare_time = get_time_list(String.valueOf(data[5]));
			
		}catch(Exception e)
		{
			System.out.println("data init failed:"+e);
			return -1;
		}
	
		return 0;
	}
	
	private int[] get_time_list(String str)
	{
		String out[] = str.split(":|-");
		if (out.length != 3)
			return null;
		
		int day = Integer.parseInt(out[0]) - 1;
		int st = Integer.parseInt(out[1]) - 1;
		int et = Integer.parseInt(out[2]) - 1;
		int length = et - st +1;
		int result[] = new int[length];

		for(int i=0;i<length;i++)
			result[i] = day*8 + et + i;

		return result;
	}
}
