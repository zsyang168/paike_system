package common;

import java.util.List;

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
	
	public static void show(List<Teaching> data) {
		for (Teaching ti :data) {
						System.out.println(ti.get_teacher().get_id());
						show(ti.get_class_list());
						show(ti.get_time_list());
		}
		System.out.println();
	}
}
