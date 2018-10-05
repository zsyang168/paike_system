package user;

import java.util.Arrays;
import java.util.List;

import common.oa_time;

public class user_info {
	
	public static String file_path="�οΰ��ű�.xlsx";
	public static List<String> course_list= (List<String>) Arrays.asList(
	new String[] { "����", "��ѧ", "Ӣ��", "����", "��ѧ", "����", "����", "��ʷ", "����", "����", 
	"΢��", "����", "�鷨", "����","��", "��", "Ӣ", "��", "��", "��", "��", "ʷ", "��", "��", "��", "��", "��" });
	private String CourseName[] = { "��", "��", "Ӣ", "��", "��", "��", "��", "ʷ", "��", "��", "��", "��", "��" };// ÿ�ſμ��
	public static int CourseHour[] = { 5, 5, 5, 3, 3, 2, 3, 3, 3, 1, 1, 1, 2 };// ÿ�ſ�ÿ�ܿ�ʱ
	public static int DAYS_OF_WEEK = 5;// һ���Ͽ�����
	public static int CourseS_OF_DAY = 8;// һ���Ͽν���
	public static int TotalTime=DAYS_OF_WEEK*CourseS_OF_DAY;
	public static int CourseS_OF_AM = 5;// һ���Ͽν���
	public static int CourseS_OF_PM = 3;// һ���Ͽν���
	public static oa_time Class_Meeting_Time = new oa_time(1, 8, 8);// ���ʱ�䣬��Ϊ-1ʱû�а��
	public static int ComputerRoomNum = 1;// �i����������ͬһʱ����ϻ��İ༶��
	public static int PE_Time[][];
	
	public user_info() {
		// TODO Auto-generated constructor stub
	}
	
	// ��ȡ�γ̼��
	private String getCourseForShort(String name) {
		if ("����,��".contains(name))
			return CourseName[0];
		else if ("��ѧ,��".contains(name))
			return CourseName[1];
		else if ("Ӣ��,Ӣ".contains(name))
			return CourseName[2];
		else if ("����,��".contains(name))
			return CourseName[3];
		else if ("��ѧ,��".contains(name))
			return CourseName[4];
		else if ("����,��".contains(name))
			return CourseName[5];
		else if ("����,��".contains(name))
			return CourseName[6];
		else if ("��ʷ,ʷ".contains(name))
			return CourseName[7];
		else if ("����,��".contains(name))
			return CourseName[8];
		else if ("����,��,΢��".contains(name))
			return CourseName[9];
		else if ("����,��".contains(name))
			return CourseName[10];
		else if ("�鷨,��".contains(name))
			return CourseName[11];
		else if ("����,��".contains(name))
			return CourseName[12];
		else
			return null;
	}

	// ��ȡ������ʱ��
	public int[] getCourseUnAvailableTime(String name) {
		int time1[] = { 25, 26, 27 };
		int time2[] = { 1, 2, 3 };
		int time3[] = { 5, 6, 7 };
		int time4[] = { 17, 18, 19 };
		int time5[] = { 37, 38, 39 };
		int time6[] = { 29, 30, 31 };
		if ("����,��".contains(name))
			return time1;
		else if ("��ѧ,��".contains(name))
			return time1;
		else if ("Ӣ��,Ӣ".contains(name))
			return time2;
		else if ("����,��".contains(name))
			return time3;
		else if ("��ѧ,��".contains(name))
			return time3;
		else if ("����,��".contains(name))
			return time4;
		else if ("����,��".contains(name))
			return time5;
		else if ("��ʷ,ʷ".contains(name))
			return time6;
		else if ("����,��".contains(name))
			return time6;
		else
			return null;
	}

	// ��ȡ��ʱ
	public int getCourseHour(String name) {
		int hour = -1;
		String temp = getCourseForShort(name);
		if (temp != null) {
			for (int i = 0; i < CourseName.length; i++)
				if (temp.equals(CourseName[i])) {
					hour = CourseHour[i];
					break;
				}
		}
		return hour;
	}
	

}
