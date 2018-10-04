package user;

public class user_info {
	
	public static String file_path="�οΰ��ű�.xlsx";
	private String LessonName[] = { "��", "��", "Ӣ", "��", "��", "��", "��", "ʷ", "��", "��", "��", "��", "��" };// ÿ�ſμ��
	public static int LessonHour[] = { 5, 5, 5, 3, 3, 2, 3, 3, 3, 1, 1, 1, 2 };// ÿ�ſ�ÿ�ܿ�ʱ
	public static int DAYS_OF_WEEK = 5;// һ���Ͽ�����
	public static int LESSONS_OF_DAY = 8;// һ���Ͽν���
	public static int TotalTime=DAYS_OF_WEEK*LESSONS_OF_DAY;
	public static int LESSONS_OF_AM = 5;// һ���Ͽν���
	public static int LESSONS_OF_PM = 3;// һ���Ͽν���
	public static int Class_Meeting_Time = 15;// ���ʱ�䣬��Ϊ-1ʱû�а��
	public static int ComputerRoomNum = 1;// �i����������ͬһʱ����ϻ��İ༶��
	public static int PE_Time[][];
	
	public user_info() {
		// TODO Auto-generated constructor stub
	}
	
	// ��ȡ�γ̼��
	private String getLessonForShort(String name) {
		if ("����,��".contains(name))
			return LessonName[0];
		else if ("��ѧ,��".contains(name))
			return LessonName[1];
		else if ("Ӣ��,Ӣ".contains(name))
			return LessonName[2];
		else if ("����,��".contains(name))
			return LessonName[3];
		else if ("��ѧ,��".contains(name))
			return LessonName[4];
		else if ("����,��".contains(name))
			return LessonName[5];
		else if ("����,��".contains(name))
			return LessonName[6];
		else if ("��ʷ,ʷ".contains(name))
			return LessonName[7];
		else if ("����,��".contains(name))
			return LessonName[8];
		else if ("����,��,΢��".contains(name))
			return LessonName[9];
		else if ("����,��".contains(name))
			return LessonName[10];
		else if ("�鷨,��".contains(name))
			return LessonName[11];
		else if ("����,��".contains(name))
			return LessonName[12];
		else
			return null;
	}

	// ��ȡ������ʱ��
	public int[] getLessonUnAvailableTime(String name) {
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
	public int getLessonHour(String name) {
		int hour = -1;
		String temp = getLessonForShort(name);
		if (temp != null) {
			for (int i = 0; i < LessonName.length; i++)
				if (temp.equals(LessonName[i])) {
					hour = LessonHour[i];
					break;
				}
		}
		return hour;
	}
	

}
