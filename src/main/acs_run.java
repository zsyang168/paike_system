package main;

import java.util.Collections;
import java.util.List;

import common.math_method;
import common.print;
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
		System.out.println(class_num + " " + teacher_num);
		print.show(teaching_list,-1);
	}
	
	/**
	 ��ʼ��������������*/
	private void data_init()
	{
		//��ʼ�������ά��������
		run_data =new acs_data();
		teacher_num = run_data.get_teacher_num();
		class_num = run_data.get_class_num();
		teaching_list = run_data.get_teaching_list();
		//��ȡ�û�ά�����û�����
		morning_reading = user_data.morning_reading;
		days = user_data.DAYS_OF_WEEK;
		am_periods = user_data.PERIODS_OF_AM;
		pm_periods = user_data.PERIODS_OF_PM;
		day_periods = user_data.PERIODS_OF_DAY;
		total_periods = user_data.TOTAL_PERIODS;
		computer_room_num = user_data.NUM_OF_COMPUTERROOM;
		//��ʼ���α��������
		timetable = new int[class_num + 1][total_periods + 1];
		computer_room_used = new int[total_periods + 1];
	}
	
	/**
	 ���½�ѧ�������ȼ��������ȼ��Ӹߵ�������*/
	@SuppressWarnings("unchecked")
	private void priority_update()
	{
		double priority = 0;
		for (Teaching t:teaching_list)
		{
			//ȡ���ȼ�����ߵ�
			priority = math_method.max(get_priorities(t));
			t.set_priority(priority);
		}
		Collections.sort(teaching_list);
	}
	
	/**
	 �����ʦ���ڰ༶����ʦ�������ȼ�*/
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
		if ( teaching.get_course().get_short_name().equals("��"))
			is_computer_course = true;

		for ( int i = 1; i <= cl_num; i ++)
		{
			class_available_periods[i] = get_class_available_periods(i, period_arrangment, time_list, is_computer_course);
			class_needed_periods[i] = periods - class_arrangment_count[i];
			
			if (class_available_periods[i] == 0 && class_needed_periods[i] !=0)
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
		
		priorities[cl_num + 1] = (double)(teacher_needed_periods)/(double)(teacher_available_periods);
		
	    return priorities;
	}
	
	/**
	 ��ȡ�༶һ���ڿ��ÿ�ʱ*/
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
	 ��ȡ��ʦһ���ڿ��ÿ�ʱ*/
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
}
