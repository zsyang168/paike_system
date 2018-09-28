package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excel_read {
	File excel_file;
	FileInputStream fs;
	Workbook wb;
	//以表名读取数据
	public Object[][] get_sheet_by_name(String name) {
		if (null == wb)
			return null;
		Sheet sheet = wb.getSheet(name);
		if (null == sheet)
			return null;
		return get_data(sheet);
	}
	//以表下标读取数据
	public Object[][] get_sheet_by_index(int index) {
		if (null == wb)
			return null;
		Sheet sheet = wb.getSheetAt(index);
		if (null == sheet)
			return null;
		return get_data(sheet);
	}
	//释放表数据
	public void excel_free()
	{
		destory(wb,fs);
	}
	// 构造函数
	public excel_read(String file_name) {
		// 初始化数据
		excel_file = null;
		fs = null;
		wb = null;
		excel_init(file_name);
	}

	private void excel_init(String file_name) {
		// 检查文件类型
		int iIndex = file_name.lastIndexOf(".");
		String ext = (iIndex < 0) ? "" : file_name.substring(iIndex + 1).toLowerCase();
		if (!"xls,xlsx".contains(ext) || "".contains(ext)) {
			System.out.println("文件类型不是EXCEL！");
			return;
		}
		// 打开文件
		try {
			excel_file = new File(file_name);
			// 获取表格数据输入流
			fs = new FileInputStream(excel_file);
			// 获取EXCEL表
			if (ext.equals("xls"))
				wb = new HSSFWorkbook(fs);
			else
				wb = new XSSFWorkbook(fs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 读取表格数据
	private Object[][] get_data(Sheet sheet) {
		Object data[][] = null;
		try {
			Row row = null;
			// 获取行数
			int lastRowNum = sheet.getLastRowNum();
			data = new String[lastRowNum + 1][];// 初始化表格数据存放数组
			// 循环读取
			for (int i = 0; i <= lastRowNum; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					// 获取列数，分配列存储空间
					int ColNum = row.getLastCellNum();
					if (0 > ColNum)
						break;
					data[i] = new String[ColNum];
					// 获取每一列的值
					for (int j = 0; j < ColNum; j++) {
						Cell cell = row.getCell(j);
						Object value = getCellValue(cell);
						// 存放数据
						data[i][j] = value;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回结果
		return data;
	}

	/**
	 * 关闭表格连接
	 */
	private void destory(Workbook wb, FileInputStream fs) {
		// 关闭表
		if (wb != null)
			try {
				wb.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		// 关闭数据流
		if (fs != null)
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	/***
	 * 读取单元格的值
	 */
	@SuppressWarnings("deprecation")
	private Object getCellValue(Cell cell) {
		Object result = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				result = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				result = cell.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				result = cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA:
				result = cell.getCellFormula();
				break;
			case Cell.CELL_TYPE_ERROR:
				result = cell.getErrorCellValue();
				break;
			case Cell.CELL_TYPE_BLANK:
				break;
			default:
				break;
			}
		}
		return result;
	}
}
