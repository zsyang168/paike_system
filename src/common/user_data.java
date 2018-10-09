package common;

public class user_data {
	
	public static String t_file_path="任课安排表.xlsx";
	public static String c_file_path="课程信息.xlsx";
	
	public static int DAYS_OF_WEEK = 5;// 一周上课天数
	public static int Courses_OF_DAY = 8;// 一天上课节数
	public static int TotalTime=DAYS_OF_WEEK*Courses_OF_DAY;
	public static int CourseS_OF_AM = 5;// 一天上课节数
	public static int CourseS_OF_PM = 3;// 一天上课节数
	public static int ComputerRoomNum = 1;// 杋房数，决定同一时间可上机的班级数
	
	public static String morning_reading[]={"语文","英语","语文","英语","班会"};
	
	public user_data() {
		// TODO Auto-generated constructor stub
		
	}
	
	

}
