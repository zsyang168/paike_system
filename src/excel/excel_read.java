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
	//�Ա�����ȡ����
	public Object[][] get_sheet_by_name(String name) {
		if (null == wb)
			return null;
		Sheet sheet = wb.getSheet(name);
		if (null == sheet)
			return null;
		return get_data(sheet);
	}
	//�Ա��±��ȡ����
	public Object[][] get_sheet_by_index(int index) {
		if (null == wb)
			return null;
		Sheet sheet = wb.getSheetAt(index);
		if (null == sheet)
			return null;
		return get_data(sheet);
	}
	//�ͷű�����
	public void excel_free()
	{
		destory(wb,fs);
	}
	// ���캯��
	public excel_read(String file_name) {
		// ��ʼ������
		excel_file = null;
		fs = null;
		wb = null;
		excel_init(file_name);
	}

	private void excel_init(String file_name) {
		// ����ļ�����
		int iIndex = file_name.lastIndexOf(".");
		String ext = (iIndex < 0) ? "" : file_name.substring(iIndex + 1).toLowerCase();
		if (!"xls,xlsx".contains(ext) || "".contains(ext)) {
			System.out.println("�ļ����Ͳ���EXCEL��");
			return;
		}
		// ���ļ�
		try {
			excel_file = new File(file_name);
			// ��ȡ�������������
			fs = new FileInputStream(excel_file);
			// ��ȡEXCEL��
			if (ext.equals("xls"))
				wb = new HSSFWorkbook(fs);
			else
				wb = new XSSFWorkbook(fs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��ȡ�������
	private Object[][] get_data(Sheet sheet) {
		Object data[][] = null;
		try {
			Row row = null;
			// ��ȡ����
			int lastRowNum = sheet.getLastRowNum();
			data = new String[lastRowNum + 1][];// ��ʼ��������ݴ������
			// ѭ����ȡ
			for (int i = 0; i <= lastRowNum; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					// ��ȡ�����������д洢�ռ�
					int ColNum = row.getLastCellNum();
					if (0 > ColNum)
						break;
					data[i] = new String[ColNum];
					// ��ȡÿһ�е�ֵ
					for (int j = 0; j < ColNum; j++) {
						Cell cell = row.getCell(j);
						Object value = getCellValue(cell);
						// �������
						data[i][j] = value;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ���ؽ��
		return data;
	}

	/**
	 * �رձ������
	 */
	private void destory(Workbook wb, FileInputStream fs) {
		// �رձ�
		if (wb != null)
			try {
				wb.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		// �ر�������
		if (fs != null)
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	/***
	 * ��ȡ��Ԫ���ֵ
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
