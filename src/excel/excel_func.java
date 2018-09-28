package excel;

public class excel_func {

	public Object[][] get_excel_data_by_name(String file_name, String sheet_name) {
		// TODO Auto-generated method stub
		excel_read test=new excel_read(file_name);
		Object[][] result=null;
		result=test.get_sheet_by_name(sheet_name);
		test.excel_free();
		if (result == null)
			System.out.println("get file " + file_name + " sheet " + sheet_name +"failed!");
		return result;
	}

	public Object[][] get_excel_data_by_index(String file_name, int sheet_index) {
		// TODO Auto-generated method stub
		excel_read test=new excel_read(file_name);
		Object[][] result=null;
		result=test.get_sheet_by_index(sheet_index);
		test.excel_free();
		if (result == null)
			System.out.println("get file " + file_name + " sheet " + sheet_index +"failed!");
		return result;
	}

}
