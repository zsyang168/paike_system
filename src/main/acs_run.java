package main;

import common.common;
import common.print;
import entity.Teaching;

public class acs_run extends acs_data {

	public acs_run() {
		// TODO Auto-generated constructor stub
		priority_update();
		System.out.println(class_num + " " + teacher_num);
		print.show(teaching_list, -1);
	}

	public void run() {
		priority_update();
		Teaching t = teaching_list.get(0);
		int class_list[] = t.get_class_list();
		int cl_num = class_list.length - 1;
		int course_id = t.get_course().get_id();
		int cid = common.hash_method(course_id, course_num);
		
		for (int i = 1; i < cl_num; i++) {
			for(int j=1;j<=days;j++)
				if (class_course_arrangment[i][cid][j]==0)
				{
					
				}
		}

	}

}
