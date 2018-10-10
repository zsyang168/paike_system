package main;

import java.util.List;

import common.user_data;
import entity.Teaching;

public class acs_run {
	
	private acs_data run_data;
	private int teacher_num;// ��ʦ��
	private int class_num;// �༶��
	private List<Teaching> teaching_list;// ��ʦ������
	
	private String morning_reading[];//�����Ϣ
	private int days;//����
	private int am_periods;//�����ʱ
	private int pm_periods;//�����ʱ
	private int day_periods;//һ���ʱ
	private int total_periods;//�ܿ�ʱ
	private int computer_room_num;//������
	
	private int timetable[][];  //�γ̱�
	private int computer_room_used[];//����ʹ�ü���
	
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
