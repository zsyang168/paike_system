package common;

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
}
