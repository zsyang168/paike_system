package main;

import java.util.List;

import common.user_data;
import entity.Teaching;

public class acs_run {
	
	private acs_data run_data;
	private int teacher_num;// 教师数
	private int class_num;// 班级数
	private List<Teaching> teaching_list;// 教师类数据
	
	private String morning_reading[];//早读信息
	private int days;//天数
	private int am_periods;//上午课时
	private int pm_periods;//下午课时
	private int day_periods;//一天课时
	private int total_periods;//总课时
	private int computer_room_num;//机房数
	
	private int timetable[][];  //课程表
	private int computer_room_used[];//机房使用计数
	
	public acs_run() {
		// TODO Auto-generated constructor stub
		new user_data();
		data_init();
		priority_update();
	}
	
	private void data_init()
	{
		run_data =new acs_data();
		teacher_num = run_data.get_teacher_num();
		class_num = run_data.get_class_num();
		teaching_list = run_data.get_teaching_list();
		
		morning_reading = user_data.morning_reading;
		days = user_data.DAYS_OF_WEEK;
		am_periods = user_data.PERIODS_OF_AM;
		pm_periods = user_data.PERIODS_OF_PM;
		day_periods = user_data.PERIODS_OF_DAY;
		total_periods = user_data.TOTAL_PERIODS;
		computer_room_num = user_data.NUM_OF_COMPUTERROOM;
		
		timetable = new int[class_num + 1][total_periods + 1];
		computer_room_used = new int[total_periods + 1];
	}
	
	private void priority_update()
	{
		for (Teaching t:teaching_list)
		{
			
		}
	}

}
