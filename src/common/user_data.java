package common;

public class user_data {
	
	public static String t_file_path="任课安排表.xlsx";
	public static String c_file_path="课程信息.xlsx";
	
	public static int DAYS_OF_WEEK;// 一周上课天数
	public static int PERIODS_OF_AM;// 上午上课节数
	public static int PERIODS_OF_PM;// 下午上课节数
	public static int PERIODS_OF_DAY;// 一天上课节数
	public static int TOTAL_PERIODS;// 一周上课节数
	public static int NUM_OF_COMPUTERROOM;// 杋房数，决定同一时间可上机的班级数
	
	public static String morning_reading[]={"","语文","英语","语文","英语","班会"};
	
	public user_data() {
		// TODO Auto-generated constructor stub
		data_int();
	}
	
	private void data_int()
	{
		DAYS_OF_WEEK = 5;// 一周上课天数
		PERIODS_OF_AM = 5;// 一天上课节数
		PERIODS_OF_PM = 3;// 一天上课节数
		PERIODS_OF_DAY = PERIODS_OF_AM + PERIODS_OF_PM;// 一天上课节数
		TOTAL_PERIODS = DAYS_OF_WEEK * PERIODS_OF_DAY;// 一周上课节数
		NUM_OF_COMPUTERROOM = 1;// 杋房数，决定同一时间可上机的班级数
	}
	
	

}
