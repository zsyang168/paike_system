package main;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import common.math_method;
import common.user_data;

import entity.Class;
import entity.Course;
import entity.Teacher;
import entity.Teaching;
import excel.excel_func;

public class acs_data {

	protected int teacher_num;// 教师数
	protected int class_num;// 班级数
	protected int course_num;// 课程数

	protected Class[] class_list;
	protected Course[] course_list;
	protected Teacher[] teacher_list;

	protected List<Teaching> teaching_list;// 教师类数据
	protected List<Teaching> pri_list;// 优先教师类数据
	protected List<Teaching> nor_list;// 普通教师类数据

	protected int morning_reading[];// 早读信息
	protected int days;// 天数
	protected int am_periods;// 上午课时
	protected int pm_periods;// 下午课时
	protected int day_periods;// 一天课时
	protected int total_periods;// 总课时
	protected int computer_room_num;// 机房数

	protected int timetable[][]; // 课程表
	protected int computer_room_used[];// 机房使用计数
	protected int class_course_arrangment[][][];//班级每天课程安排记录
	protected int teacher_arangment[][];//教师每天教学安排记录

	protected int max_per[]; //课程每天每班最大安排量
	protected int min_per[]; //课程每天每班最小安排量
	protected int total_steps; //总安排量
	protected int steps;//目前安排数

	public acs_data() {
		// TODO Auto-generated constructor stub
		new user_data();
		data_init();
	}

	protected void data_init() {
		excel_func test = new excel_func();
		Object[][] t_data = test.get_excel_data_by_name(user_data.t_file_path, "Sheet1");
		Object[][] c_data = test.get_excel_data_by_name(user_data.c_file_path, "Sheet1");

		//获取表格有效数据区域
		act_range t_range = get_range(t_data);
		act_range c_range = get_range(c_data);

		//初始化班级数据
		class_init(t_range, t_data);

		//初始化课程信息
		course_init(c_range, c_data);

		//初始化教师信息
		teacher_init(t_range, t_data);

		// 获取用户维护的用户数据
		days = user_data.DAYS_OF_WEEK;
		morning_reading = get_morning_reading_info();
		am_periods = user_data.PERIODS_OF_AM;
		pm_periods = user_data.PERIODS_OF_PM;
		day_periods = user_data.PERIODS_OF_DAY;
		total_periods = user_data.TOTAL_PERIODS;
		computer_room_num = user_data.NUM_OF_COMPUTERROOM;

		// 初始化课表相关数据
		timetable = new int[class_num + 1][total_periods + 1];
		computer_room_used = new int[total_periods + 1];
		class_course_arrangment = new int[class_num + 1][course_num + 1][days + 1];
		teacher_arangment = new int[teacher_num + 1][days + 1];
		per_limit_init();
		//进度相关
		total_steps = get_total_steps();
		steps = 0;
		//教学数据分类
		teaching_classify_init();

	}

	//初始化班级信息
	private void class_init(act_range range, Object[][] data) {
		String class_name = null;
		String class_type = null;
		class_num = range.y2 - range.y1;
		class_list = new Class[class_num + 1];

		for (int i = 1; i <= class_num; i++) {
			class_name = String.valueOf(data[range.y1 + i][0]);
			class_type = String.valueOf(data[range.y1 + i][2]);
			class_list[i] = new Class(i, class_name, class_type);
		}
	}
	
	//初始化课程信息
	private void course_init(act_range range, Object[][] data) {
		course_num = range.y2 - range.y1;
		course_list = new Course[course_num + 1];

		for (int i = 1; i <= course_num; i++) {
			course_list[i] = new Course(i);
			if (course_list[i].init(data[i]) != 0)
				System.out.println("course " + i + " init failed!");

		}
	}
	
	//初始化教师信息
	private void teacher_init(act_range range, Object[][] data) {
		LinkedList<String> _master_list = new LinkedList<String>();
		LinkedList<String> _name_list = new LinkedList<String>();
		LinkedList<Integer> _course_list = new LinkedList<Integer>();
		String teacher_name = null;
		int index = -1;
		for (int i = 1; i <= class_num; i++) {
			_master_list.add(String.valueOf(data[range.y1 + i][1]));
			for (int j = 4; j < range.x2; j++) {
				teacher_name = String.valueOf(data[range.y1 + i][j]);
				if (!_name_list.contains(teacher_name)) {
					_name_list.add(teacher_name);
					_course_list.add(j);
				}

			}
		}

		teacher_num = _name_list.size();
		teacher_list = new Teacher[teacher_num + 1];
		for (int i = 1; i <= teacher_num; i++) {
			teacher_name = _name_list.get(i - 1);

			teacher_list[i] = new Teacher(i, teacher_name);

			if (_master_list.contains(teacher_name))
			{
				index = _master_list.indexOf(teacher_name);
				if(index != -1)
					teacher_list[i].set_head(index);
				else
					System.out.println("get head teacher info failed!");
			}

		}

		teaching_init(range, _course_list, data);
	}

	//初始化教学数据
	private void teaching_init(act_range range, LinkedList<Integer> _course_list, Object[][] data) {

		LinkedList<Integer> _class_list = new LinkedList<Integer>();
		teaching_list = new LinkedList<Teaching>();
		int t_list[] = new int[user_data.TOTAL_PERIODS + 1];
		int length = -1;
		int _course_id = -1;
		String t_name = null;
		Course _course = null;

		for (int i = 1; i <= teacher_num; i++) {
			_class_list.clear();
			_course_id = _course_list.get(i - 1);

			for (int j = 1; j <= class_num; j++) {
				t_name = String.valueOf(data[1 + j][_course_id]);
				if (t_name.equals(teacher_list[i].get_name())) {
					_class_list.add(j);
				}
			}

			length = _class_list.size();
			int c_list[] = new int[length + 1];
			for (int j = 1; j <= length; j++)
				c_list[j] = _class_list.get(j - 1);

			for (int j = 0; j < t_list.length; j++)
				t_list[j] = 1;

			for (int j = 1; j <= course_num; j++) {
				if (data[1][_course_id].equals(course_list[j].get_name())) {
					_course = course_list[j];
					break;
				}
			}

			if (_course.get_prepare_time() != null) {

				int m_time[] = _course.get_prepare_time();
				for (int j = 1; j < m_time.length; j++)
					t_list[m_time[j]] = 0;
			}

			int b_time[] = teacher_list[i].get_bt();
			if (b_time != null) {
				for (int j = 1; j < b_time.length; j++)
					t_list[b_time[j]] = 0;
			}

			t_list[16] = 0;

			Teaching temp = new Teaching(teacher_list[i], _course, c_list, t_list);
			teaching_list.add(temp);

		}

	}
	
	//教学数据分类
	private void teaching_classify_init()
	{
		pri_list = new LinkedList<Teaching>();
		nor_list = new LinkedList<Teaching>();
		
		int course_num = -1;
		boolean flag = false;
		for (Teaching tl :teaching_list)
		{
			flag = false;
			course_num = tl.get_course().get_no();
			for(int i = 1; i < days; i ++)
			{
				if (course_num == morning_reading[i])
				{
					pri_list.add(tl);
					flag= true;
					break;
				}
			}
			
			if(!flag)
				nor_list.add(tl);
		}
	}
	
	//初始化每天课程安排课时限制
	private void per_limit_init()
	{
		max_per = new int[course_num + 1];
		min_per = new int[course_num + 1];
		int periods = 0;

		for(int i=1;i <= course_num; i ++)
		{
			periods = course_list[i].get_periods();

			if(periods >= days)
			{
				min_per[i] = periods / days;
				max_per[i] = periods / days + 1;
			}
			else
			{
				min_per[i] = 0;
				max_per[i] = 1;
			}
		}
	}
	
	//获取早读信息，将早读转化为对应number号
	private int[] get_morning_reading_info()
	{
		int res[] = new int[days + 1];
		for(int i = 1;i <= days; i ++){
			res[i] = get_course_number(user_data.morning_reading[i-1]);
		}
		return res;

	}
	
	//由获取课程名获取课程number
	private int get_course_number(String name)
	{
		for(int i= 1;i <= course_num; i ++)
			if(course_list[i].get_name().equals(name))
				return i;
		return 0;
	}
	
	//获取总步数，即要安排的总的课时数
	private int get_total_steps()
	{
		int count = 0;
		for (int i = 1; i <= course_num; i++)
			count += course_list[i].get_periods();

		count = count * class_num;
		return count;
	}

	/**
	 更新教学安排优先级，按优先级从高到低排序*/
	@SuppressWarnings("unchecked")
	protected void priority_update(List<Teaching> teaching_list)
	{
		double priority = 0;
		for (Teaching t:teaching_list)
		{
			//取优先级中最高的
			priority = math_method.max(get_priorities(t));
			t.set_priority(priority);
		}
		Collections.sort(teaching_list);
	}
	
	/**
	 计算教师所授班级及教师的总优先级*/
	private double []  get_priorities(Teaching teaching)
	{
		int	class_list[] = teaching.get_class_list();
		int	time_list[] = teaching.get_time_list();
		int	cl_num = class_list.length - 1;
		int	class_needed_periods[] = new int[cl_num + 1];
		int	class_available_periods[] = new int[cl_num + 1];
		int	class_arrangment_count[] = new int[cl_num + 1];
		int	period_arrangment[] = new int[total_periods + 1];
		int t_id = teaching.get_teacher().get_id();
		int periods = teaching.get_course().get_periods();

		double priorities[] = new double[cl_num + 2];

		for(int i= 1;i <= cl_num; i++)
		{

			for ( int j = 1;j <= total_periods; j ++)
			{
				if (timetable[i][j] == t_id)
				{
					class_arrangment_count[i] ++;
					period_arrangment[j] = 1;
				}
			}
		}

		boolean is_computer_course = false;
		if ( teaching.get_course().get_short_name().equals("电"))
			is_computer_course = true;

		for ( int i = 1; i <= cl_num; i ++)
		{
			class_available_periods[i] = get_class_available_periods(i, period_arrangment, time_list, is_computer_course);
			class_needed_periods[i] = periods - class_arrangment_count[i];

			if (class_available_periods[i] == 0)
			{
				priorities[i] = class_available_periods[i];
				continue;
			}

			priorities[i] = (double)(class_needed_periods[i])/(double)(class_available_periods[i]);

		}

		int teacher_available_periods = get_teacher_available_periods(period_arrangment, time_list, is_computer_course);
		int teacher_needed_periods = 0;
		for ( int i = 1; i <= cl_num; i ++)
			teacher_needed_periods += class_arrangment_count[i];
		
		teacher_needed_periods = periods * cl_num - teacher_needed_periods;
	
		if(teacher_available_periods == 0)
			priorities[cl_num + 1] = teacher_needed_periods;
		else
			priorities[cl_num + 1] = (double)(teacher_needed_periods)/(double)(teacher_available_periods);
		
	    return priorities;
	}
	
	/**
	 获取班级一周内可用课时*/
	private int get_class_available_periods(int c_no, int period_arrangment[], int time_list[], boolean is_computer_course)
	{
		int count = 0;
		boolean effect_flag;
		for ( int j = 1;j <= total_periods; j ++)
		{
			if (period_arrangment[j] == 0 && time_list[j] == 1 && timetable[c_no][j] ==0)
				effect_flag = true;
			else
				effect_flag = false;

			if (is_computer_course){
					if ( effect_flag && computer_room_used[j] <= computer_room_num)
							count ++;
			}else{
				if (effect_flag)
					count ++;
			}

		}
		return count;
	}
  
	/**
	 获取教师一周内可用课时*/
	private int get_teacher_available_periods(int period_arrangment[], int time_list[], boolean is_computer_course)
	{
		int count = 0;
		boolean effect_flag;
		for ( int j = 1;j <= total_periods; j ++)
		{
			if (period_arrangment[j] == 0 && time_list[j] == 1)
				effect_flag = true;
			else
				effect_flag = false;

			if (is_computer_course){
					if ( effect_flag && computer_room_used[j] <= computer_room_num)
							count ++;
			}else{
				if (effect_flag)
					count ++;
			}

		}
		return count;
	}
	
	private act_range get_range(Object data[][]) {
		int row_num = data.length;
		int[] line_num = new int[row_num];

		for (int i = 0; i < row_num; i++)
			for (int j = 0; j < data[i].length; j++)
				if (data[i][j] != null)
					line_num[i]++;

		int max_column = 0;
		for (int i = 0; i < row_num; i++)
			if (line_num[i] > max_column)
				max_column = line_num[i];

		int start_line = -1;
		int end_line = -1;

		for (int i = 0; i < row_num; i++) {
			if (line_num[i] == max_column && start_line == -1) {
				start_line = i;
				end_line = i;
				continue;
			}

			if (line_num[i] == max_column && end_line != -1)
				end_line = i;
		}

		act_range result = new act_range();
		result.y1 = start_line;
		result.y2 = end_line;
		result.x1 = 0;
		result.x2 = max_column;

		return result;
	}

	public class act_range {
		public int x1;
		public int x2;
		public int y1;
		public int y2;

		public act_range() {

		}
	}
}
