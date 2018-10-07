package entity;

@SuppressWarnings("rawtypes")
public class Teaching implements Comparable{
	private Teacher teacher;
	private Course course;
	private int class_list[];
	private int time_list[];
	private double priority;

	public Teaching(Teacher t, Course c, int c_list[], int t_list[])
	{
		teacher = t;
		course = c;
		class_list = c_list;
		time_list = t_list;
		priority = 0;
	}
	
	public Teacher get_teacher()
	{
		return teacher;
	}
	
	public Course get_course()
	{
		return course;
	}
	
	public int[] get_class_list()
	{
		return class_list;
	}
	
	public int[] get_time_list()
	{
		return time_list;
	}
	
	public double get_priority()
	{
		return priority;
	}
	
	public void set_priority(double pri)
	{
		priority = pri;
	}
	
	public int compareTo(Object arg0) {
		// TODO 自动生成的方法存根
		Teaching o = (Teaching) arg0;
		if (this.priority > o.priority) {
			return -1;
		} else if (o.priority == this.priority) {
			return 0;
		} else {
			return 1;
		}
	}

}

