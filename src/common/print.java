package common;

import java.util.List;

import entity.Course;
import entity.Teacher;
import entity.Teaching;

public class print {
	public static void show(Object[][] data) {
		for (int i = 0; i < data.length; i++) {
			if (null != data[i])
				for (int j = 0; j < data[i].length; j++)
					if (null != data[i][j])
						System.out.print(data[i][j] + " ");
					else
						System.out.println("null ");
			System.out.println();
		}
	}
	
	public static void show(int[] data) {
		for (int i = 0; i < data.length; i++) {
						System.out.print(data[i] + " ");
		}
		System.out.println();
	}
	
	public static void show(Teacher[] data) {
		for (int i = 0; i < data.length; i++) {
						System.out.println(data[i].get_id() + " " +data[i].get_name() +" "+data[i].is_master());
		}
		System.out.println();
	}
	public static void show(Course[] data) {
		for (int i = 0; i < data.length; i++) {
						System.out.println(data[i].get_id() + " " +data[i].get_name() +" "+data[i].get_grade()+" "+data[i].get_periods());
						show(data[i].get_prepare_time());
		}
		System.out.println();
	}
	public static void show(List<Teaching> data,int count) {
		int i=0;
		for (Teaching ti :data) {
						System.out.println(ti.get_teacher().get_name() +" " +ti.get_course().get_name()+" "+ti.get_priority());
						show(ti.get_class_list());
						show(ti.get_time_list());
						
						if(count == -1)
							continue;
						i ++;
						if(i >= count)
							break;
		}
		System.out.println();
	}
}
