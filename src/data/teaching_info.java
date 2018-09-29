package data;

public class teaching_info{
	public int teacher_id;
	public int class_list[];
	public int time_list[];
	public double priority;

	public teaching_info(int t_id, int c_list[], int t_list[])
	{
		teacher_id = t_id;
		class_list = c_list;
		time_list = t_list;
		priority = 0;
	}

}

