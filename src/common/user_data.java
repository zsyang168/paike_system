package common;

public class user_data {
	
	public static String t_file_path="�οΰ��ű�.xlsx";
	public static String c_file_path="�γ���Ϣ.xlsx";
	
	public static int DAYS_OF_WEEK;// һ���Ͽ�����
	public static int PERIODS_OF_AM;// �����Ͽν���
	public static int PERIODS_OF_PM;// �����Ͽν���
	public static int PERIODS_OF_DAY;// һ���Ͽν���
	public static int TOTAL_PERIODS;// һ���Ͽν���
	public static int NUM_OF_COMPUTERROOM;// �i����������ͬһʱ����ϻ��İ༶��
	
	public static String morning_reading[]={"����","Ӣ��","����","Ӣ��","���"};
	
	public user_data() {
		// TODO Auto-generated constructor stub
		data_int();
	}
	
	private void data_int()
	{
		DAYS_OF_WEEK = 5;// һ���Ͽ�����
		PERIODS_OF_AM = 5;// һ���Ͽν���
		PERIODS_OF_PM = 3;// һ���Ͽν���
		PERIODS_OF_DAY = PERIODS_OF_AM + PERIODS_OF_PM;// һ���Ͽν���
		TOTAL_PERIODS = DAYS_OF_WEEK * PERIODS_OF_DAY;// һ���Ͽν���
		NUM_OF_COMPUTERROOM = 1;// �i����������ͬһʱ����ϻ��İ༶��
	}
	
	

}
