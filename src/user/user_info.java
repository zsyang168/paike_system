package user;

import java.util.Arrays;
import java.util.List;

import common.oa_time;

public class user_info {
	
	public static String t_file_path="�οΰ��ű�.xlsx";
	public static String c_file_path="�γ���Ϣ.xlsx";
	
	public static List<String> course_list= (List<String>) Arrays.asList(
	new String[] { "����", "��ѧ", "Ӣ��", "����", "��ѧ", "����", "����", "��ʷ", "����", "����", 
	"΢��", "����", "�鷨", "����","��", "��", "Ӣ", "��", "��", "��", "��", "ʷ", "��", "��", "��", "��", "��" });
	
	public static int DAYS_OF_WEEK = 5;// һ���Ͽ�����
	public static int Courses_OF_DAY = 8;// һ���Ͽν���
	public static int TotalTime=DAYS_OF_WEEK*Courses_OF_DAY;
	public static int CourseS_OF_AM = 5;// һ���Ͽν���
	public static int CourseS_OF_PM = 3;// һ���Ͽν���
	public static oa_time Class_Meeting_Time = new oa_time(1, 8, 8);// ���ʱ�䣬��Ϊ-1ʱû�а��
	public static int ComputerRoomNum = 1;// �i����������ͬһʱ����ϻ��İ༶��
	public static int PE_Time[][];

	public static String morning_reading={"����","Ӣ��"};
	
	public user_info() {
		// TODO Auto-generated constructor stub
		
	}
	
	

}
