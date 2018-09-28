package main;

import excel.excel_func;
import tools.print;

public class paike_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		excel_func test=new excel_func();
		Object[][] data=test.get_excel_data_by_name("хн©н╟╡ее╠М.xlsx","Sheet1");
		System.out.println(data.length);
		print.show(data);
	}

}
