package main;

import java.util.List;

import common.print;
import entity.Teaching;

public class acs_run extends acs_data {

	public acs_run() {
		// TODO Auto-generated constructor stub
		priority_update(pri_list);
		System.out.println(class_num + " " + teacher_num);
		print.show(pri_list, -1);
	}

	public void run(List<Teaching> list) {
		priority_update(list);
		Teaching t = list.get(0);
		int class_list[] = t.get_class_list();
		int cl_num = class_list.length - 1;
		int cid = t.get_course().get_no();
		
		for (int i = 1; i < cl_num; i++) {
			for(int j=1;j<=days;j++)
				if (class_course_arrangment[i][cid][j]==0)
				{
					
				}
		}

	}

}
