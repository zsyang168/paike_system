package data;

import java.util.LinkedList;

import common.print;
import excel.excel_func;
import user.user_info;

public class timetable_info {
	/******************************* 共享变量区 *******************************/
	public static int teacher_num;// 教师数
	public static int class_num;// 班级数
	public static int course_num;// 课程数
	public static LinkedList<teaching_info> teaching_info;// 教师类数据
	public static int ComputerLessonID;// 电脑课号
	public static int ComputerTeacherID[];// 电脑课教师ID
	public static int timetable[][];

	public timetable_info() {
		// TODO Auto-generated constructor stub
		data_init();
	}
	
	private void data_init()
	{
		excel_func test=new excel_func();
		String[][] data=(String[][]) test.get_excel_data_by_name(user_info.file_path,"Sheet1");
		
		int row_num=data.length;
		int[] line_num = new int[row_num];
		
		for (int i=0; i < row_num; i++)
			for (int j=0; j < data[i].length; j++)
				if (data[i][j] != null && data[i][j] != "")
					line_num[i] ++;
		
		int max_line=0;
		for (int i=0;i< row_num; i++)
			if (line_num[i] > max_line)
				max_line = line_num[i];
		
		int start_line=-1;
		int end_line= -1;
		
		for (int i=0; i < row_num; i++)
		{
			if ( line_num[i] == max_line && start_line == -1)
			{
				start_line = i;
				end_line = i;
				continue;
			}
			
			if( line_num[i] == max_line && end_line != -1)
				end_line =i;
		}
		
		class_num = end_line - start_line;
		
		
		System.out.println(row_num+" "+start_line + " " + end_line);
		print.show(line_num);
		
	}

}
