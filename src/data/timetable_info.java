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
	
	public static class_info[] class_list;
	public static course_info[] course_list;
	public static teacher_info[] teacher_list;
	
	public static LinkedList<teaching_info> teaching_info;// 教师类数据
	public static int ComputerLessonID;// 电脑课号
	public static int ComputerTeacherID[];// 电脑课教师ID
	public static int timetable[][];

	public timetable_info() {
		// TODO Auto-generated constructor stub
		new user_info();
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
		
		int max_column=0;
		for (int i=0;i< row_num; i++)
			if (line_num[i] > max_column)
				max_column = line_num[i];
		
		int start_line=-1;
		int end_line= -1;
		
		for (int i=0; i < row_num; i++)
		{
			if ( line_num[i] == max_column && start_line == -1)
			{
				start_line = i;
				end_line = i;
				continue;
			}
			
			if( line_num[i] == max_column && end_line != -1)
				end_line =i;
		}
		
		String header[]=data[start_line];
		
		int class_column = -1;
		int class_type_column = -1;
		int master_column = -1;
		int first_course_column = -1;
		int last_course_column = -1;
		
		for (int i =0; i< max_column; i++)
		{
			if (header[i].equals("班别"))
			{
				class_column =i;
				continue;
			}
			
			if (header[i].equals("班类型"))
			{
				class_type_column =i;
				continue;
			}
			
			if (header[i].equals("班主任"))
			{
				master_column =i;
				continue;
			}
			
			if (user_info.course_list.contains(header[i]) && first_course_column == -1)
			{
				first_course_column = i;
				last_course_column = i;
				continue;
			}
			
			if (user_info.course_list.contains(header[i]) && last_course_column != -1)
			{
				last_course_column = i;
			}
			
		}
		
		String class_name = null;
		String class_type = null;
		class_num = end_line - start_line;
		class_list =new class_info[class_num];
		for(int i= 0; i < class_num; i ++)
		{
			class_name = data[start_line + 1 + i][class_column];
			class_type = data[start_line + 1 + i][class_type_column];
			class_info temp =new class_info(i,class_name , class_type);
			class_list[i] = temp;
		}
		
		String course_name = null;
		course_num = last_course_column - first_course_column +1;
		course_list =new course_info[course_num];
		for(int i= 0; i < course_num; i ++)
		{
			course_name = data[start_line][first_course_column+i];
			course_info temp =new course_info(i, course_name);
			course_list[i] = temp;
		}
		
		LinkedList<String> master_list=new LinkedList<String>();
		LinkedList<String> teacher_name_list =new LinkedList<String>();
		LinkedList<Integer> teacher_course_list=new LinkedList<Integer>();
		String teacher_name = null;
		for(int i= 0; i < class_num; i ++)
		{
			master_list.add(data[start_line + 1 + i][master_column]);
			for (int j=0; j < course_num; j ++)
			{
				teacher_name =data[start_line + 1 + i][first_course_column+j];
				if(!teacher_name_list.contains(teacher_name))
				{
					teacher_name_list.add(teacher_name);
					teacher_course_list.add(j);
				}
					
			}
		}
		teacher_num=teacher_name_list.size();
		teacher_list =new teacher_info[teacher_num];
		for(int i= 0; i < teacher_num; i ++)
		{
			teacher_name = teacher_name_list.get(i);
			teacher_info temp =new teacher_info(i, teacher_name);
			temp.set_course_id(teacher_course_list.get(i));
			if (master_list.contains(teacher_name))
				temp.set_master(true);
	
			teacher_list[i]=temp; 
		}
		
		System.out.println(row_num+" "+start_line + " " + end_line);
		System.out.println(class_num+" "+course_num + " " + teacher_num);
		print.show(teacher_list);
		
	}

}
