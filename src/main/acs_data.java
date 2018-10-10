package main;

import java.util.LinkedList;
import java.util.List;

import common.user_data;

import entity.Class;
import entity.Course;
import entity.Teacher;
import entity.Teaching;
import excel.excel_func;


public class acs_data {

	private int teacher_num;// 教师数
	private int class_num;// 班级数
	private int course_num;// 课程数

	private Class[] class_list;
	private Course[] course_list;
	private Teacher[] teacher_list;

	private List<Teaching> teaching_list;// 教师类数据

	public acs_data() {
		// TODO Auto-generated constructor stub
		data_init();
	}

	private void data_init() {
		excel_func test = new excel_func();
		Object[][] t_data = test.get_excel_data_by_name(user_data.t_file_path, "Sheet1");
		Object[][] c_data = test.get_excel_data_by_name(user_data.c_file_path, "Sheet1");

		act_range t_range = get_range(t_data);
		act_range c_range = get_range(c_data);

		class_init(t_range, t_data);
		
		course_init(c_range, c_data);
	
		teacher_init(t_range, t_data);

	}

	private void class_init(act_range range, Object[][] data) {
		String class_name = null;
		String class_type = null;
		class_num = range.y2 - range.y1;
		class_list = new Class[class_num+1];

		for (int i = 1; i <=class_num; i++) {
			class_name = String.valueOf(data[range.y1 + i][0]);
			class_type = String.valueOf(data[range.y1 + i][2]);
			class_list[i] = new Class(i, class_name, class_type);
		}
	}

	private void course_init(act_range range, Object[][] data) {
		course_num = range.y2 - range.y1;
		course_list = new Course[course_num+1];

		for (int i = 1; i <= course_num; i++) {
			course_list[i] = new Course();
			if (course_list[i].init(data[i]) != 0)
				System.out.println("course "+ i +" init failed!");

		}
	}

	private void teacher_init(act_range range, Object[][] data) {
		LinkedList<String> master_list = new LinkedList<String>();
		LinkedList<String> _name_list = new LinkedList<String>();
		LinkedList<Integer> _course_list = new LinkedList<Integer>();
		String teacher_name = null;

		for (int i = 1; i <= class_num; i++) {
			master_list.add(String.valueOf(data[range.y1 + i][1]));
			for (int j = 4; j < range.x2; j++) {
				teacher_name = String.valueOf(data[range.y1 + i][j]);
				if (!_name_list.contains(teacher_name)) {
					_name_list.add(teacher_name);
					_course_list.add(j);
				}

			}
		}

		teacher_num = _name_list.size();
		teacher_list = new Teacher[teacher_num+1];
		for (int i = 1; i <=teacher_num; i++) {
			teacher_name = _name_list.get(i-1);
	
			teacher_list[i] = new Teacher(i, teacher_name);

			if (master_list.contains(teacher_name))
				teacher_list[i].set_master(true);

		}

		teaching_init(range, _course_list, data);
	}

	private void teaching_init(act_range range, LinkedList<Integer> _course_list, Object[][] data) {

		LinkedList<Integer> _class_list = new LinkedList<Integer>();
		teaching_list = new LinkedList<Teaching>();
		int t_list[] = new int[user_data.TOTAL_PERIODS+1];
		int length = -1;
		int _course_id = -1;
		String t_name = null;
		Course _course = null;

		for (int i = 1; i <= teacher_num; i++) {
			_class_list.clear();
			_course_id = _course_list.get(i-1);

			for (int j = 1; j <= class_num; j++) {
				t_name =String.valueOf(data[ 1 + j ][_course_id]);
				if (t_name.equals(teacher_list[i].get_name())) {
					_class_list.add(j);
				}
			}

			length = _class_list.size();
			int c_list[] = new int[length+1];
			for (int j = 1; j <= length; j++)
				c_list[j] = _class_list.get(j-1);

			for (int j = 0; j < t_list.length; j++)
				t_list[j] = 1;
			
			for (int j = 1; j <= course_num; j++){
				if(data[1][_course_id].equals(course_list[j].get_name())){
						_course = course_list[j];
						break;
				}
			}
			
			if(_course.get_prepare_time() != null)
			{
				
				int m_time[] = _course.get_prepare_time();
				for (int j = 1; j < m_time.length; j++)
					t_list[m_time[j]] = 0;
			}
		

			int b_time[] = teacher_list[i].get_busy_time();
			if (b_time != null) {
				for (int j = 1; j < b_time.length; j++)
					t_list[b_time[j]] = 0;
			}

			t_list[16] = 0;

			Teaching temp = new Teaching(teacher_list[i], _course, c_list, t_list);
			teaching_list.add(temp);

		}

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

	public int get_teacher_num()
	{
		return teacher_num;
	}
	
	public int get_class_num()
	{
		return class_num;
	}
	
	public int get_course_num()
	{
		return course_num;
	}

	public Class[] get_class_list()
	{
		return class_list;
	}
	
	public Course[] get_course_list()
	{
		return course_list;
	}
	
	public Teacher[] get_teacher_list()
	{
		return teacher_list;
	}

	public List<Teaching> get_teaching_list()
	{
		return teaching_list;
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
