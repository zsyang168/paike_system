package user;

public class user_info {
	
	public static String file_path="任课安排表.xlsx";
	private String LessonName[] = { "语", "数", "英", "物", "化", "生", "政", "史", "地", "电", "音", "书", "体" };// 每门课简称
	public static int LessonHour[] = { 5, 5, 5, 3, 3, 2, 3, 3, 3, 1, 1, 1, 2 };// 每门课每周课时
	public static int DAYS_OF_WEEK = 5;// 一周上课天数
	public static int LESSONS_OF_DAY = 8;// 一天上课节数
	public static int TotalTime=DAYS_OF_WEEK*LESSONS_OF_DAY;
	public static int LESSONS_OF_AM = 5;// 一天上课节数
	public static int LESSONS_OF_PM = 3;// 一天上课节数
	public static int Class_Meeting_Time = 15;// 班会时间，设为-1时没有班会
	public static int ComputerRoomNum = 1;// 杋房数，决定同一时间可上机的班级数
	public static int PE_Time[][];
	
	public user_info() {
		// TODO Auto-generated constructor stub
	}
	
	// 获取课程简称
	private String getLessonForShort(String name) {
		if ("语文,语".contains(name))
			return LessonName[0];
		else if ("数学,数".contains(name))
			return LessonName[1];
		else if ("英语,英".contains(name))
			return LessonName[2];
		else if ("物理,物".contains(name))
			return LessonName[3];
		else if ("化学,化".contains(name))
			return LessonName[4];
		else if ("生物,生".contains(name))
			return LessonName[5];
		else if ("政治,政".contains(name))
			return LessonName[6];
		else if ("历史,史".contains(name))
			return LessonName[7];
		else if ("地理,地".contains(name))
			return LessonName[8];
		else if ("电脑,电,微机".contains(name))
			return LessonName[9];
		else if ("音乐,音".contains(name))
			return LessonName[10];
		else if ("书法,书".contains(name))
			return LessonName[11];
		else if ("体育,体".contains(name))
			return LessonName[12];
		else
			return null;
	}

	// 获取不可用时间
	public int[] getLessonUnAvailableTime(String name) {
		int time1[] = { 25, 26, 27 };
		int time2[] = { 1, 2, 3 };
		int time3[] = { 5, 6, 7 };
		int time4[] = { 17, 18, 19 };
		int time5[] = { 37, 38, 39 };
		int time6[] = { 29, 30, 31 };
		if ("语文,语".contains(name))
			return time1;
		else if ("数学,数".contains(name))
			return time1;
		else if ("英语,英".contains(name))
			return time2;
		else if ("物理,物".contains(name))
			return time3;
		else if ("化学,化".contains(name))
			return time3;
		else if ("生物,生".contains(name))
			return time4;
		else if ("政治,政".contains(name))
			return time5;
		else if ("历史,史".contains(name))
			return time6;
		else if ("地理,地".contains(name))
			return time6;
		else
			return null;
	}

	// 获取课时
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
