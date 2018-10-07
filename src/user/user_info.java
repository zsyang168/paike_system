package user;

import java.util.Arrays;
import java.util.List;

import common.oa_time;

public class user_info {
	
	public static String t_file_path="任课安排表.xlsx";
	public static String c_file_path="课程信息.xlsx";
	
	public static List<String> course_list= (List<String>) Arrays.asList(
	new String[] { "语文", "数学", "英语", "物理", "化学", "生物", "政治", "历史", "地理", "电脑", 
	"微机", "音乐", "书法", "体育","语", "数", "英", "物", "化", "生", "政", "史", "地", "电", "音", "书", "体" });
	
	public static int DAYS_OF_WEEK = 5;// 一周上课天数
	public static int Courses_OF_DAY = 8;// 一天上课节数
	public static int TotalTime=DAYS_OF_WEEK*Courses_OF_DAY;
	public static int CourseS_OF_AM = 5;// 一天上课节数
	public static int CourseS_OF_PM = 3;// 一天上课节数
	public static oa_time Class_Meeting_Time = new oa_time(1, 8, 8);// 班会时间，设为-1时没有班会
	public static int ComputerRoomNum = 1;// 杋房数，决定同一时间可上机的班级数
	public static int PE_Time[][];
	
	public user_info() {
		// TODO Auto-generated constructor stub
		
	}
	
	

}
